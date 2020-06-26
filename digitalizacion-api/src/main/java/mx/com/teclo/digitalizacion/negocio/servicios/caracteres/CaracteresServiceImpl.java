package mx.com.teclo.digitalizacion.negocio.servicios.caracteres;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.negocio.utils.RutinasTiempoImpl;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PeticionReporteVO;
import mx.com.teclo.digitalizacion.negocio.utils.generaexcel.vo.PropiedadesReporte;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.caracteres.CaracteresDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestas.RespuestasDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CamposDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.CaracterVO;
import mx.com.teclo.generaexcel.vo.PeticioReporteVO;
@Service
public class CaracteresServiceImpl implements CaracteresService{
	
@Autowired
private CaracteresDAO caracteresDAO;

@Autowired
RespuestasDAO respuestasDAO;

@Autowired
private UsuarioFirmadoService usuario;

//private ByteArrayOutputStream reporte;

private ByteArrayOutputStream byteArrayOutputStream = null;



	@Override
	@Transactional(readOnly=true)
	public List<CaracterVO> getCaracter(String tipoBusqueda,String valor) {
		List<CaracteresDTO> listcaracteresDTO=new ArrayList<CaracteresDTO>();
		List<CaracterVO> listCaracterVO =new ArrayList<CaracterVO>();
		///List<PlantillasRespuestasDTO> lisRespuestasVO=new ArrayList<PlantillasRespuestasDTO>();
		//lisRespuestasVO=respuestasDAO.getRespuestaAndContador();
		
		switch (tipoBusqueda) {
		case "TODOS":
			listcaracteresDTO=caracteresDAO.getCaracterAll();
		   listCaracterVO = ResponseConverter.converterLista(new ArrayList<>(), listcaracteresDTO, CaracterVO.class);
			break;
		case "NOMBRE":
			 listcaracteresDTO=caracteresDAO.getCaracterByConsidencia(valor);
			 listCaracterVO = ResponseConverter.converterLista(new ArrayList<>(), listcaracteresDTO, CaracterVO.class);
			
			break;
		case "ESTATUS":
			if (valor.equals("*")) {
				listcaracteresDTO=caracteresDAO.getCaracterAll();
				listCaracterVO = ResponseConverter.converterLista(new ArrayList<>(), listcaracteresDTO, CaracterVO.class);
					
			}else
			 listcaracteresDTO=caracteresDAO.getCaracterByEstatus(valor);
			 listCaracterVO = ResponseConverter.converterLista(new ArrayList<>(), listcaracteresDTO, CaracterVO.class);

			break;
		default:
			break;
		}
		
		
		return listCaracterVO;
	}
	
	@Override
	@Transactional
	public Boolean evaluasaveOrUpdate(CaracterVO caracterVO) {
		Boolean operacion=false;
		CaracteresDTO valdarCaracter=caracteresDAO.getCaracterByName(caracterVO.getCaracter());
		CaracteresDTO caracterOpe=new CaracteresDTO();
		if (valdarCaracter==null) {	
			CaracteresDTO caractersave =new CaracteresDTO();
			caracterOpe=saveOrUpdate(caractersave,caracterVO);
			operacion=true;
		}else if(valdarCaracter!=null && valdarCaracter.getId().equals(caracterVO.getId())){	
			caracterOpe=saveOrUpdate(valdarCaracter,caracterVO);
			operacion=true;
		}
		return operacion;
	}
	@Override
	public CaracteresDTO saveOrUpdate(CaracteresDTO caracteresDTO,CaracterVO caracterVO) {
		if (caracterVO.getId()!=null) {
			 caracteresDTO =caracteresDAO.findOne(caracterVO.getId());
		}
		caracteresDTO.setCaracter(caracterVO.getCaracter());
		caracteresDTO.setTxObservacion(caracterVO.getTxObservacion());
		caracteresDTO.setStActivo(caracterVO.getStActivo()==null?1:caracterVO.getStActivo());
		caracteresDTO.setFhModificacion(new Date());
		caracteresDTO.setFhCreacion(caracterVO.getFhCreacion()==null?new Date():caracterVO.getFhCreacion());
		caracteresDTO.setIdUsrCreacion(caracterVO.getIdUsrCreacion()==null?usuario.getUsuarioFirmadoVO().getId():caracterVO.getIdUsrCreacion());
		caracteresDTO.setIdUsrModifica(usuario.getUsuarioFirmadoVO().getId());
		caracteresDAO.saveOrUpdate(caracteresDTO);
		return caracteresDTO;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ByteArrayOutputStream getCaracterExcel(String tipoBusqueda,String valor) {
		ByteArrayOutputStream retorno = null;
		
		List<CaracterVO> listaCaracter = getCaracter(tipoBusqueda,valor);
			retorno = generarReporte(listaCaracter,"Reporte General de Caracteres",tipoBusqueda);	
		return retorno;
	}
	
	@SuppressWarnings("deprecation")
	private ByteArrayOutputStream generarReporte(List<CaracterVO> listaCaracteres, String nombreReporte,String tipoBusqueda) {
			RutinasTiempoImpl f =new RutinasTiempoImpl();
			PeticionReporteVO peticionReporteVO = new PeticionReporteVO();
			PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
			String busqueda =tipoBusqueda!=null? StringUtils.capitalize(tipoBusqueda):"";
			propiedadesReporte.setTituloExcel(nombreReporte);
			String resultado="";
			if (!tipoBusqueda.equals("TODOS")) {
				if (tipoBusqueda!=null || tipoBusqueda!="") {
				 resultado = busqueda.toUpperCase().charAt(0) + busqueda.substring(1, busqueda.length()).toLowerCase();
				}
				propiedadesReporte.setPersonaReporte("Tipo de Busqueda :"+resultado);
			}
			propiedadesReporte.setPersonaReporte("Fecha de Consulta: "+f.getFecha_ddMMYYYY_hhmmss(new Date()));
			propiedadesReporte.setNombreReporte(nombreReporte);
			propiedadesReporte.setExtencionArchvio(".xls");
			
			PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
			List<String> titulos = new ArrayList<String>();
			titulos.add("NOMBRE");
//			titulos.add("OBSERVACIONES");
			titulos.add("FECHA ALTA");
			titulos.add("ESTATUS");
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			encabezadoTitulo.add(titulos);
			List<Object> hojas = new ArrayList<Object>();
			List<Object> hoja1 = new ArrayList<Object>();
			List<String> listaContenido1;
			
			for (CaracterVO record : listaCaracteres) {
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(record.getCaracter());
				listaContenido1.add(record.getFhCreacion()!=null?f.getStringDateFromFormta("dd-MM-yyyy",record.getFhCreacion()):"");
				listaContenido1.add(record.getStActivo()==1?"ACTIVO":"INACTIVO");
				hoja1.add(listaContenido1);
			}
			hojas.add(hoja1);
			peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
			peticionReporteVO.setEncabezado(encabezadoTitulo);
			peticionReporteVO.setContenido(hojas);

			try {
				byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return byteArrayOutputStream;
		}
	
	@Override
	@Transactional
	public List<CaracterVO> getCaracteresByRespuesta(BigDecimal idRespuesta) {
		List<CaracteresDTO> listcaracteresDTO=new ArrayList<CaracteresDTO>();
		List<CaracterVO> listCaracterVO =new ArrayList<CaracterVO>();
		listcaracteresDTO=caracteresDAO.getCaracterByRespuesta(idRespuesta);
		listCaracterVO = ResponseConverter.converterLista(new ArrayList<>(), listcaracteresDTO, CaracterVO.class);
		return listCaracterVO;
	}

	}
