package mx.com.teclo.digitalizacion.negocio.servicios.imagenes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.negocio.utils.PosicionImagen;
import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;

import mx.com.teclo.digitalizacion.negocio.utils.ValoresImagenValidada;

import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.LBImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValHistHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadorConfigHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadoresHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

@Service
public class ImagenesServiceImpl implements ImagenesService {

	@Autowired
	private ImagenesHDAO imagenesHDAO;

	@Autowired
	private ValidadorConfigHDAO validadorConfigHDAO;

	@Autowired
	private ValidadoresHDAO validadoresHDAO;

	@Autowired
	private AsignValidacionHDAO asignValidacionHDAO;
	
	@Autowired
	private AsignValHistHDAO asignValHistHDAO;
	
	@Autowired
	private LBImagenesHDAO lBImagenesHDAO;
	
	@Autowired
	private LotesHDAO lotesHDAO;
	

	@Override
	@Transactional(readOnly = true)
	public List<LotesVO> getLotes() {
		List<LotesVO> listadoLotesVO = new ArrayList<LotesVO>();

		List<LotesDTO> listadoLotesDTO = imagenesHDAO.metodoPrueba();
		// List<LotesDTO> listadoLotesDTO = lotesHDAO.findAll();

		listadoLotesVO = ResponseConverter.converterLista(new ArrayList<>(), listadoLotesDTO, LotesVO.class);

		return listadoLotesVO;
	}

	/**
	 * El método devuelve el primer conjunto de imágenes a validar por el validador
	 * cuyo idUsuario es especificado. Debe buscar en ValidadorConfigDTO la
	 * configuración del validador dado que esté activa (ST_CONFIG_ACTIVA=1).
	 */
	@Override
	@Transactional(readOnly = false)
	public List<ImagenesSimplesVO> getImagenesIniciales(Long idUsuario) {
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		List<ImagenesDTO> imagenesAsignadas = asignValidacionHDAO.getTodasImagenesAsignadasActivas(validadorActivo);
		List<ImagenesDTO> imagenesEnUI = null;
		if (imagenesAsignadas.size() != 0) {// El validador tiene asignaciones activas.
			imagenesEnUI = asignValidacionHDAO.getTodasImagenesEnUI(validadorActivo);
			if(imagenesEnUI != null && imagenesEnUI.size() > 0) {//Tiene imágenes aún sin validar en la UI
				List<ImagenesSimplesVO> imagenesVOEnUI = //new ArrayList<ImagenesSimplesVO>();
						ResponseConverter.converterLista(new ArrayList<>(),imagenesEnUI, ImagenesSimplesVO.class);
				return imagenesVOEnUI;
			}else {//Tiene imágenes asignadas pero ninguna en la UI
				List<ImagenesSimplesVO> imagenesRetornar = getProximoGrupoImagenesAsignadas(idUsuario);
				asignValidacionHDAO.setImagenesEnUIById(getIds(imagenesRetornar), validadorActivo);
				return imagenesRetornar; 
			}
		}
		//Llegado a este punto, es la primera vez que al validador se le asignan validaciones
		Long cantidadTotalMaximaAsignar = validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo)
				.getNuLimImgAsignacion();
		int cantidadImagenesRetornar = (int)validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuImgMax();

		List<ImagenesDTO> todasLasImagenesNoAsignadas = imagenesHDAO.getTodasLasImagenesDTONoAsignadas();
		int sizeListaImagenesNoAsignadas = todasLasImagenesNoAsignadas.size();
		if(sizeListaImagenesNoAsignadas == 0) {
			/*No hay imágenes para asignar, debo retornar una lista vacía*/
			List<ImagenesSimplesVO> listaRetornoVacia = new ArrayList<>();
			return listaRetornoVacia;
		}
		int cantImagenesAsignar = (int) (sizeListaImagenesNoAsignadas >= cantidadTotalMaximaAsignar
				? cantidadTotalMaximaAsignar
				: sizeListaImagenesNoAsignadas);

		List<ImagenesDTO> imagenesParaAsignar = todasLasImagenesNoAsignadas.subList(0, cantImagenesAsignar);
		asignValidacionHDAO.asignarImagenesValidador(imagenesParaAsignar, validadorActivo);
		validadorConfigHDAO.setCantidadImagenesRestantesPorValidar(idValidadorActivo, (long) cantImagenesAsignar);
		/*
		 * En este punto el validador ya tiene la cantidad máxima de boletas asignadas,
		 * sin embargo, deben enviarse al front-end solo la cantidad máxima que se
		 * estipuló puede validar a la vez. Este número está definido por
		 * cantidadParcialMaximaAsignar, por lo que debe devolverse una lista con esta
		 * cantidad de imágenes.
		 */

		List<ImagenesSimplesVO> listadoImagenesIniciales = ResponseConverter.converterLista(new ArrayList<>(),
				imagenesParaAsignar.subList(0,cantidadImagenesRetornar), ImagenesSimplesVO.class);
		asignValidacionHDAO.setImagenesEnUIById(getIds(listadoImagenesIniciales), validadorActivo);

		return listadoImagenesIniciales;
	}
	
	private List<Long> getIds(List<ImagenesSimplesVO> imagenesVO){
		List<Long> listaRetornar = new ArrayList<>();
		
		for(ImagenesSimplesVO imagenVO : imagenesVO) {
			Long valor = new Long(imagenVO.getIdImagen());
			listaRetornar.add(valor);
		}
		
		return listaRetornar;
	}

	private List<Long> getIdsVO(List<ImagenesVO> imagenesVO){
		List<Long> listaRetornar = new ArrayList<>();
		
		for(ImagenesVO imagenVO : imagenesVO) {
			Long valor = new Long(imagenVO.getIdImagen());
			listaRetornar.add(valor);
		}
		
		return listaRetornar;
	}

	/* Devuelve el próximo grupo de imágenes a validar. Siempre en cantidad menor o igual que el valor
	 * de CONFIG_VALIDADOR.NU_IMG_MAX 
	 * PRECONDICIÓN: El usuario debe tener asignaciones de validaciones activas
	 */
	@Override
	@Transactional(readOnly = false)
	public List<ImagenesSimplesVO> getProximoGrupoImagenesAsignadas(Long idUsuario) {
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		int cantidadImagenesRetornar = (int)validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuImgMax();
		List<ImagenesDTO> imagenesAsignadas = asignValidacionHDAO.getTodasImagenesAsignadasActivas(validadorActivo);
		int cantImagenesAsignadas = imagenesAsignadas.size();
		
		if (cantImagenesAsignadas == 0) // El validador no tiene asignaciones activas, por lo que este método no continuará ejecutándose
			return null;
		int cantidadRealRetornar = (cantImagenesAsignadas < cantidadImagenesRetornar) 
				? cantImagenesAsignadas : cantidadImagenesRetornar;

		List<ImagenesSimplesVO> imagenesSimplesVO = ResponseConverter.converterLista(new ArrayList<>(),
				imagenesAsignadas.subList(0,cantidadRealRetornar), ImagenesSimplesVO.class);
		
		return imagenesSimplesVO;
	}

	@Override
	@Transactional(readOnly = false)
	public List<ImagenesVO> getProximoGrupoImagenesAsignadasVO(Long idUsuario) {
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		int cantidadImagenesRetornar = (int)validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuImgMax();
		List<ImagenesDTO> imagenesAsignadas = asignValidacionHDAO.getTodasImagenesAsignadasActivas(validadorActivo);
		int cantImagenesAsignadas = imagenesAsignadas.size();
		
		if (cantImagenesAsignadas == 0) // El validador no tiene asignaciones activas, por lo que este método no continuará ejecutándose
			return null;
		int cantidadRealRetornar = (cantImagenesAsignadas < cantidadImagenesRetornar) 
				? cantImagenesAsignadas : cantidadImagenesRetornar;

		List<ImagenesVO> imagenesVO = new ArrayList<>();
		Utils.llenadoDatosListaImagenDTOtoListaImagenVO(imagenesAsignadas.subList(0,cantidadRealRetornar), imagenesVO);
		/*List<ImagenesVO> imagenesVO = ResponseConverter.converterLista(new ArrayList<>(),
				imagenesAsignadas.subList(0,cantidadRealRetornar), ImagenesVO.class);*/
		
		return imagenesVO;
	}



	/* El método marca como aceptada la imagen,
	 * Realiza las operaciones correspondientes en las tablas ASIGN_VALIDACION y ASIGN_VAL_HIST
	 * @see mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService#marcarImagenComoAceptada(mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO)
	 */
	/*@Override
	@Transactional(readOnly = false)
	public void marcarImagenComoAceptada(ImagenesSimplesVO imagenSimpleVO) throws Exception {
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(imagenSimpleVO.getIdImagen());
		
		imagenesHDAO.marcarImagenDTOComoAceptada(imagenDTO);
		lotesHDAO.incrementaCantidadImagenesAceptadas(imagenDTO.getIdLote());
		
		/*Ahora hay que decrementar la cantidad de boletas asignadas, borrar  de la tabla 
		 * ASIGN_VALIDACION  y crear el registro histórico en la tabla ASIGN_VAL_HIST */
		/*limpiezaAsignaciones(imagenDTO);
	}*/

	/*Este es el método usado para aceptar una imágen, el anterior está de reserva por si cambia el negocio*/
	/*No está actualizando el estado del lote correspondiente*/
	@Override
	@Transactional(readOnly = false)
	public void marcarImagenVOComoAceptada(ImagenesVO imagenVO) throws Exception {
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(imagenVO.getIdImagen());
		
		//imagenDTO.setStValidada((short) ValoresImagenValidada.ACEPTADA.getValor());
		imagenesHDAO.marcarImagenDTOComoAceptada(imagenDTO);
		lotesHDAO.incrementaCantidadImagenesAceptadas(imagenDTO.getIdLote());
		Utils.llenadoDatosImagenVOtoImagenDTO(imagenVO, imagenDTO);
		imagenesHDAO.update(imagenDTO);
		
		
		/*Ahora hay que decrementar la cantidad de boletas asignadas, borrar  de la tabla 
		 * ASIGN_VALIDACION  y crear el registro histórico en la tabla ASIGN_VAL_HIST */
		limpiezaAsignaciones(imagenDTO);
	}

	
	/**
	 * eliminarse el registro de la tabla ASIGN_VALIDACION
	 * y crea uno con los datos correspondientes en la tabla ASIGN_VAL_HIST
	 * @param imagenDTO
	 */
	private void limpiezaAsignaciones(ImagenesDTO imagenDTO) {
		ValidadoresDTO validador = asignValidacionHDAO.getAsignacionActivaByImagen(imagenDTO).getIdValidador();
		validadorConfigHDAO.decrementaCantidadImagenesRestantesPorValidar(validador.getIdValidador(), 1);
		AsignValidacionDTO asignacionActiva = asignValidacionHDAO.getAsignacionActivaByImagen(imagenDTO);
		asignacionActiva.setStActivo((short) 1);
		asignValHistHDAO.addNuevaAsignacionHistoricaByAsignacion(asignacionActiva);
		asignValidacionHDAO.delete(asignacionActiva);
	}

	/* 
	 * El método marca como cancelada la imagen, debe eliminarse el registro de la tabla ASIGN_VALIDACION
	 * y crearse uno con los datos correspondientes en la tabla ASIGN_VAL_HIST
	 * @see mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService#marcarImagenComoRechazada(mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO)
	 */
	@Override
	@Transactional(readOnly = false)
	public void marcarImagenComoRechazada(Long idImagen) throws Exception {
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(idImagen);
		
		imagenesHDAO.marcarImagenDTOComoRechazada(imagenDTO);
		lotesHDAO.incrementaCantidadImagenesRechazadas(imagenDTO.getIdLote());
		//imagenesHDAO.update(imagenDTO);
		/*Ahora hay que decrementar la cantidad de boletas asignadas, borrar  de la tabla 
		 * ASIGN_VALIDACION  y crear el registro histórico en la tabla ASIGN_VAL_HIST */
		limpiezaAsignaciones(imagenDTO);
	}

	/* Devuelve el VO reflejo del DTO dada una ImagenSimpleVO
	 * @see mx.com.teclo.digitalizacion.negocio.servicios.imagenes.ImagenesService#getImagenVO(mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO)
	 */
	@Override
	@Transactional(readOnly = true)
	public ImagenesVO getImagenVO(ImagenesSimplesVO imagenSimpleVO) throws Exception {
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(imagenSimpleVO.getIdImagen());
		ImagenesVO imagenVO = new ImagenesVO();
		Utils.llenadoDatosImagenDTOtoImagenVO(imagenDTO, imagenVO);
				//ResponseConverter.copiarPropiedadesFull(imagenDTO, ImagenesVO.class);
		
		return imagenVO;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ImagenesVO getImagenVOPorId(Long idImagenSimpleVO) {
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(idImagenSimpleVO);
		ImagenesVO imagenVO = new ImagenesVO();
		try {
			Utils.llenadoDatosImagenDTOtoImagenVO(imagenDTO, imagenVO);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return imagenVO;
	}

	@Override
	@Transactional(readOnly = false)
	public List<ImagenesVO> getImagenesInicialesVO(Long idUsuario) {
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		List<ImagenesDTO> imagenesAsignadas = asignValidacionHDAO.getTodasImagenesAsignadasActivas(validadorActivo);
		List<ImagenesDTO> imagenesDTOEnUI = null;
		if (imagenesAsignadas.size() != 0) {// El validador tiene asignaciones activas.
			imagenesDTOEnUI = asignValidacionHDAO.getTodasImagenesEnUI(validadorActivo);
			if(imagenesDTOEnUI != null && imagenesDTOEnUI.size() > 0) {//Tiene imágenes aún sin validar en la UI
				List<ImagenesVO> imagenesVOEnUI = new ArrayList<>();
				Utils.llenadoDatosListaImagenDTOtoListaImagenVO(imagenesDTOEnUI, imagenesVOEnUI);
				return imagenesVOEnUI;
			}else {//Tiene imágenes asignadas pero ninguna en la UI
				List<ImagenesVO> imagenesRetornar = getProximoGrupoImagenesAsignadasVO(idUsuario);
				asignValidacionHDAO.setImagenesEnUIById(getIdsVO(imagenesRetornar), validadorActivo);
				return imagenesRetornar; 
			}
		}
		//Llegado a este punto, el validador no tiene ninguna asignación
		Long cantidadTotalMaximaAsignar = validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuLimImgAsignacion();
		int cantidadImagenesRetornar = (int)validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuImgMax();

		List<ImagenesDTO> todasLasImagenesNoAsignadas = imagenesHDAO.getTodasLasImagenesDTONoAsignadas();
		
		int sizeListaImagenesNoAsignadas = todasLasImagenesNoAsignadas.size();
		if(sizeListaImagenesNoAsignadas == 0) {
			/*No hay imágenes para asignar, debo retornar una lista vacía*/
			List<ImagenesVO> listaRetornoVacia = new ArrayList<>();
			return listaRetornoVacia;
		}
		int cantImagenesAsignar = (int) (sizeListaImagenesNoAsignadas >= cantidadTotalMaximaAsignar
				? cantidadTotalMaximaAsignar
				: sizeListaImagenesNoAsignadas);

		List<ImagenesDTO> imagenesParaAsignar = todasLasImagenesNoAsignadas.subList(0, cantImagenesAsignar);//lista de imágenes con las imágenes que se asignarán al validador
		asignValidacionHDAO.asignarImagenesValidador(imagenesParaAsignar, validadorActivo);//Asignar las imágenes al validador
		actualizaEstatusLotesDespuesSeleccion(imagenesParaAsignar);//Actualizar el estado de los lotes donde están las imágenes
		validadorConfigHDAO.setCantidadImagenesRestantesPorValidar(idValidadorActivo, (long) cantImagenesAsignar);
		/*
		 * En este punto el validador ya tiene la cantidad máxima de boletas asignadas,
		 * sin embargo, deben enviarse al front-end solo la cantidad máxima que se
		 * estipuló puede validar a la vez. Este número está definido por
		 * "cantidadImagenesRetornar", por lo que debe devolverse una lista con esta
		 * cantidad de imágenes.
		 */
		cantidadImagenesRetornar = cantidadImagenesRetornar < imagenesParaAsignar.size() 
				?cantidadImagenesRetornar
				:imagenesParaAsignar.size();

		List<ImagenesVO> listadoImagenesIniciales = new ArrayList<ImagenesVO>();//Listado de imágenes a devolver, ya en forma de VO
		Utils.llenadoDatosListaImagenDTOtoListaImagenVO(imagenesParaAsignar.subList(0,cantidadImagenesRetornar), 
															listadoImagenesIniciales);
		asignValidacionHDAO.setImagenesEnUIById(getIdsVO(listadoImagenesIniciales), validadorActivo);

		return listadoImagenesIniciales;
	}
	
	private void actualizaEstatusLotesDespuesSeleccion(List<ImagenesDTO> listaImagenes) {
		
		if(listaImagenes.isEmpty())
			return;
		/*Si los lotes de las imágenes asignadas al validador están en estado PROCESADO, este estado debe pasar a ser 
		 * "VALIDANDO_INFORMACION"*/
		List<LotesDTO> lotesDTO = new ArrayList<>();
		for(ImagenesDTO imagen : listaImagenes) {
			if(imagen.getIdLote().getIdStatProceso().getCdEstatusProceso().equals(ValoresEstadosLotes.PROCESADO.getValor())) {
				lotesDTO.add(imagen.getIdLote());
			}
		}
		if(!lotesDTO.isEmpty()) {
			lotesHDAO.actualizaEstatusLotesDespuesSeleccion(lotesDTO);
		}
			
	}

	@Override
	@Transactional(readOnly = false)
	public void marcarLBImagenComoAnverso(Long idLBImagen) {
		lBImagenesHDAO.marcarLBImagenDTOComoAnverso(idLBImagen);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void marcarLBImagenComoReverso(Long idLBImagen) {
		lBImagenesHDAO.marcarLBImagenDTOComoReverso(idLBImagen);
	}

	@Override
	@Transactional(readOnly = false)
	public void pruebaPersistencia() {
		imagenesHDAO.pruebaPersistencia();
		
	}

	@Override
	@Transactional(readOnly = false)
	public List<LBImagenesVO> cambiarPosicionImagen(Long idLbImagen) {
		List<LBImagenesVO> listaRetorno = new ArrayList<>();
		List<LBImagenesDTO> listaDTO = new ArrayList<>();
		LBImagenesDTO anverso = new LBImagenesDTO();
		LBImagenesDTO reverso = new LBImagenesDTO();
		
		LBImagenesDTO firstImage = lBImagenesHDAO.findOne(idLbImagen);
		
		if(firstImage.getCodigoImagen().equals(PosicionImagen.ANVERSO.getValor())) {
			lBImagenesHDAO.marcarLBImagenDTOComoReverso(firstImage);
			reverso = firstImage;
			anverso = lBImagenesHDAO.getLBImagenDTOHermana(firstImage);
		}else {
			lBImagenesHDAO.marcarLBImagenDTOComoAnverso(firstImage);
			anverso = firstImage;
			reverso = lBImagenesHDAO.getLBImagenDTOHermana(firstImage);
		}
		
	
		listaDTO.add(anverso);
		listaDTO.add(reverso);
		
		Utils.llenadoDatosListaLBImagenDTOtoListaLBImagenVO(listaDTO, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = false)
	public void actualizarImagenesPorLoteCancelado(LotesDTO loteDTO) {
		
		
	}





	
}