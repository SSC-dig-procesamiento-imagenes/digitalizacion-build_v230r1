package mx.com.teclo.digitalizacion.negocio.servicios.respuestas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestacaracter.RespuestaCaracterDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.respuestas.RespuestasDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillaRespuestaCaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.PlantillasRespuestasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.CaracterVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.RespuestaCaracterVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.RespuestaVO;

@Service
public class RespuestasServiceImpl implements RespuestasService {
	@Autowired
	RespuestasDAO respuestasDAO;

	@Autowired
	private CaracteresDAO caracteresDAO;

	@Autowired
	private RespuestaCaracterDAO respuestaCaracterDAO;

	@Autowired
	private UsuarioFirmadoService usuario;
	
	
	private ByteArrayOutputStream byteArrayOutputStream = null;

//	@Override
//	@Transactional
//	public List<RespuestaVO> getAllRespuestas() {
//		List<RespuestaVO> lisRespuestasVO = new ArrayList<RespuestaVO>();
//		List<PlantillasRespuestasDTO> listRespuesta = new ArrayList<PlantillasRespuestasDTO>();
//		listRespuesta = respuestasDAO.findAll();
//		for (PlantillasRespuestasDTO resp : listRespuesta) {
//			RespuestaVO res = new RespuestaVO();
//			Long contador = respuestasDAO.contador(resp.getId());
//			res.setId(resp.getId());
//			res.setNombre(resp.getNombre());
//			res.setDescripcion(resp.getDescripcion());
//			res.setStActivo(resp.getStActivo());
//			res.setFhModificacion(resp.getFhModificacion());
//			res.setFhCreacion(resp.getFhCreacion());
//			res.setIdUsrCreacion(resp.getIdUsrCreacion());
//			res.setIdUsrModifica(resp.getIdUsrModifica());
//			res.setContador(contador);
//			lisRespuestasVO.add(res);
//		}
//		return lisRespuestasVO;
//	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<RespuestaVO> getRespuestas(String tipoBusqueda,String valor) {
		List<RespuestaVO> listRespuestaVO =new ArrayList<RespuestaVO>();
     	List<PlantillasRespuestasDTO> listRespuestaDTO = new ArrayList<PlantillasRespuestasDTO>();

		///List<PlantillasRespuestasDTO> lisRespuestasVO=new ArrayList<PlantillasRespuestasDTO>();
		//lisRespuestasVO=respuestasDAO.getRespuestaAndContador();
		
		switch (tipoBusqueda) {
		case "TODOS":
			listRespuestaDTO = respuestasDAO.getAllRespuestas();
			listRespuestaVO=dtoToVO(listRespuestaDTO);
			break;
		case "NOMBRE":
			listRespuestaDTO = respuestasDAO.getListRespuestaByName(valor);
			listRespuestaVO=dtoToVO(listRespuestaDTO);
			break;
		case "ESTATUS":
			if (valor.equals("*")) {
			listRespuestaDTO = respuestasDAO.getAllRespuestas();
			listRespuestaVO=dtoToVO(listRespuestaDTO);
			}else
			listRespuestaDTO = respuestasDAO.getRespuestaByEstatus(valor);
			listRespuestaVO=dtoToVO(listRespuestaDTO);
			break;
		default:
			break;
		}
		
		
		return listRespuestaVO;
	}
	public List<RespuestaVO> dtoToVO( 	List<PlantillasRespuestasDTO>  listRespuestaDTO){
		List<RespuestaVO> listRespuestaVO =new ArrayList<RespuestaVO>();
		for (PlantillasRespuestasDTO resp : listRespuestaDTO) {
			RespuestaVO res = new RespuestaVO();
			List<CaracteresDTO> listcaracteresDTO=new ArrayList<CaracteresDTO>();
			//Long contador = respuestasDAO.contador(resp.getId());
			String caracteres="";
			res.setId(resp.getId());
			res.setNombre(resp.getNombre());
			res.setDescripcion(resp.getDescripcion());
			res.setStActivo(resp.getStActivo());
			res.setFhModificacion(resp.getFhModificacion());
			res.setFhCreacion(resp.getFhCreacion());
			res.setIdUsrCreacion(resp.getIdUsrCreacion());
			res.setIdUsrModifica(resp.getIdUsrModifica());
			listcaracteresDTO=caracteresDAO.getCaracterByRespuesta(resp.getId());
			res.setContador(listcaracteresDTO.size());
			if (listcaracteresDTO!=null) {
				for (CaracteresDTO c : listcaracteresDTO) {
					if (caracteres=="") {
						caracteres=caracteres+c.getCaracter();
					}else
						caracteres=caracteres+","+c.getCaracter();
				}
			}
			res.setCaracteres(caracteres);
			listRespuestaVO.add(res);
		}
		return listRespuestaVO;
	}
	


	@Override
	@Transactional
	public PlantillasRespuestasDTO saveRespuesta(RespuestaCaracterVO respuestaVO) {
		PlantillasRespuestasDTO existRespuestasDTO = new PlantillasRespuestasDTO();
		PlantillaRespuestaCaracteresDTO saveOrUpdateRespCarac =new PlantillaRespuestaCaracteresDTO();
		PlantillasRespuestasDTO saveOrUpdateResp= new PlantillasRespuestasDTO();
		PlantillasRespuestasDTO respDTO = new PlantillasRespuestasDTO();
		int contadorOrden=0;
		BigDecimal idRespuesta=respuestaVO.getPlantillasrespuestasId().getId()!=null?respuestaVO.getPlantillasrespuestasId().getId():null;
		existRespuestasDTO = respuestasDAO.getRespuestaByName(respuestaVO.getPlantillasrespuestasId().getNombre());
		if (existRespuestasDTO == null) {
				saveOrUpdateResp=saveOrUpdateRespuesta(respuestaVO,respDTO);
			for (CaracterVO caracterVO : respuestaVO.getListCaracterNew()) {
				contadorOrden++;
				BigInteger idOrden = BigInteger.valueOf(contadorOrden);
				PlantillaRespuestaCaracteresDTO dtoSave = new PlantillaRespuestaCaracteresDTO();
				dtoSave.setOrden(idOrden);
				saveOrUpdateRespCarac = saveRespuestaAndCaracter(caracterVO, saveOrUpdateResp.getId(),dtoSave);
			}
		}else if(idRespuesta!=null && existRespuestasDTO.getId().equals(idRespuesta)){
				saveOrUpdateResp=saveOrUpdateRespuesta(respuestaVO,existRespuestasDTO);
				if (respuestaVO.getListCaracterNew()!=null && respuestaVO.getListCaracterOld()!=null) {
					saveOrUpdateRespCarac=evalToUpdateRespuestaAndCaracter(respuestaVO);
				}
				
			return existRespuestasDTO;
		}else 
			
			return saveOrUpdateResp;;
			
		return saveOrUpdateResp;
	}
		
	
	public PlantillaRespuestaCaracteresDTO evalToUpdateRespuestaAndCaracter(RespuestaCaracterVO respuestaVO) {
		
		List<CaracterVO> newItems = respuestaVO.getListCaracterNew();
		List<CaracterVO> oldItems = respuestaVO.getListCaracterOld();
		int contadorOld = 0;
		int contadorOrden=0;
		
		PlantillaRespuestaCaracteresDTO saveOrUpdateResp = null;
		
		for (CaracterVO iNew : newItems) {
			contadorOrden++;
			BigInteger idOrden = BigInteger.valueOf(contadorOrden);
			
				PlantillaRespuestaCaracteresDTO dtoSave = new PlantillaRespuestaCaracteresDTO();
				dtoSave=respuestaCaracterDAO.getCaracterByRespuesta(respuestaVO.getPlantillasrespuestasId().getId(), iNew.getId());
				dtoSave=dtoSave==null?new PlantillaRespuestaCaracteresDTO():dtoSave;		
				dtoSave.setOrden(idOrden);
				saveOrUpdateResp = saveRespuestaAndCaracter(iNew, respuestaVO.getPlantillasrespuestasId().getId(),dtoSave);
		}
		
		for (CaracterVO iOld : oldItems) {
			contadorOld = 0;
			for (CaracterVO iNew : newItems) {if (iOld.getId().equals(iNew.getId())){contadorOld++;}}
			if (contadorOld == 0 && oldItems.size()>0) {		
				PlantillaRespuestaCaracteresDTO dtoUpdate = new PlantillaRespuestaCaracteresDTO();
				dtoUpdate=respuestaCaracterDAO.getCaracterByRespuesta(respuestaVO.getPlantillasrespuestasId().getId(), iOld.getId());
				iOld.setStActivo(0);
				saveOrUpdateResp = saveRespuestaAndCaracter(iOld, respuestaVO.getPlantillasrespuestasId().getId(),dtoUpdate);
			}
		}
		return saveOrUpdateResp;
	}
	
	public PlantillasRespuestasDTO saveOrUpdateRespuesta( RespuestaCaracterVO respuestaVO,PlantillasRespuestasDTO respDTO){
		respDTO.setNombre(respuestaVO.getPlantillasrespuestasId().getNombre());
		respDTO.setDescripcion(respuestaVO.getPlantillasrespuestasId().getDescripcion());
		respDTO.setStActivo(respuestaVO.getPlantillasrespuestasId().getStActivo()==null?1:respuestaVO.getPlantillasrespuestasId().getStActivo());
		respDTO.setFhModificacion(new Date());
		respDTO.setFhCreacion(respuestaVO.getPlantillasrespuestasId().getFhCreacion()==null?new Date():respuestaVO.getPlantillasrespuestasId().getFhCreacion());
		respDTO.setIdUsrCreacion(respuestaVO.getPlantillasrespuestasId().getIdUsrCreacion()==null?usuario.getUsuarioFirmadoVO().getId():respuestaVO.getPlantillasrespuestasId().getIdUsrCreacion());
		respDTO.setIdUsrModifica(usuario.getUsuarioFirmadoVO().getId());
		respuestasDAO.saveOrUpdate(respDTO);
		return respDTO;
	}

	public PlantillaRespuestaCaracteresDTO saveRespuestaAndCaracter(CaracterVO caracter, BigDecimal idRespuesta,PlantillaRespuestaCaracteresDTO respToCaracDto) {
	
		respToCaracDto.setCaracteresId(caracteresDAO.findOne(caracter.getId()));
		respToCaracDto.setPlantillasrespuestasId(respuestasDAO.findOne(idRespuesta));
		respToCaracDto.setStActivo(caracter.getStActivo()==null?1:caracter.getStActivo());
		respToCaracDto.setFhModificacion(new Date());
		respToCaracDto.setFhCreacion(caracter.getFhCreacion()==null?new Date():caracter.getFhCreacion());
		respToCaracDto.setIdUsrCreacion(caracter.getIdUsrCreacion()==null?usuario.getUsuarioFirmadoVO().getId():caracter.getIdUsrCreacion());
		respToCaracDto.setFhModificacion(new Date());
		respToCaracDto.setIdUsrModifica(usuario.getUsuarioFirmadoVO().getId());
		respuestaCaracterDAO.saveOrUpdate(respToCaracDto);
		return respToCaracDto;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ByteArrayOutputStream getRespuestaExcel(String tipoBusqueda,String valor) {
		ByteArrayOutputStream retorno = null;
		
		List<RespuestaVO> listaRespuesta = getRespuestas(tipoBusqueda,valor);
			retorno = generarReporte(listaRespuesta,"Reporte General De Respuestas",tipoBusqueda);	
		return retorno;
	}
	
	@SuppressWarnings("deprecation")
	private ByteArrayOutputStream generarReporte(List<RespuestaVO> listaCaracteres, String nombreReporte,String tipoBusqueda) {
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
			propiedadesReporte.setExtencionArchvio(".xls");
			
			PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();
			List<String> titulos = new ArrayList<String>();
			titulos.add("NOMBRE");
			titulos.add("OBSERVACIONES");
			titulos.add("TAMAÑO");
			titulos.add("CARACTERES");
			titulos.add("FECHA ALTA");
			titulos.add("ESTATUS");
			List<Object> encabezadoTitulo = new ArrayList<Object>();
			encabezadoTitulo.add(titulos);
			List<Object> hojas = new ArrayList<Object>();
			List<Object> hoja1 = new ArrayList<Object>();
			List<String> listaContenido1;
			

			for (RespuestaVO record : listaCaracteres) {
				listaContenido1 = new ArrayList<String>();
				listaContenido1.add(record.getNombre());
				listaContenido1.add(record.getDescripcion());
				listaContenido1.add(Integer.toString(record.getContador()));
				listaContenido1.add(record.getCaracteres());
				listaContenido1.add(record.getFhCreacion()!=null?f.getStringDateFromFormta("dd/MM/yyyy", record.getFhCreacion()):"");
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

}
