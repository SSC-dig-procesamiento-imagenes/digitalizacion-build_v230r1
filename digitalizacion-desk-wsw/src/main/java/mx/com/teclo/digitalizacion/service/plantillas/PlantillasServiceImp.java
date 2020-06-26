package mx.com.teclo.digitalizacion.service.plantillas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.alveolos.AlveolosDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.areastrabajo.AreasTrabajoDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.campos.CamposHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.camposplantillarespuesta.CamposPlantillaRespuestaDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.marcas.MarcasDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.plantillarespuestacaracteres.PlantillaRespuestaCaracteresHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.plantillas.PlantillasDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.alveolos.AlveolosDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.areastrabajo.AreasTrabajoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.campos.CamposDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.camposplantillarespuesta.CamposPlantillaRespuestaDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.caracteres.CaracteresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.marcas.MarcasDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.plantillas.PlantillasDTO;
import mx.com.teclo.digitalizacion.persistencia.vo.alvelos.AlveolosPlantillaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.alvelos.AlveolosVO;
import mx.com.teclo.digitalizacion.persistencia.vo.areastrabajo.AreasTrabajoPlantillaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.campos.CamposPlantillaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.caracteres.CaracteresVO;
import mx.com.teclo.digitalizacion.persistencia.vo.marcas.MarcasPlantillaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillas.PlantillaCompletaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillas.PlantillasVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada.CamposSolicitadosVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada.PlantillaRespuestaSolicitadaVO;
import mx.com.teclo.digitalizacion.persistencia.vo.plantillasolicitada.PlantillaSolicitadaVO;

@Service
public class PlantillasServiceImp implements PlantillasService {
	
	@Autowired
	private PlantillasDAO plantillasDAO;
	
	@Autowired
	private CamposPlantillaRespuestaDAO camposPlantillaRespuestaDAO;
	
	@Autowired
	private AreasTrabajoDAO areasTrabajoDAO;
	
	@Autowired
	private AlveolosDAO alveolosDAO;
	
	@Autowired
	private MarcasDAO marcasDAO;
	
	@Autowired
	private CamposHDAO camposHDAO;
	
	@Autowired
	private PlantillaRespuestaCaracteresHDAO plantillaRespuestaCaracteresHDAO;
	
	@Transactional(readOnly = true)
	public PlantillaCompletaVO obtenerPlantillaPorIdentificador(Long platillaId) {
		PlantillaCompletaVO plantillaCompletaVO = new PlantillaCompletaVO();
		
		PlantillasVO plantillasVO = null;
		PlantillasDTO plantillasDTO = new PlantillasDTO();
		
		List<CamposPlantillaRespuestaDTO> listaCamposDTO = new ArrayList<>();
		List<CamposPlantillaVO> listaCamposVO = new ArrayList<>(); 
		
		List<AreasTrabajoDTO> listaAreasTrabajoDTO = new ArrayList<>();
		List<AreasTrabajoPlantillaVO> listaAreasTrabajoVO = new ArrayList<>();
		
		List<AlveolosDTO> listaalveolosDTO = new ArrayList<>();
		List<AlveolosPlantillaVO> listaAlvelosVO = new ArrayList<>();
		
		List<MarcasDTO> listaMarcasDTO = new ArrayList<>();
		List<MarcasPlantillaVO> listaMarcasVO = new ArrayList<>();
		
		plantillasDTO = plantillasDAO.findOne(platillaId);
		plantillasVO = ResponseConverter.copiarPropiedadesFull(plantillasDTO, PlantillasVO.class);
		
		plantillaCompletaVO = ResponseConverter.copiarPropiedadesFull(plantillasVO, PlantillaCompletaVO.class);
		
		//Obtener los  campos del DTO de campos 
		List<CamposDTO> listCamposDTO; 
			
		//Obtener la relacion de 
		listaCamposDTO = camposPlantillaRespuestaDAO.buscaTodosLosCampos(plantillasDTO.getIdPlantilla());
		
		listaCamposVO = extraerListaCampos(listaCamposDTO);
		
		plantillaCompletaVO.setCampos(listaCamposVO);
		
		listaAreasTrabajoDTO = areasTrabajoDAO.obtnerListaAreasTrabajo(plantillasDTO.getIdPlantilla());
		listaAreasTrabajoVO = ResponseConverter.converterLista(new ArrayList<>(), listaAreasTrabajoDTO, AreasTrabajoPlantillaVO.class);
		
		plantillaCompletaVO.setAreasTrabajo(listaAreasTrabajoVO);
		
		listaalveolosDTO = alveolosDAO.buscarAlbelosPlantilla(plantillasDTO.getIdPlantilla());
		listaAlvelosVO = ResponseConverter.converterLista(new ArrayList<>(), listaalveolosDTO, AlveolosPlantillaVO.class);
		
		plantillaCompletaVO.setAlveolos(listaAlvelosVO);
		
		listaMarcasDTO = marcasDAO.buscarMarcasPorPlatilla(plantillasDTO.getIdPlantilla());
		listaMarcasVO = ResponseConverter.converterLista(new ArrayList<>(), listaMarcasDTO, MarcasPlantillaVO.class);
		
		plantillaCompletaVO.setMarcas(listaMarcasVO);
		
		return plantillaCompletaVO;
	}
	
	private List<CamposPlantillaVO> extraerListaCampos(List<CamposPlantillaRespuestaDTO> listaCamposDTO){
		List<CamposPlantillaVO> listaCamposVO = new ArrayList<>();
		
		for (CamposPlantillaRespuestaDTO camposPlantillaRespuestaDTO : listaCamposDTO) {
			listaCamposVO.add(ResponseConverter.copiarPropiedadesFull(camposPlantillaRespuestaDTO.getCampos(), CamposPlantillaVO.class));
		}
		
		return listaCamposVO;
	}

	@Override
	@Transactional(readOnly = true)
	public PlantillaSolicitadaVO obtenerPlantillaSolicitadaVOPorId(Long idPlantilla) {
		PlantillaSolicitadaVO plantillaRetorno = new PlantillaSolicitadaVO();
		
		PlantillasDTO plantillaDTO = plantillasDAO.findOne(idPlantilla);
		copiaDatosInicialesPlantillaDTOtoPlantillaSolicitada(plantillaDTO, plantillaRetorno);//Copia Datos iniciales
		
		AlveolosDTO alveoloDTO = alveolosDAO.buscarAlbelosPlantilla(idPlantilla).get(0);
		AlveolosVO alveoloVO = ResponseConverter.copiarPropiedadesFull(alveoloDTO, AlveolosVO.class);
		plantillaRetorno.setAlveolo(alveoloVO);
		
		List<CamposSolicitadosVO> campos = new ArrayList<>();
		List<CamposDTO> camposDTO = camposHDAO.getListaCamposDTOporIdPlantilla(idPlantilla);
		
		//Llenado de List<CamposSolicitadosVO> campos:
		for(CamposDTO campoDTO : camposDTO) {
			CamposSolicitadosVO campoSolicitadoVO = new CamposSolicitadosVO();
			copiaDatosCamposDTOtoCamposSolicitadosVO(campoDTO,campoSolicitadoVO);
			
			List<PlantillaRespuestaSolicitadaVO> listaPlantillaRespuestaSolicitadaVO = new ArrayList<>();
			
			List<CamposPlantillaRespuestaDTO> camposPlantillaRespuestaDTO = camposPlantillaRespuestaDAO.getTodosCamposPlantillasRespuestaDTOporIdCampo(campoDTO.getIdCampos());
			
			//Llenado de List<PlantillaRespuestaSolicitadaVO> plantillarespuesta:
			for(CamposPlantillaRespuestaDTO camposPlantillaRespuestaDTOTmp : camposPlantillaRespuestaDTO) {
				PlantillaRespuestaSolicitadaVO plantillaRespuestaSolicitadaVOTmp = new PlantillaRespuestaSolicitadaVO();
				plantillaRespuestaSolicitadaVOTmp.setId(camposPlantillaRespuestaDTOTmp.getPlantillaRespuesta().getIdPlantillaRespuesta());
				plantillaRespuestaSolicitadaVOTmp.setNombre(camposPlantillaRespuestaDTOTmp.getPlantillaRespuesta().getNombrePlantillaRespuesta());
				plantillaRespuestaSolicitadaVOTmp.setDescripcion(camposPlantillaRespuestaDTOTmp.getPlantillaRespuesta().getDescripcionPlantillaRespuesta());
				
				Long idBuscar = camposPlantillaRespuestaDTOTmp.getPlantillaRespuesta().getIdPlantillaRespuesta();
				
				List<CaracteresVO> caracteresVO = new ArrayList<>();
				List<CaracteresDTO> caracteresDTO = plantillaRespuestaCaracteresHDAO.getCaracteresPorIdPlantillaRespuesta(idBuscar);
				caracteresVO = ResponseConverter.converterLista(new ArrayList<>(), caracteresDTO, CaracteresVO.class);
				
				plantillaRespuestaSolicitadaVOTmp.setCaracteres(caracteresVO);
				listaPlantillaRespuestaSolicitadaVO.add(plantillaRespuestaSolicitadaVOTmp);
				
			}
			campoSolicitadoVO.setPlantillarespuesta(listaPlantillaRespuestaSolicitadaVO);
			
			campos.add(campoSolicitadoVO);
		}
		
		
		plantillaRetorno.setCampos(campos);
		return plantillaRetorno;
	}
	
	private void copiaDatosCamposDTOtoCamposSolicitadosVO(CamposDTO origen, CamposSolicitadosVO destino) {
		destino.setId(origen.getIdCampos());
		destino.setNombre(origen.getNombreCampos());
		destino.setCoordenadaiy(origen.getCoordenadaiY());
		destino.setCoordenadaix(origen.getCoordenadaiX());
		destino.setCoordenadafy(origen.getCoordenadafY());
		destino.setCoordenadafx(origen.getCoordenadafX());
		destino.setOrientaciones_id(origen.getOrientaciones().getIdOrientaciones());
		destino.setPlantillas_id(origen.getIdPlantilla().getIdPlantilla());
	}
	
	private void copiaDatosInicialesPlantillaDTOtoPlantillaSolicitada(PlantillasDTO origen, PlantillaSolicitadaVO destino) {
		destino.setId(origen.getIdPlantilla());
		destino.setNombre(origen.getNombre());
		destino.setAlgoritmo_ajuste(origen.getAlgoritmoAjuste());
	}
	
}
