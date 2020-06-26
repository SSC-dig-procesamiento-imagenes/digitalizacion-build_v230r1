package mx.com.teclo.digitalizacion.negocio.servicios.reportesgenerales;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboComponentesVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ControlCatalogosVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.reportesgenerales.CtrlReportesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CtrlReportesDTO;


@Service
public class ReportesGeneralesServiceImpl implements ReportesGeneralesService {

	@Autowired
	private CtrlReportesHDAO ctrlReportesHDAO;
	
	
	/*
	 * 
	 */
	
	@Override
	public List<ControlCatalogosVO> getReportesConfigurados() {
		List<ControlCatalogosVO> listReturn = new ArrayList<>();
		List<CtrlReportesDTO> listaReportes = ctrlReportesHDAO.findAll();
		if(listaReportes == null || listaReportes.isEmpty()) {
			return null;
		}
	
		for(CtrlReportesDTO dto : listaReportes) {
			CtrlReportesDTO padre = ctrlReportesHDAO.findOne(dto.getIdPadre());
			ControlCatalogosVO destino = new ControlCatalogosVO();
			Utils.llenadoDatosCtrlReportesDTOToControlCatalogosVO(dto, padre, destino);
			listReturn.add(destino);
		}
		
		return listReturn;
	}

	

}
