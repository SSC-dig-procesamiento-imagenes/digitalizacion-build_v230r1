package mx.com.teclo.digitalizacion.negocio.servicios.imagenes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;
import mx.com.teclo.digitalizacion.negocio.servicios.lotes.LotesService;
import mx.com.teclo.digitalizacion.negocio.utils.PosicionImagen;
import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresGenerales;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresImagenValidada;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.AsignacionesDatosVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.VehiculoMarcaVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ArticulosInfracFinanzasHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.EstadisticasImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.LBImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.VehiculoMarcaHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValHistHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.PermisoPospuestasHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadorConfigHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadoresHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstadisticasImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.VehiculoMarcaDTO;

import mx.com.teclo.digitalizacion.negocio.utils.*;

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
	@Autowired
	private EstadisticasImagenesHDAO estadisticasImagenesHDAO;
	@Autowired
	private VehiculoMarcaHDAO vehiculoMarcaHDAO;
	@Autowired
	private ArticulosInfracFinanzasHDAO articulosInfracFinanzasHDAO;
	@Autowired
	private LotesService lotesService;
	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private PermisoPospuestasHDAO permisoPospuestasHDAO;
	

	@Override
	@Transactional(readOnly = true)
	public List<LotesVO> getLotes() {
		List<LotesVO> listadoLotesVO = new ArrayList<LotesVO>();

		List<LotesDTO> listadoLotesDTO = imagenesHDAO.metodoPrueba();

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

		List<ImagenesDTO> todasLasImagenesNoAsignadas = imagenesHDAO.getTodasLasImagenesDTONoAsignadas(cantidadTotalMaximaAsignar);
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


	/*Este es el método usado para aceptar una imágen, el anterior está de reserva por si cambia el negocio*/
	/*No está actualizando el estado del lote correspondiente*/
	@Override
	@Transactional(readOnly = false)
	public boolean marcarImagenVOComoAceptada(ImagenesVO imagenVO, Long idUsuario) throws Exception {
		//Investigar después por qué dejó de funcionar este método: imagenesHDAO.findOne
		//ImagenesDTO imagenDTO = imagenesHDAO.findOne(imagenVO.getIdImagen()); //imagenesHDAO.getImagenDTOById(imagenVO.getIdImagen());
		boolean generaCambioEstadoLote = false;
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(imagenVO.getIdImagen());
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		crearEstadisticasImagenes(imagenDTO, validadorActivo, ValoresImagenValidada.ACEPTADA);
		
		validadorActivo.setFechaUltimaOperacion(new Date());
		validadoresHDAO.update(validadorActivo);
		
		guardarBitacoraImagenAceptada(imagenDTO, imagenVO);
		
		imagenesHDAO.marcarImagenDTOComoAceptada(imagenDTO);
		generaCambioEstadoLote = lotesHDAO.incrementaCantidadImagenesAceptadas(imagenDTO.getIdLote());
		Utils.llenadoDatosImagenVOtoImagenDTO(imagenVO, imagenDTO);
		if(imagenVO.getVehiculoMarca() != null) {
			VehiculoMarcaDTO marcaDTO = vehiculoMarcaHDAO.findOne(imagenVO.getVehiculoMarca().getIdMarca());
			imagenDTO.setVehiculoMarca(marcaDTO);
		}
		
		imagenDTO.setIdValidador(validadorActivo);
		
		imagenesHDAO.update(imagenDTO);
		
		
		/*Ahora hay que decrementar la cantidad de boletas asignadas, borrar  de la tabla 
		 * ASIGN_VALIDACION  y crear el registro histórico en la tabla ASIGN_VAL_HIST */
		limpiezaAsignaciones(imagenDTO);
		
		return generaCambioEstadoLote;
	}

	private void guardarBitacoraImagenAceptada(ImagenesDTO imagenDTO, ImagenesVO imagenVO) {
		
		String valorStValidadaAnterior = "NO EVALUADA";
		if(imagenDTO.getStValidada() != null ) {
			valorStValidadaAnterior = imagenDTO.getStValidada().toString();
		}
		
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
				BitacoraConceptosEnum.VALIDACION_DE_BOLETA.getValor(), 
				valorStValidadaAnterior,
				"ACEPTADA",
				usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
				imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(), 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
		boolean fechasIguales = false;
		

		String	fIni=imagenDTO.getFhInfraccion()!=null? RutinasTiempoImpl.getFecha_YYYYMMDD(imagenDTO.getFhInfraccion()):"";
		String fFin = imagenVO.getFhInfraccion()!=null? RutinasTiempoImpl.getFecha_YYYYMMDD(imagenVO.getFhInfraccion()):"";

		if(Objects.equals(fIni,fFin)) {
			fechasIguales = true;
		}
				
		if(!fechasIguales) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_FECHA.getValor(), 
					fIni,
					fFin,	
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(), 

					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
	 
		if(!Objects.equals(imagenDTO.getNuNumeroFolio(), imagenVO.getNuNumeroFolio())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_NUMERO_FOLIO.getValor(), 
					imagenDTO.getNuNumeroFolio() == null ? "" : imagenDTO.getNuNumeroFolio().toString(),
					imagenVO.getNuNumeroFolio() == null ? "" : imagenVO.getNuNumeroFolio().toString(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		if(!Objects.equals(imagenDTO.getCodigoPlaca(), imagenVO.getCodigoPlaca())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_PLACA.getValor(), 
					imagenDTO.getCodigoPlaca()==null?"":imagenDTO.getCodigoPlaca(),
					imagenVO.getCodigoPlaca()==null?"":imagenVO.getCodigoPlaca(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),					
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		if(!Objects.equals(imagenDTO.getNuFraccion(), imagenVO.getNuFraccion())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_FRACCION.getValor(), 
					imagenDTO.getNuFraccion() == null ? "" : imagenDTO.getNuFraccion().toString(),
					imagenVO.getNuFraccion() == null ? "" : imagenVO.getNuFraccion().toString(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		if(!Objects.equals(imagenDTO.getNuParrafo(), imagenVO.getNuParrafo())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_PARRAFO.getValor(), 
					imagenDTO.getNuParrafo() == null ? "" : imagenDTO.getNuParrafo().toString(),
					imagenVO.getNuParrafo() == null ? "" : imagenVO.getNuParrafo().toString(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		if(!Objects.equals(imagenDTO.getNumeroLicencia(), imagenVO.getNumeroLicencia())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_NUMERO_LICENCIA.getValor(),
					imagenDTO.getNumeroLicencia() == null ? "" : imagenDTO.getNumeroLicencia().toString(),
					imagenVO.getNumeroLicencia() == null ? "" : imagenVO.getNumeroLicencia().toString(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		
		String nuevoLugarExp = imagenVO.getNbLicExpEn() == null ? "" : imagenVO.getNbLicExpEn();
		if(Objects.equals("99", nuevoLugarExp)) {
			nuevoLugarExp = "OTRO";
		}else if(Objects.equals("15", nuevoLugarExp)) {
			nuevoLugarExp = "ESTADO DE MÉXICO";
		}else if(Objects.equals("9", nuevoLugarExp)) {
			nuevoLugarExp = "DF";
		}else {
			nuevoLugarExp = "DESCONOCIDO";
		}
		
		if(!Objects.equals(imagenDTO.getNbLicExpEn(), imagenVO.getNbLicExpEn())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_LUGAR_EXP_LICENCIA.getValor(), 
					imagenDTO.getNbLicExpEn() == null ? "" : imagenDTO.getNbLicExpEn(),
					nuevoLugarExp,
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		if(!Objects.equals(imagenDTO.getNuInciso(), imagenVO.getNuInciso())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_INCISO.getValor(), 
					imagenDTO.getNuInciso() == null ? "" : imagenDTO.getNuInciso(),
					imagenVO.getNuInciso() == null ? "" : imagenVO.getNuInciso(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		if(!Objects.equals(imagenDTO.getNbUtDelegacion(), imagenVO.getNbUtDelegacion())) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_UNIDAD_TERRITORIAL.getValor(), 
					imagenDTO.getNbUtDelegacion() == null ? "" : imagenDTO.getNbUtDelegacion(),
					imagenVO.getNbUtDelegacion() == null ? "" : imagenVO.getNbUtDelegacion(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		String marcaDTO = imagenDTO.getVehiculoMarca() == null ? "" : imagenDTO.getVehiculoMarca().getNombreMarca();
		String marcaVO = imagenVO.getVehiculoMarca() == null ? "" : imagenVO.getVehiculoMarca().getNombreMarca();
		
		if(!Objects.equals(marcaDTO, marcaVO) ) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_MARCA_VEHICULO.getValor(), 
					marcaDTO,
					marcaVO,
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		
	}

	private void crearEstadisticasImagenes(ImagenesDTO imagenDTO, ValidadoresDTO validadorDTO, ValoresImagenValidada valor) {
		EstadisticasImagenesDTO stat = new EstadisticasImagenesDTO();
		
		stat.setFechaValidacion(new Date(System.currentTimeMillis()));
		stat.setIdImagen(imagenDTO);
		stat.setIdLote(imagenDTO.getIdLote());
		stat.setIdValidador(validadorDTO);
		stat.setValidacion(valor.getValor().shortValue());
		
		estadisticasImagenesHDAO.save(stat);
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
		asignacionActiva.setStActivo((short) ValoresGenerales.ACTIVO.getVal());
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
	public boolean marcarImagenComoRechazada(Long idImagen, Long idUsuario, String causa) throws Exception {
		boolean generaCambioEstadoLote = false;
		ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(idImagen);
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		crearEstadisticasImagenes(imagenDTO, validadorActivo, ValoresImagenValidada.RECHAZADA);

		guardarBitacoraImagenRechazada(imagenDTO);
		
		validadorActivo.setFechaUltimaOperacion(new Date());
		validadoresHDAO.update(validadorActivo);
		
		imagenesHDAO.marcarImagenDTOComoRechazada(imagenDTO, validadorActivo, causa);
		generaCambioEstadoLote = lotesHDAO.incrementaCantidadImagenesRechazadas(imagenDTO.getIdLote());
		//imagenesHDAO.update(imagenDTO);
		/*Ahora hay que decrementar la cantidad de boletas asignadas, borrar  de la tabla 
		 * ASIGN_VALIDACION  y crear el registro histórico en la tabla ASIGN_VAL_HIST */
		limpiezaAsignaciones(imagenDTO);
		return generaCambioEstadoLote;
	}

	private void guardarBitacoraImagenRechazada(ImagenesDTO imagenDTO) {
		String valorStValidadaAnterior = "NO EVALUADA";
		if(imagenDTO.getStValidada() != null) {
			valorStValidadaAnterior = imagenDTO.getStValidada().toString();
		}
		
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
				BitacoraConceptosEnum.VALIDACION_DE_BOLETA.getValor(), 
				valorStValidadaAnterior,
				"RECHAZADA",
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
				imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
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
		//System.out.println("	Entrada al método getImagenesInicialesVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		//List<ImagenesDTO> imagenesAsignadas = asignValidacionHDAO.getTodasImagenesAsignadasActivas(validadorActivo);
		Long cantidadImagenesAsignadas = asignValidacionHDAO.getCantidadImagenesAsignadas(validadorActivo);
		List<ImagenesDTO> imagenesDTOEnUI = null;
		
		if (cantidadImagenesAsignadas != 0) {// El validador tiene asignaciones activas.
			imagenesDTOEnUI = asignValidacionHDAO.getTodasImagenesEnUI(validadorActivo);
			if(imagenesDTOEnUI != null && imagenesDTOEnUI.size() > 0) {//Tiene imágenes aún sin validar en la UI
				List<ImagenesVO> imagenesVOEnUI = new ArrayList<>();
				Utils.llenadoDatosListaImagenDTOtoListaImagenVO(imagenesDTOEnUI, imagenesVOEnUI);
				//System.out.println("	Salida del método getImagenesInicialesVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());
				return imagenesVOEnUI;
			}else {//Tiene imágenes asignadas pero ninguna en la UI
				List<ImagenesVO> imagenesRetornar = getProximoGrupoImagenesAsignadasVO(idUsuario);
				asignValidacionHDAO.setImagenesEnUIById(getIdsVO(imagenesRetornar), validadorActivo);
				//System.out.println("	Salida del método getImagenesInicialesVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());
				return imagenesRetornar; 
			}
		}
		//Llegado a este punto, el validador no tiene ninguna asignación
		Long cantidadTotalMaximaAsignar = validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuLimImgAsignacion();
		int cantidadImagenesRetornar = (int)validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuImgMax();

		List<ImagenesDTO> todasLasImagenesNoAsignadas = imagenesHDAO.getTodasLasImagenesDTONoAsignadas(cantidadTotalMaximaAsignar);
		
		int sizeListaImagenesNoAsignadas = todasLasImagenesNoAsignadas.size();
		if(sizeListaImagenesNoAsignadas == 0) {
			/*No hay imágenes para asignar, debo retornar una lista vacía*/
			List<ImagenesVO> listaRetornoVacia = new ArrayList<>();
			//System.out.println("	Salida del método getImagenesInicialesVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());
			return listaRetornoVacia;
		}
		int cantImagenesAsignar = (int) (sizeListaImagenesNoAsignadas >= cantidadTotalMaximaAsignar
				? cantidadTotalMaximaAsignar
				: sizeListaImagenesNoAsignadas);

		List<ImagenesDTO> imagenesParaAsignar = todasLasImagenesNoAsignadas.subList(0, cantImagenesAsignar);//lista de imágenes con las imágenes que se asignarán al validador
		asignValidacionHDAO.asignarImagenesValidador(imagenesParaAsignar, validadorActivo);//Asignar las imágenes al validador
		List<LotesDTO> listaLotesDTO = actualizaEstatusLotesDespuesSeleccion(imagenesParaAsignar);//Actualizar el estado de los lotes donde están las imágenes
		
		for(LotesDTO loteDTO : listaLotesDTO) {
			lotesService.addCambiosLotes(loteDTO, idUsuario);
		}
		
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

		//System.out.println("	Salida del método getImagenesInicialesVO. Usuario: "+idUsuario+" Hilo:"+Thread.currentThread().getId());
		
		return listadoImagenesIniciales;
	}
	
	private List<LotesDTO> actualizaEstatusLotesDespuesSeleccion(List<ImagenesDTO> listaImagenes) {
		
		if(listaImagenes.isEmpty())
			return null;
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
		
		return lotesDTO;
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
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_POSICION_DE_LAS_IMAGENES.getValor(), 
				"REVERSO",
				"ANVERSO",
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				anverso.getIdImagen()==null?"":anverso.getIdImagen().toString(),
				anverso.getIdImagen()==null?"":anverso.getNombreImagen(), 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_POSICION_DE_LAS_IMAGENES.getValor(), 
				"ANVERSO",
				"REVERSO",
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				reverso.getIdImagen()==null?"":reverso.getIdImagen().toString(),
				reverso.getNombreImagen()==null?"":reverso.getNombreImagen(), 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
		Utils.llenadoDatosListaLBImagenDTOtoListaLBImagenVO(listaDTO, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = false)
	public void actualizarImagenesPorLoteCancelado(LotesDTO loteDTO) {
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<LBImagenesVO> getLBImagenesPorIdImagenDTO(Long idImagenDTO) {
		List<LBImagenesVO> listaRetornar = new ArrayList<>();
		
		List<LBImagenesDTO> listaDTO = lBImagenesHDAO.getListaLBImagenDTOPorIdImagen(idImagenDTO);
		Utils.llenadoDatosListaLBImagenDTOtoListaLBImagenVO(listaDTO, listaRetornar);
		
		return listaRetornar;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AsignacionesDatosVO> getAsignacionesActivasData() {
		
		List<Object[]> listaObjetos =asignValidacionHDAO.getAsignacionesActivasData();
		List<AsignacionesDatosVO> asignaciones = new ArrayList<AsignacionesDatosVO>();
		Utils.llenadoDatosListaDatosConsultaObjToListaAsignacionesDatosVO(listaObjetos, asignaciones);
		
		return asignaciones;
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteAsignacionesActivasPorValidador(Long idValidador) {
		List<AsignValidacionDTO> asignaciones = asignValidacionHDAO.getTodasAsignaciones(idValidador);
		for(AsignValidacionDTO asignacion: asignaciones) {
			asignValHistHDAO.addNuevaAsignacionHistoricaByAsignacion(asignacion);
		}
		asignValidacionHDAO.deleteAsignacionesActivasPorValidador(idValidador);
		validadorConfigHDAO.setCantidadImagenesRestantesPorValidar(idValidador, (long) 0);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<VehiculoMarcaVO> getTodasMarcasVehiculos() {
		List<VehiculoMarcaDTO> listaDTO = vehiculoMarcaHDAO.findAll();
		List<VehiculoMarcaVO> listaRetornar = new ArrayList<>();
		Utils.llenadoDatosListaVehiculoMarcaDTOtoListaVehiculoMarcaVO(listaDTO, listaRetornar);
		
		return listaRetornar;

	}

	@Override
	@Transactional(readOnly = true)
	public Boolean tieneAsignacionesElUsuario(Long idUsuario) {
		Boolean retorno = true;
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorActivo = validadoresHDAO.getValidadorByIdValidador(idValidadorActivo);
		List<ImagenesDTO> imagenesAsignadas = asignValidacionHDAO.getTodasImagenesAsignadasActivas(validadorActivo);
		
		if(imagenesAsignadas.isEmpty())
			retorno = false;
		else
			retorno = true;
		
		return retorno;
	}
	
	

	@Override
	public Boolean tieneAsignacionesPospuestasElUsuario(Long idUsuario) {
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		
		Long cantidad = asignValidacionHDAO.getCantImagenesPospuestasByValidador(idValidadorActivo);
		
		return cantidad > 0 ? true : false;
		

	}

	@Override
	@Transactional(readOnly = true)
	public Boolean isImagenAceptable(ImagenesVO imagenMarcar) throws Exception {
		boolean retorno = true;
		
		retorno = getSancionArtId(imagenMarcar.getFhInfraccion(),
				imagenMarcar.getNuArtInfrac(), imagenMarcar.getNuFraccion(), 
				imagenMarcar.getNuInciso() == null ? null : imagenMarcar.getNuInciso(), 
				imagenMarcar.getNuParrafo() == null ? null : imagenMarcar.getNuParrafo());
		
		
		return retorno;
	}
	
	private boolean getSancionArtId(Date fecha, Long articulo, Long fraccion, String inciso, Long parrafo) throws Exception {
		return articulosInfracFinanzasHDAO.getArticuloPorDatos(fecha, articulo, fraccion, inciso, parrafo);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getIdLoteByIdImagen(Long idImagen) {
		LotesDTO loteDTO = imagenesHDAO.getLoteByIdImagen(idImagen);
		return loteDTO.getIdLote();
	}

	/**
	 * 
	 */
	@Override
	@Transactional(readOnly = false)
	public Boolean posponerBoleta(Long idImagen, String causa, Long idUsuario) {
		Boolean retorno = true;
		if(causa == null || causa.length() == 0) {
			causa = "BOLETA POSPUESTA";
		}
		
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(idUsuario).getIdValidador();
		ValidadoresDTO validadorPosp = validadoresHDAO.getValidadorByIdUsuario(idUsuario);
		Long cantidadPospuestas = asignValidacionHDAO.getCantidadBoletasPospuestasByValidador(idValidadorActivo);
		Long cantidadMaximaPosponer = 
				validadorConfigHDAO.getConfiguracionActiva(idValidadorActivo).getNuMaxPospuestas();
		
		if(cantidadPospuestas >= cantidadMaximaPosponer) {
			retorno = false;
		}else {
			AsignValidacionDTO asignacion = asignValidacionHDAO.getAsignacionByIdValidadorAndIdImagen(idValidadorActivo, idImagen);
			asignacion.setStPospuesta((short) 1);
		
			ImagenesDTO imagenDTO = imagenesHDAO.getImagenDTOById(idImagen);
			imagenDTO.setTxPospuesta(causa);
			imagenDTO.setIdValidadorPosp(validadorPosp);
			
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.POSPONER_BOLETA.getValor(), 
					"NO EVALUADA",
					"POSPUESTA",
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			crearEstadisticasImagenes(imagenDTO,validadorPosp,ValoresImagenValidada.POSPUESTA);
			
			asignValidacionHDAO.update(asignacion);
			imagenesHDAO.update(imagenDTO);
		}
		
		return retorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ImagenesVO> getTodasImagenesPospuestasByidUsuario(UsuarioFirmadoVO usuario) {
		String cdPerfil = usuario.getCdPerfil();
		List<ImagenesDTO> listaDTO = new ArrayList<>();
		List<ImagenesVO> listaRetorno = new ArrayList<>();
		Long idValidadorActivo = validadoresHDAO.getValidadorByIdUsuario(usuario.getId()).getIdValidador();
		
		//listaRetorno = asignValidacionHDAO.getTodasImagenesPospuestasSinBlobByValidador(idValidadorActivo);
		listaDTO = asignValidacionHDAO.getTodasImagenesPospuestasByValidador(idValidadorActivo);
		Utils.llenadoDatosListaImagenDTOtoListaImagenVOSinBlob(listaDTO, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ImagenesVO> getTodasImagenesPospuestas() {
		List<ImagenesDTO> listaDTO = new ArrayList<>();
		List<ImagenesVO> listaRetorno = new ArrayList<>();
		
		listaDTO = asignValidacionHDAO.getTodasImagenesPospuestas();
		
		Utils.llenadoDatosListaImagenDTOtoListaImagenVOSinBlob(listaDTO, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ImagenesVO> getImagenesPospuestasParam(Long idValidador, Date fechaInicial, Date fechaFinal) {
		List<ImagenesDTO> listaDTO = new ArrayList<>();
		List<ImagenesVO> listaRetorno = new ArrayList<>();
		
		listaDTO = asignValidacionHDAO.getImagenesPospuestasParam(idValidador, fechaInicial, fechaFinal);
		
		Utils.llenadoDatosListaImagenDTOtoListaImagenVOSinBlob(listaDTO, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean existenAsignacionesPospuestas() {
		return asignValidacionHDAO.existenAsignacionesPospuestas();

	}



	
}