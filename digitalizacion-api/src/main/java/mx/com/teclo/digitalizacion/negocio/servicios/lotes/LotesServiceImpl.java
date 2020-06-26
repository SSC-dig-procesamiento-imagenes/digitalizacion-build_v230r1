package mx.com.teclo.digitalizacion.negocio.servicios.lotes;

import java.util.Date;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;
import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresFicheroExcel;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PropiedadesReporte;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora.ImagenesBHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora.LotesBHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.CambiosLotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.StatusLotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesBDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;

@Service
public class LotesServiceImpl implements LotesService {
	
	@Autowired
	private LotesHDAO lotesHDAO;
	@Autowired
	private StatusLotesHDAO statusLotesHDAO;
	@Autowired
	private ImagenesHDAO imagenesHDAO;
	@Autowired
	private CambiosLotesHDAO cambiosLotesHDAO;
	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	@Autowired
	private LotesBHDAO lotesBHDAO;
	@Autowired
	private ImagenesBHDAO imagenesBHDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<LotesResultVO> getInformacionLotes() {
		List<LotesResultVO> listaRetorno = new ArrayList<>();
		List<LotesQuery> lotesQuery = lotesHDAO.getQueryGeneral();
		Utils.llenadoDatosListaLotesQueryToListaLotesResultVO(lotesQuery, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LotesResultVO> getInformacionLotes(Date fechaInicial, Date fechaFinal, Long idEstatus) {
		
		List<LotesResultVO> listaRetorno = new ArrayList<>();
		List<LotesQuery> lotesQuery = lotesHDAO.getQueryAvanzada(fechaInicial, fechaFinal, idEstatus);
		Utils.llenadoDatosListaLotesQueryToListaLotesResultVO(lotesQuery, listaRetorno);
		
		return listaRetorno;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<LotesQuery> getLotesPorIds(List<Long> idsLista, ValoresFicheroExcel valorFicheroExcel) {
		List<LotesQuery> listaRetorno = null;

		if(valorFicheroExcel == ValoresFicheroExcel.LOTES) {
			listaRetorno = lotesHDAO.getLotesParaExcel(idsLista);
		}else if(valorFicheroExcel == ValoresFicheroExcel.LOTESB) {
			listaRetorno = lotesBHDAO.getLotesParaExcel(idsLista);
		}
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = false)
	public LotesResultVO cancelarLote(Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		
		LotesDTO loteDTO = lotesHDAO.findOne(idLote);
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.CONSULTA_DE_LOTES.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES.getValor(), 
				loteDTO.getIdStatProceso()==null?"":loteDTO.getIdStatProceso().getNombreEstatus(),///---
				ValoresEstadosLotes.CANCELADO.getValor(),
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				loteDTO.getIdLote()==null?"":loteDTO.getIdLote().toString(),//---
				loteDTO.getNbLote()==null?"":loteDTO.getNbLote(), //---
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		LotesDTO loteActualizado = lotesHDAO.cancelarLote(loteDTO);


		
		Utils.llenadoDatosLotesDTOtoLotesResultVO(loteActualizado,retorno);
		
		lotesHDAO.flush();
		
		return retorno;
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public LotesResultVO formarParaLiberarLote(Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		
		LotesDTO loteDTO = lotesHDAO.findOne(idLote);
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.CONSULTA_DE_LOTES.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES.getValor(), 
				loteDTO.getIdStatProceso()==null?"":loteDTO.getIdStatProceso().getNombreEstatus(),//---
				ValoresEstadosLotes.FORMADO_PARA_LIBERACION.getValor(),
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				loteDTO.getIdLote()==null?"":loteDTO.getIdLote().toString(),//--
				loteDTO.getNbLote()==null?"":loteDTO.getNbLote(),  //---
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		LotesDTO loteActualizado = lotesHDAO.formarParaLiberarLote(loteDTO);
		Utils.llenadoDatosLotesDTOtoLotesResultVO(loteActualizado,retorno);
		
		lotesHDAO.flush();
		
		return retorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstatusProcesoVO> getTodosEstatus() {
		List<EstatusProcesoDTO> listaStat = statusLotesHDAO.getEstatusPertinentesLiberacion();
		List<EstatusProcesoVO> listaRetorno = new ArrayList<>();
		
		Utils.llenadoDatosListaStatProcesoDTOtoListaEstatusProcesoVO(listaStat, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = false)
	public LotesResultVO enValidandoInformacion(Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		
		LotesDTO loteDTO = lotesHDAO.findOne(idLote);
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.CONSULTA_DE_LOTES.getValor(), 
				BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES.getValor(), 
				loteDTO.getIdStatProceso()==null?"":loteDTO.getIdStatProceso().getNombreEstatus(),///---
				ValoresEstadosLotes.VALIDANDO_INFORMACION.getValor(),
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				loteDTO.getIdLote()==null?"":loteDTO.getIdLote().toString(),//---
				loteDTO.getNbLote()==null?"":loteDTO.getNbLote(), //--- 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
		LotesDTO loteActualizado = lotesHDAO.enValidandoInformacion(loteDTO);
		Utils.llenadoDatosLotesDTOtoLotesResultVO(loteActualizado,retorno);
		
		lotesHDAO.flush();
		
		return retorno;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ImagenesSinBlobVO> getImagenesPorLote(Long idLoteSeleccionado){
		List<ImagenesSinBlobVO> listaRetornar = new ArrayList<>();
		
		List<ImagenesDTO> listaDTO = imagenesHDAO.getImagenesDTOByLote(idLoteSeleccionado);
		Utils.llenadoDatosListaImagenDTOtoListaImagenSinBlobVO(listaDTO, listaRetornar);
		
		return listaRetornar;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ByteArrayOutputStream getFicheroExcel(List<Long> listaIds, String nombreReporte, ValoresFicheroExcel valorFicheroExcel) {
		ByteArrayOutputStream retorno = null;
		List<LotesQuery> listaLotesQuery = this.getLotesPorIds(listaIds, valorFicheroExcel);
		
		if(valorFicheroExcel == ValoresFicheroExcel.LOTES) {
			retorno = generarReporte(listaLotesQuery, nombreReporte);
		}else if(valorFicheroExcel == ValoresFicheroExcel.LOTESB) {
			retorno = generarReporteB(listaLotesQuery, nombreReporte);
		}
		

				
		return retorno;
	}
	
	private ByteArrayOutputStream generarReporteB(List<LotesQuery> listaLotes, String nombreReporte) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticionReporteVO peticionReporteVO = new PeticionReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		List<PeticionReporteVO> listaPeticionReporteVO = new ArrayList<>();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("NÚMERO DE LOTE");
		titulos.add("NOMBRE DE LOTE");
		titulos.add("FECHA DE LOTE");
		titulos.add("TOTAL DE IMÁGENES");
		titulos.add("CANTIDAD DE IMÁGENES ACEPTADAS");
		titulos.add("CANTIDAD DE IMÁGENES RECHAZADAS");
		titulos.add("ESTATUS");
		titulos.add("FOLIO INICIAL");
		titulos.add("FOLIO FINAL");

		encabezadoTitulo.add(titulos);
		
		propiedadesReporte.setTituloExcel(nombreReporte.replaceAll("_", " "));
		propiedadesReporte.setExtencionArchvio(".xls");
		
		List<String> listaContenido1;
		
		for(LotesQuery lotesQuery : listaLotes) {
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(lotesQuery.getNumeroLote() == null ? "" : lotesQuery.getNumeroLote().toString());
			listaContenido1.add(lotesQuery.getNombreLote());
			listaContenido1.add(this.getStringDateFormated("dd/MM/yyyy", lotesQuery.getFechaLote()));
			listaContenido1.add(lotesQuery.getTotalImagenes() == null ? "" : lotesQuery.getTotalImagenes().toString());
			listaContenido1.add(lotesQuery.getCantImagenesAceptadas() == null ? "" : lotesQuery.getCantImagenesAceptadas().toString());
			listaContenido1.add(lotesQuery.getCantImagenesRechazadas() == null ? "" : lotesQuery.getCantImagenesRechazadas().toString());
			listaContenido1.add(lotesQuery.getNombreEstatus());
			listaContenido1.add(lotesQuery.getNuFolioInicial() == null ? "" :lotesQuery.getNuFolioInicial().toString());
			listaContenido1.add(lotesQuery.getNuFolioFinal() == null ? "" :lotesQuery.getNuFolioFinal().toString());
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		/*Formación de las páginas de detalles*/
		listaPeticionReporteVO = getListaPeticionReporteBVO(listaLotes, nombreReporte);
		/*Fin de la formación de las páginas de detalles*/
		
		try {
			reporte = peticionReporteBOImpl.plopezGeneraReporteExcel(peticionReporteVO,listaPeticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return reporte;
		
	}

	private List<PeticionReporteVO> getListaPeticionReporteBVO(List<LotesQuery> listaLotes, String nombreReporte) {
		List<PeticionReporteVO> listaRetorno = new ArrayList<>();
		
		for(LotesQuery lotesQuery : listaLotes) {
			PeticionReporteVO peticionReporteVO = new PeticionReporteVO();
			PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
			Long idLote = lotesQuery.getNumeroLote();
			List <ImagenesBDTO> listaImagenes = imagenesBHDAO.getImagenesBDTOByLote(idLote);
			List<Object> contenido = new ArrayList<Object>();
			List<Object> contenido1 = new ArrayList<Object>();
			
			//Leyendas de las columnas de las tablas
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			List<String> titulos = new ArrayList<String>(); 
			
			titulos.add("NÚMERO DE IMAGEN");
			titulos.add("ESTATUS");
			titulos.add("NÚMERO DE FOLIO");
			titulos.add("NOMBRE DE BOLETA");
			titulos.add("PLACA VEHICULAR");
			titulos.add("PLACA DEL OFICIAL");
			titulos.add("UNIDAD TERRITORIAL");
			titulos.add("FECHA DE BOLETA");
			titulos.add("ARTÍCULO");
			titulos.add("FRACCIÓN");
			titulos.add("INCISO");
			titulos.add("PÁRRAFO");
			

			encabezadoTitulo.add(titulos);
			String nuevoNombre = "Imágenes del Lote "+lotesQuery.getNumeroLote();
			propiedadesReporte.setTituloExcel(nuevoNombre.replaceAll("_", " "));
			propiedadesReporte.setExtencionArchvio(".xls");
			
			List<String> listaContenido1;
			
			
			for(ImagenesBDTO imagen : listaImagenes) {
				String validada="";
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(imagen.getIdImagen().toString());
				
				Short val = imagen.getStValidada();
				if(val != null ) {
					if(val.equals((short)1)) {
						validada = "ACEPTADA";
					}else {
						validada = "RECHAZADA";
					}
				}else {
					validada = "NO EVALUADA";
				}
				
				listaContenido1.add(validada);
				listaContenido1.add(imagen.getNuNumeroFolio() == null ? "" : imagen.getNuNumeroFolio().toString());
				listaContenido1.add(imagen.getNombreImagen());
				listaContenido1.add(imagen.getCodigoPlaca());
				listaContenido1.add(imagen.getCodigoPlacaOficial());
				listaContenido1.add(imagen.getNbUtDelegacion());
				if(imagen.getFhInfraccion() != null) {
					listaContenido1.add(this.getStringDateFormated("dd/MM/yyyy",imagen.getFhInfraccion()));
				}else {
					listaContenido1.add("");
				}
					
				
				listaContenido1.add(imagen.getNuArtInfrac() == null ? "" : imagen.getNuArtInfrac().toString());
				listaContenido1.add(imagen.getNuFraccion() == null ? "" : imagen.getNuFraccion().toString());
				listaContenido1.add(imagen.getNuInciso() == null ? "" : imagen.getNuInciso().toString());
				listaContenido1.add(imagen.getNuParrafo() == null ? "" : imagen.getNuParrafo().toString());
				
				contenido1.add(listaContenido1);
			}
			contenido.add(contenido1);
			
			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
			peticionReporteVO.setEncabezado(encabezadoTitulo);
			peticionReporteVO.setContenido(contenido);
			
			listaRetorno.add(peticionReporteVO);
			
		}
		
		return listaRetorno;

	}

	private ByteArrayOutputStream generarReporte(List<LotesQuery> listaLotes, String nombreReporte) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticionReporteVO peticionReporteVO = new PeticionReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		List<PeticionReporteVO> listaPeticionReporteVO = new ArrayList<>();
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("NÚMERO DE LOTE");
		titulos.add("NOMBRE DE LOTE");
		titulos.add("FECHA DE LOTE");
		titulos.add("TOTAL DE IMÁGENES");
		titulos.add("CANTIDAD DE IMÁGENES ACEPTADAS");
		titulos.add("CANTIDAD DE IMÁGENES RECHAZADAS");
		titulos.add("ESTATUS");
		titulos.add("FOLIO INICIAL");
		titulos.add("FOLIO FINAL");

		encabezadoTitulo.add(titulos);
		
		propiedadesReporte.setTituloExcel(nombreReporte.replaceAll("_", " "));
		propiedadesReporte.setExtencionArchvio(".xls");
		
		List<String> listaContenido1;
		
		for(LotesQuery lotesQuery : listaLotes) {
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(lotesQuery.getNumeroLote().toString());
			listaContenido1.add(lotesQuery.getNombreLote());
			if(lotesQuery.getFechaLote() != null) {
				listaContenido1.add(this.getStringDateFormated("dd/MM/yyyy", lotesQuery.getFechaLote()));
			}else {
				listaContenido1.add("");
			}
			
			listaContenido1.add(lotesQuery.getTotalImagenes() == null ? "" : lotesQuery.getTotalImagenes().toString());
			listaContenido1.add(lotesQuery.getCantImagenesAceptadas() == null ? "" : lotesQuery.getCantImagenesAceptadas().toString());
			listaContenido1.add(lotesQuery.getCantImagenesRechazadas() == null ? "" : lotesQuery.getCantImagenesRechazadas().toString());
			listaContenido1.add(lotesQuery.getNombreEstatus());
			listaContenido1.add(lotesQuery.getNuFolioInicial() == null ? "" : lotesQuery.getNuFolioInicial().toString());
			listaContenido1.add(lotesQuery.getNuFolioFinal() == null ? "" : lotesQuery.getNuFolioFinal().toString());
			
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		/*Formación de las páginas de detalles*/
		listaPeticionReporteVO = getListaPeticionReporteVO(listaLotes, nombreReporte);
		/*Fin de la formación de las páginas de detalles*/
		
		try {
			reporte = peticionReporteBOImpl.plopezGeneraReporteExcel(peticionReporteVO,listaPeticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return reporte;
	}
	
	private List<PeticionReporteVO> getListaPeticionReporteVO(List<LotesQuery> listaLotes, String nombreReporte){
		List<PeticionReporteVO> listaRetorno = new ArrayList<>();
		
		for(LotesQuery lotesQuery : listaLotes) {
			PeticionReporteVO peticionReporteVO = new PeticionReporteVO();
			PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
			

			Long idLote = lotesQuery.getNumeroLote();
			List <ImagenesDTO> listaImagenes = imagenesHDAO.getImagenesDTOByLote(idLote);
			List<Object> contenido = new ArrayList<Object>();
			List<Object> contenido1 = new ArrayList<Object>();
			
			//Leyendas de las columnas de las tablas
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			List<String> titulos = new ArrayList<String>(); 
			
			titulos.add("NÚMERO DE IMAGEN");
			titulos.add("ESTATUS");
			titulos.add("NÚMERO DE FOLIO");
			titulos.add("NOMBRE DE BOLETA");
			titulos.add("PLACA VEHICULAR");
			titulos.add("PLACA DEL OFICIAL");
			titulos.add("UNIDAD TERRITORIAL");
			titulos.add("FECHA DE BOLETA");
			titulos.add("ARTÍCULO");
			titulos.add("FRACCIÓN");
			titulos.add("INCISO");
			titulos.add("PÁRRAFO");
			titulos.add("POSPOSICIÓN");
			titulos.add("CANCELACIÓN");
			
			encabezadoTitulo.add(titulos);
			String nuevoNombre = "Imágenes del Lote "+lotesQuery.getNumeroLote();
			propiedadesReporte.setTituloExcel(nuevoNombre.replaceAll("_", " "));
			propiedadesReporte.setExtencionArchvio(".xls");
			
			List<String> listaContenido1;
			
			for(ImagenesDTO imagen : listaImagenes) {
				String validada="";
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(imagen.getIdImagen().toString());
				
				Short val = imagen.getStValidada();
				if(val != null ) {
					if(val.equals((short)1)) {
						validada = "ACEPTADA";
					}else {
						validada = "RECHAZADA";
					}
				}else {
					validada = "NO EVALUADA";
				}
				
				listaContenido1.add(validada);
				listaContenido1.add(imagen.getNuNumeroFolio().toString());
				listaContenido1.add(imagen.getNombreImagen());
				listaContenido1.add(imagen.getCodigoPlaca());
				listaContenido1.add(imagen.getCodigoPlacaOficial());
				listaContenido1.add(imagen.getNbUtDelegacion());
				if(imagen.getFhInfraccion() != null) {
					listaContenido1.add(this.getStringDateFormated("dd/MM/yyyy",imagen.getFhInfraccion()));
				}else {
					listaContenido1.add("");
				}
				
				listaContenido1.add(imagen.getNuArtInfrac() == null ? "" : imagen.getNuArtInfrac().toString());
				listaContenido1.add(imagen.getNuFraccion() == null ? "" : imagen.getNuFraccion().toString());
				listaContenido1.add(imagen.getNuInciso() == null ? "" : imagen.getNuInciso().toString());
				listaContenido1.add(imagen.getNuParrafo() == null ? "" : imagen.getNuParrafo().toString());
				listaContenido1.add(imagen.getTxPospuesta());
				listaContenido1.add(imagen.getTxRechazada());
				
				contenido1.add(listaContenido1);
			}
			contenido.add(contenido1);
			
			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
			peticionReporteVO.setEncabezado(encabezadoTitulo);
			peticionReporteVO.setContenido(contenido);
			
			listaRetorno.add(peticionReporteVO);
			
		}
		
		return listaRetorno;
	}
	
	private String getStringDateFormated(String format,Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}

	@Override
	@Transactional(readOnly = false)
	public void addCambiosLotes(LotesDTO lotesDTO, Long idUsuario) {
		cambiosLotesHDAO.addCambiosLotes(lotesDTO, idUsuario);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void addCambiosLotes(Long idLote, Long idUsuario) {
		LotesDTO lotesDTO = lotesHDAO.findOne(idLote);
		addCambiosLotes(lotesDTO,idUsuario);
		
	}
	
}
