package mx.com.teclo.digitalizacion.negocio.servicios.estadisticas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.ConsultaGeneralVO;
import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.FechasConsultasVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.EstadisticasImagenesHDAO;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

	@Autowired
	private EstadisticasImagenesHDAO estadisticasImagenesHDAO;
	
	@Override
	@Transactional(readOnly = true)
	public List<ConsultaGeneralVO> getConsultaGeneral(String fechaInicialStr, String fechaFinalStr) {
		List<ConsultaGeneralVO> listaRetorno = new ArrayList<>();
		List<Object[]>listaObjetos = estadisticasImagenesHDAO.getCantidadEvaluacionesPorUsuario(fechaInicialStr, fechaFinalStr);
		Utils.llenadoDatosListaConsultaGralObToListaConsultaGralVOJPA(listaObjetos, listaRetorno);
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConsultaGeneralVO> getConsultaGeneral() {
		List<ConsultaGeneralVO> listaRetorno = new ArrayList<>();
		List<Object[]>listaObjetos = estadisticasImagenesHDAO.getCantidadEvaluacionesPorUsuario();
		Utils.llenadoDatosListaConsultaGralObToListaConsultaGralVOJPA(listaObjetos, listaRetorno);
		return listaRetorno;
	}

	
}
