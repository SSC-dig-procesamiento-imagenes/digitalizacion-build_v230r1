package mx.com.teclo.digitalizacion.negocio.servicios.lotes;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;
import mx.com.teclo.digitalizacion.negocio.utils.PosicionImagen;
import mx.com.teclo.digitalizacion.negocio.utils.UsuarioLiberador;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ArticulosHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ArticulosInfracFinanzasHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.DirectoriosDigitalizacionDiaDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.InfraccionesDigitalizacionHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.CambiosLotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.DigitalizacionIdDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.DirDigitalizacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.InfraccionesDigitalizacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

@Service
public class LiberacionLotesServiceImpl implements LiberacionLotesService {
	private static final Logger logger = Logger.getLogger(LiberacionLotesServiceImpl.class);
	
	@Autowired
	private LotesHDAO lotesHDAO;
	@Autowired
	private ImagenesHDAO imagenesHDAO;
	@Autowired
	private DirectoriosDigitalizacionDiaDAO directoriosDigitalizacionDiaDAO;
	@Autowired
	private ArticulosInfracFinanzasHDAO articulosInfracFinanzasHDAO; 
	@Autowired
	private InfraccionesDigitalizacionHDAO infraccionesDigitalizacionHDAO;
//	@Autowired
//	private InfraccionesImagenesHDAO infraccionesImagenesHDAO;
	@Autowired
	private ArticulosHDAO articulosHDAO;
	@Autowired
	private CambiosLotesHDAO cambiosLotesHDAO;
	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;


	@Transactional(readOnly = false)
	private void setEstadoLote(LotesDTO lote, ValoresEstadosLotes estado) {
		lotesHDAO.setEstatus(lote, estado);
		LotesDTO loteNuevo = lotesHDAO.update(lote);
		lotesHDAO.flush();
		cambiosLotesHDAO.addCambiosLotes(loteNuevo, null);
	}
	
	
	
	@Override
	@Transactional(readOnly = false)
	public boolean setEstado() {
		List<LotesDTO> listaVerificar = lotesHDAO.getLotesPorEstado(ValoresEstadosLotes.EN_LIBERACION);
		if(listaVerificar.size() != 0) {
			logger.info("Existe un lote en liberación. Volvemos a esperar");
			return false;
		}
		
		List<LotesDTO> lotesFormados = lotesHDAO.getLotesPorEstado(ValoresEstadosLotes.FORMADO_PARA_LIBERACION);
		if(lotesFormados.size() == 0) {
			logger.info("No hay lotes para liberar. Esperamos otro minuto");
			return false;
		}
		
		LotesDTO lote = lotesFormados.get(0);
		
		logger.info("Lote en liberación");
		this.guadarBitacoraCambioEstadoLote(lote, ValoresEstadosLotes.EN_LIBERACION);
		lotesHDAO.setEstatus(lote, ValoresEstadosLotes.EN_LIBERACION);
		LotesDTO loteNuevo = lotesHDAO.update(lote);
		lotesHDAO.flush();
		cambiosLotesHDAO.addCambiosLotes(loteNuevo, UsuarioLiberador.LIBERADOR.getValor());
		return true;
	}



	@Override
	@Transactional(readOnly = false)
	public void liberaUnLoteDisponible() throws Exception {
		logger.info("Inicia proceso de liberación de un lote.");
		
		List<LotesDTO> listaVerificar = lotesHDAO.getLotesPorEstado(ValoresEstadosLotes.EN_LIBERACION);
		LotesDTO lote = listaVerificar.get(0);
			
			try {		
				List<ImagenesDTO> listaImagenes = liberarImagenesLote(lote);/*Hace que cada imagen del lote tenga fecha de liberación y */
				copiarDatosTablaExterna(listaImagenes);/*Copia en la tabla externa*/
				guadarBitacoraCambioEstadoLote(lote, ValoresEstadosLotes.LIBERADO);
				setEstadoLote(lote,ValoresEstadosLotes.LIBERADO);
				
				logger.info("Lote liberado");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
			
		logger.info("Termina proceso de liberación del lote.");
		
	}

	
	private void guadarBitacoraCambioEstadoLote(LotesDTO lote, ValoresEstadosLotes nuevoEstado) {
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.IMAGENES_CONSULTA_DE_LOTES.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES.getValor(), 
				lote.getIdStatProceso()==null?"":lote.getIdStatProceso().getNombreEstatus(),
				nuevoEstado.getValor()==null?"":nuevoEstado.getValor(),
				UsuarioLiberador.LIBERADOR.getValor(),
				lote.getIdLote()==null?"":lote.getIdLote().toString(),
				lote.getNbLote()==null?"":lote.getNbLote(), 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
	}



	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
	public void liberaLotesDisponibles() throws Exception {
		logger.info("Inicia proceso de liberación de lotes.");
		List<LotesDTO> listaVerificar = lotesHDAO.getLotesPorEstado(ValoresEstadosLotes.EN_LIBERACION);
		if(listaVerificar.size() != 0) {
			throw new Exception("La BD está corrupta, solo puede existir a lo sumo un lote EN_LIBERACION");
		}
		
		List<LotesDTO> lotesFormados = lotesHDAO.getLotesPorEstado(ValoresEstadosLotes.FORMADO_PARA_LIBERACION);
		if(lotesFormados.size() == 0) {
			logger.info("No hay lotes para liberar. Esperamos otro minuto");
			return;
		} else {/*Existen lotes que pueden someterse al proceso de liberación*/
			for(LotesDTO lote : lotesFormados) {/*Para cada lote*/
				try {
					//lotesHDAO.ponerEnLiberacion(lote);//Pone el lote en liberación. Realiza un flush en la BD
					guadarBitacoraCambioEstadoLote(lote,ValoresEstadosLotes.EN_LIBERACION);
					lotesHDAO.setEstatus(lote, ValoresEstadosLotes.EN_LIBERACION);
					
					List<ImagenesDTO> listaImagenes = liberarImagenesLote(lote);/*Hace que cada imagen del lote tenga fecha de liberación y */
					copiarDatosTablaExterna(listaImagenes);/*Copia en la tabla externa*/
					
					lotesHDAO.setEstatus(lote, ValoresEstadosLotes.LIBERADO);
					lotesHDAO.setEstatus(lote, ValoresEstadosLotes.LIBERADO);
					lotesHDAO.update(lote);
					logger.info("Actualizado el lote "+lote.getNbLote());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
			}
		}
		
		logger.info("Terminado proceso de liberación de lotes.");

		
	}
	
	private void copiarDatosTablaExterna(List<ImagenesDTO> listaImagenes) throws Exception {
		
		for(ImagenesDTO imagen : listaImagenes) {
			Boolean existeBoleta = infraccionesDigitalizacionHDAO.existeBoleta(imagen.getNuNumeroFolio());
			if(existeBoleta) {//Debe marcarse la boleta como duplicada y no se pasará a la tabla infracciones_digitalizacion
				imagen.setStDuplicada((short) 1);
				imagenesHDAO.update(imagen);
				logger.info("Se detectó una imagen duplicada: "+imagen.getIdImagen());
			}else {
				InfraccionesDigitalizacionDTO nuevaInfracion = new InfraccionesDigitalizacionDTO();
				logger.info("Copiando en la tabla externa: "+imagen.getNuNumeroFolio());
				llenadoDatosImagenDTOtoInfraccionesDigitalizacion(imagen,nuevaInfracion);
				infraccionesDigitalizacionHDAO.save(nuevaInfracion);
				logger.info("Terminado de copiar en la tabla externa: "+imagen.getNuNumeroFolio());
			}
		}
	}
	
	private List<ImagenesDTO> liberarImagenesLote(LotesDTO lote) {
		logger.info("Liberando imágenes del lote...");
		List<ImagenesDTO> listaRetorno  = imagenesHDAO.liberarImagenesPorLote(lote);
		for(ImagenesDTO imagenDTO : listaRetorno) {
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.IMAGENES_VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_DE_VALORES_DE_BOLETA.getValor(), 
					"ACEPTADA",
					"LIBERADA",
					UsuarioLiberador.LIBERADOR.getValor(),
					imagenDTO.getIdImagen()==null?"":imagenDTO.getIdImagen().toString(),
					imagenDTO.getNombreImagen()==null?"":imagenDTO.getNombreImagen(), 
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		}
		return listaRetorno;
	}
	
	private void llenadoDatosImagenDTOtoInfraccionesDigitalizacion(ImagenesDTO origen, InfraccionesDigitalizacionDTO destino) throws Exception {
		
		if(origen.getVehiculoMarca() == null) {
			destino.setvMarId((long) 99);
		}else {
			destino.setvMarId(origen.getVehiculoMarca().getIdMarca());
		}
		destino.setInfracPagada("N");
		destino.setInfracNum(origen.getNuNumeroFolio().toString());
		destino.setInfracImpresa(origen.getNuNumeroFolio().toString());
		destino.setInfracNumCtrl(origen.getNuNumeroFolio().toString() + origen.getCodigoPlaca());
		destino.setSecId((long) 99);
		destino.setUtId((long) 99);
		
		if(origen.getCodigoPlaca() == null || origen.getCodigoPlaca().length() == 0 ) {
			destino.setInfracConPlaca("N");
		}else {
			destino.setInfracConPlaca("S");
		}
		
		if(origen.getNbPlacaExpEn() == null || origen.getNbPlacaExpEn().length() == 0) {
			destino.setInfracPlacaEdo((long) 99);
		}else {
			try {
				Long val = new Long(origen.getNbPlacaExpEn());
				destino.setInfracPlacaEdo(val);
			}catch(NumberFormatException ex) {
				destino.setInfracPlacaEdo((long) 99);
			}
		}
		
		destino.setInfracPlaca(origen.getCodigoPlaca());
		destino.setInfracLicencia(origen.getNumeroLicencia());
		
		Long tipoLId = (long) 99;
		if(origen.getCodigoTipo()!=null) {
			switch(origen.getCodigoTipo()) {
			case "A": 
				tipoLId = (long) 1;
				break;
			case "B": 
				tipoLId = (long) 2;
				break;
			case "C": 
				tipoLId = (long) 3;
				break;
			case "D": 
				tipoLId = (long) 4;
				break;
			case "E": 
				tipoLId = (long) 5;
				break;
			default:
				tipoLId = (long) 99;
				break;
			}
		}
			
		
		destino.setTipoLId(tipoLId);
		destino.setFechaCreacion(new Date());
		destino.setInfracMFechaHora(origen.getFhInfraccion());
		destino.setEmpId(origen.getCodigoPlacaOficial());
                destino.setArtId(origen.getNuArtInfrac());
		
		Long articulosInfracFinanzasDTO = getSancionArtId(origen.getFhInfraccion(),
				origen.getNuArtInfrac(), origen.getNuFraccion(), origen.getNuInciso() == null ? null : origen.getNuInciso(), 
						origen.getNuParrafo() == null ? null : origen.getNuParrafo());
		
		Long sancionArtId = articulosInfracFinanzasDTO;//Con este sancionArtId busco en la tabla "ARTICULOS" art_motivacion
		destino.setArtId(sancionArtId);
		destino.setSancionArtId(sancionArtId);
		String artMotivacion = buscaArtMotivacion(sancionArtId);
		destino.setArtMotivacion(artMotivacion);
		logger.info("Iniciado guardado de los blobs de la imagen: "+origen.getNuNumeroFolio());
		salvaBlobs(origen);
		logger.info("Terminado guardado de los blobs de la imagen: "+origen.getNuNumeroFolio());
	}


	private Long getSancionArtId(Date fecha, Long articulo, Long fraccion, String inciso, Long parrafo) throws Exception {
		return articulosInfracFinanzasHDAO.getArticuloPorDatos(fecha, articulo, fraccion, inciso, parrafo);
		
	
	}
	
	private String buscaArtMotivacion(Long sancionArtId) {
		return articulosHDAO.findOne(sancionArtId).getArtMotivacion();
		
	}
	
	private void salvaBlobs(ImagenesDTO imagen) throws Exception {
		DigitalizacionIdDTO id=new DigitalizacionIdDTO();
		DigitalizacionIdDTO id2=new DigitalizacionIdDTO();
		DirDigitalizacionDTO img0 = new DirDigitalizacionDTO();
		DirDigitalizacionDTO img1 = new DirDigitalizacionDTO();
		
		for(LBImagenesDTO blb : imagen.getLbImagenesCollection()) {
			String ruta="E:\\IMAGENES_DIA_DIA\\"+imagen.getNuNumeroFolio().toString().substring(0, 4)+"\\I"+blb.getCodigoImagen()+
					String.format("%029d",imagen.getNuNumeroFolio())+".jpg";
			
			if(blb.getCodigoImagen().equals(PosicionImagen.ANVERSO.getValor())){
				img0.setFoto(blb.getLbImagen());
				img0.setRutaArchivo(ruta);
				id.setAnvrev(blb.getCodigoImagen());
//				img0.setAnvRev(blb.getCodigoImagen());
				
			}else {
				img1.setFoto(blb.getLbImagen());
				img1.setRutaArchivo(ruta);
				id2.setAnvrev(blb.getCodigoImagen());
//				img1.setAnvRev(blb.getCodigoImagen());
			
			}
		}
	
		id.setFolio(imagen.getNuNumeroFolio().toString());
		id2.setFolio(imagen.getNuNumeroFolio().toString());
		
		img0.setIdCopuesto(id);
		img1.setIdCopuesto(id2);
		
//		img0.setFolio(imagen.getNuNumeroFolio().toString());
//		img1.setFolio(imagen.getNuNumeroFolio().toString());
		try {
			logger.info("Guardando anverso: "+imagen.getNuNumeroFolio());
			directoriosDigitalizacionDiaDAO.save(img0);
			logger.info("Terminado de guardar anverso: "+imagen.getNuNumeroFolio());
			logger.info("Guardando reverso: "+imagen.getNuNumeroFolio());
			directoriosDigitalizacionDiaDAO.save(img1);
			logger.info("Terminado de guardar anverso: "+imagen.getNuNumeroFolio());
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("La persistencia de los blobs se canceló debido a que: "+e.getLocalizedMessage());
		}
		
		
	}
	
	
}
