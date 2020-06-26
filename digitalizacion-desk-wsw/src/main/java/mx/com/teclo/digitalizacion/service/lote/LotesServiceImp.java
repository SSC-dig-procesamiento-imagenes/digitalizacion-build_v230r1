package mx.com.teclo.digitalizacion.service.lote;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.configuracionescaner.ConfiguracionEscanerDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.estatusproceso.ProcesoEstatusDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.CambiosLotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesBDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.estatusproceso.EstatusProcesoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesBDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.vo.estatusproceso.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.ActualizarFoliosVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.ActualizarLoteVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.LoteVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.PersistirLoteLoteVO;
import mx.com.teclo.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclo.generaexcel.vo.PeticioReporteVO;
import mx.com.teclo.generaexcel.vo.PropiedadesReporte;

@Service
public class LotesServiceImp implements LotesService {
	
	@Autowired
	private LotesBDAO lotesBDAO;

	@Autowired
	private LotesDAO lotesDAO;
	
	@Autowired
	private ConfiguracionEscanerDAO configuracionEscanerDAO;
	
	@Autowired
	private ProcesoEstatusDAO procesoEstatusDAO;
	
	@Autowired
	private ImagenesDAO imagenesDAO;
	
	@Autowired
	private CambiosLotesHDAO cambiosLotesHDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Transactional(readOnly = true)
	public List<LoteVO> getLotesPorParametros(Long idstat,String fechaIni,String fechaFin){
		List<LoteVO> lotes = new ArrayList<>();
		List<LotesDTO> listalotesDTO = new ArrayList<LotesDTO>();
		listalotesDTO=lotesDAO.getLotesPorParametros(idstat, fechaIni, fechaFin);
		
		lotes = ResponseConverter.converterLista(new ArrayList<>(), listalotesDTO, LoteVO.class);
		
		return lotes;
	}
	
	@Transactional(readOnly = true)
	public List<LoteVO> getLotesAll(){
		List<LoteVO> lotes = new ArrayList<>();
		List<LotesDTO> lotesDTO =  lotesDAO.findAll();
		
		if(!lotesDTO.isEmpty()) {
			lotes = ResponseConverter.converterLista(new ArrayList<>(), lotesDTO, LoteVO.class);
		}
		

		return lotes;
	}

	@Transactional
	public LoteVO guadarLote(PersistirLoteLoteVO persistirLoteLoteVO, Long idUsuario){
		LoteVO loteVO = new LoteVO();
		LotesDTO lotesDTO = new LotesDTO();
		
		
		lotesDTO = ResponseConverter.copiarPropiedadesFull(persistirLoteLoteVO, LotesDTO.class);
		
		lotesDTO.setEstatusProceso(procesoEstatusDAO.findOne(persistirLoteLoteVO.getIdEtatusProceso()));
		lotesDTO.setConfiguracionEscaner(configuracionEscanerDAO.findOne(persistirLoteLoteVO.getIdConfiguracionEscaner()));
		
		lotesDTO = lotesDAO.saveOrUpdate(lotesDTO);
		cambiosLotesHDAO.addCambiosLotes(lotesDTO, idUsuario);
		
		loteVO = ResponseConverter.copiarPropiedadesFull(lotesDTO, LoteVO.class);
		
		lotesDAO.flush();

		return loteVO;
	}
	
	@Transactional
	public void actualizarLote(ActualizarLoteVO actualizarLoteVO, Long idUsuario) {
		LotesDTO lotesDTO = null;
		
		lotesDTO = lotesDAO.findOne(actualizarLoteVO.getIdLote());
		
		lotesDTO.setEstatusProceso(procesoEstatusDAO.findOne(actualizarLoteVO.getIdEtatusProceso()));
		lotesDTO.setNumeroTotalImagenes(actualizarLoteVO.getNumeroTotalImagenes());
		if(actualizarLoteVO.getNuFolioInicial() != null && actualizarLoteVO.getNuFolioFinal()!= null) {
			lotesDTO.setNuFolioInicial(actualizarLoteVO.getNuFolioInicial());
			lotesDTO.setNuFolioFinal(actualizarLoteVO.getNuFolioFinal());
		}
		if(actualizarLoteVO.getNombreLote() != null) {
			lotesDTO.setNombreLote(actualizarLoteVO.getNombreLote());
		}
		
		if(actualizarLoteVO.getIdUsuario() != null) {
			lotesDTO.setIdUsuario(actualizarLoteVO.getIdUsuario());
		}
		
		lotesDAO.update(lotesDTO);
		
		cambiosLotesHDAO.addCambiosLotes(lotesDTO, idUsuario);
		
		if(Objects.equals(actualizarLoteVO.getIdEtatusProceso(), Long.valueOf(7))) {
			/*Guardado del lote original en la tabla de bitácora de lotes*/
			LotesBDTO lotesBDTO = new LotesBDTO();
			lotesBDTO = ResponseConverter.copiarPropiedadesFull(lotesDTO, LotesBDTO.class);
			lotesBDTO.setEstatusProceso(procesoEstatusDAO.findOne(actualizarLoteVO.getIdEtatusProceso()));
			lotesBDTO = lotesBDAO.saveOrUpdate(lotesBDTO);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstatusProcesoVO> getTodosEstatus() {
		List<EstatusProcesoVO> listaRetorno = new ArrayList<>();
		List<EstatusProcesoDTO> listaDTO = procesoEstatusDAO.findAll();
		
		if(!listaDTO.isEmpty())
			listaRetorno = ResponseConverter.converterLista(new ArrayList<>(), listaDTO, EstatusProcesoVO.class);
		
		return listaRetorno;
	}
	
	@Transactional(readOnly = true)
	public List<EstatusProcesoVO> getTodosEstatusDigitalizacion(){
		List<EstatusProcesoVO> listaRetorno = new ArrayList<>();
		List<EstatusProcesoDTO> listaDTO = new ArrayList<EstatusProcesoDTO>();
		
		listaDTO=procesoEstatusDAO.getEstatusDigitalizacion();
		
		if(!listaDTO.isEmpty())
			listaRetorno = ResponseConverter.converterLista(new ArrayList<>(), listaDTO, EstatusProcesoVO.class);
		
		return listaRetorno;
	}
	
	@Override
	@Transactional(readOnly = true)
	public ByteArrayOutputStream getBoletasPorLoteExcel(Long idLote, String nombreReporte) {
		LotesDTO lotesDTO = this.lotesDAO.findOne(idLote);
		
		if(lotesDTO == null) {
			System.out.println("El lote seleccionado es incorrecto");
			return null;
		}
		
		List<ImagenesDTO> listaImagenes = this.imagenesDAO.getImagenesByLote(lotesDTO);
		ByteArrayOutputStream retorno = generarReporte(listaImagenes, nombreReporte);
				
		return retorno;
	}
	
	private ByteArrayOutputStream generarReporte(List<ImagenesDTO> listaImagenes, String nombreReporte) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		
		
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
		
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		titulos.add("NÚMERO DE FOLIO");
		titulos.add("PLACA VEHICULAR");
		titulos.add("NÚMERO DE LICENCIA");
		titulos.add("TIPO DE LICENCIA");
		titulos.add("LICENCIA EXPEDIDA EN");
		titulos.add("PLACA EXPEDIDA EN");
		titulos.add("ARTÍCULO");
		titulos.add("FRACCIÓN");
		titulos.add("INCISO");
		titulos.add("PÁRRAFO");
		titulos.add("UNIDAD TERRITORIAL");
		titulos.add("FECHA");
		titulos.add("PLACA DEL OFICIAL");

		encabezadoTitulo.add(titulos);
		
		propiedadesReporte.setTituloExcel(nombreReporte.replaceAll("_", " "));
		propiedadesReporte.setExtencionArchvio(".xls");
		
		List<String> listaContenido1;
		
		for(ImagenesDTO imagen : listaImagenes) {
			listaContenido1 = new ArrayList<String>();
			
			listaContenido1.add(imagen.getNumeroFolio().toString());
			listaContenido1.add(imagen.getPlaca());
			listaContenido1.add(imagen.getNumeroLicencia());
			listaContenido1.add(imagen.getTipoLicencia());
			listaContenido1.add(imagen.getLicenciaExpedidaEn());
			listaContenido1.add(imagen.getPlacaExpedidaEn());
			listaContenido1.add(imagen.getNumeroArticuloInfraccion().toString());
			listaContenido1.add(imagen.getNumeroFraccion().toString());
			listaContenido1.add(imagen.getNumeroInciso());
			listaContenido1.add(imagen.getNumeroParrafo().toString());
			listaContenido1.add(imagen.getUtDelegacion());
			listaContenido1.add(imagen.getPlaca());			
			listaContenido1.add(this.getStringDateFormated("dd/MM/yyyy", imagen.getFechaInfraccion()));
			listaContenido1.add(imagen.getPlacaOficial());
			
			contenido1.add(listaContenido1);
		}
		
		contenido.add(contenido1);
		
		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(contenido);
		
		try {
			reporte = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		return reporte;
	}
	
	private String getStringDateFormated(String format,Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(fecha);
	}

	@Override
	public void actualizarFolios(ActualizarFoliosVO actualizarFoliosVO, Long modificadoPor) {
		LotesDTO lotesDTO = lotesDAO.findOne(actualizarFoliosVO.getIdLote());
		
		lotesDTO.setNuFolioInicial(actualizarFoliosVO.getNuFolioInicial());
		lotesDTO.setNuFolioFinal(actualizarFoliosVO.getNuFolioFinal());
		lotesDAO.saveOrUpdate(lotesDTO);
		
	}
	
	
}
