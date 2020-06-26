package mx.com.teclo.digitalizacion.bitacora;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.digitalizacion.negocio.utils.RutinasTiempoImpl;
import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.BitacoraCambiosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboComponentesVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboConceptosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ParametrosBusquedaReporteBitacoraVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora.TdpBitacoraCambiosHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora.TdpBitacoraComponentesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.bitacora.TdpBitacoraConceptosHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraComponentesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraConceptosDTO;
import mx.com.teclo.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclo.generaexcel.vo.PeticioReporteVO;
import mx.com.teclo.generaexcel.vo.PropiedadesReporte;

@Service
public class BitacoraServiceImpl implements BitacoraService{
	
	@Autowired
	private TdpBitacoraComponentesHDAO tdpBitacoraComponentesHDAO;
	@Autowired
	private TdpBitacoraConceptosHDAO tdpBitacoraConceptosHDAO;
	@Autowired
	private TdpBitacoraCambiosHDAO tdpBitacoraCambiosHDAO;

	@Override
	public List<ComboComponentesVO> getComponentesBitacora() {
		List<ComboComponentesVO> listaRetorno = new ArrayList<>();
		List<TdpBitacoraComponentesDTO> listaComponentes = tdpBitacoraComponentesHDAO.findAll();
		Utils.llenadoDatosTdpBitacoraComponentesDTOToComboComponentesVO(listaComponentes, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	public List<ComboConceptosVO> getConceptosBitacora(Long componenteId) {
		List<ComboConceptosVO> listaRetorno = new ArrayList<>();
		List<TdpBitacoraConceptosDTO> listaConceptos = tdpBitacoraConceptosHDAO.getConceptosByIdComponente(componenteId);
		Utils.llenadoDatosTdpBitacoraConceptosDTOToComboConceptosVO(listaConceptos, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	public List<BitacoraCambiosVO> getBitacoraCambios(ParametrosBusquedaReporteBitacoraVO parametrosBusquedaVO) {
		List<BitacoraCambiosVO> listaRetorno = tdpBitacoraCambiosHDAO.getCambiosByParametros(parametrosBusquedaVO);
		

		
		return listaRetorno;
	}

	@Override
	public ByteArrayOutputStream getFicheroExcel(List<BitacoraCambiosVO> listaCambios, String nombreReporte, 
			Date fechaInicio, Date fechaFin) {
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();
		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
		RutinasTiempoImpl rutinasTiempoImpl=new RutinasTiempoImpl();
		
		
		
		//Resultados de la tabla
		List<Object> contenido = new ArrayList<Object>();
		List<Object> contenido1 = new ArrayList<Object>();
				
		//Leyendas de las columnas de las tablas
		List<Object> encabezadoTitulo = new ArrayList<Object>();
		List<String> titulos = new ArrayList<String>(); 
		
		
		titulos.add("COMPONENTE");
		titulos.add("CONCEPTO");
		titulos.add("VALOR ORIGINAL");
		titulos.add("VALOR FINAL");
		titulos.add("REGISTRO");
		titulos.add("MODIFICADO POR");
		titulos.add("REGISTRO MODIFICADO");
		titulos.add("FECHA DE MODIFICACIÓN");
		
		encabezadoTitulo.add(titulos);
		
		propiedadesReporte.setTituloExcel(nombreReporte.replaceAll("_", " "));
		
		if(fechaInicio.equals(fechaFin)){
			propiedadesReporte.setFechaI(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",fechaInicio));
		}else{
			propiedadesReporte.setFechaI(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",fechaInicio));
			propiedadesReporte.setFechaF(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",fechaFin));
		}
		
		propiedadesReporte.setExtencionArchvio(".xls");
		
		//cuerpo del reporte
		List<String> listaContenido1;
		
		for(BitacoraCambiosVO cambio : listaCambios) {
			listaContenido1 = new ArrayList<String>();
			listaContenido1.add(cambio.getNombreComponente());
			listaContenido1.add(cambio.getNombreConcepto());
			listaContenido1.add(cambio.getValorOriginal());
			listaContenido1.add(cambio.getValorFinal());
			listaContenido1.add(cambio.getIdRegistro());
			listaContenido1.add(cambio.getModificadoPor());
			listaContenido1.add(cambio.getRegistroAlterado());
			listaContenido1.add(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy hh:mm:ss",cambio.getFechaModificacion()));
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

	
	
	
}
