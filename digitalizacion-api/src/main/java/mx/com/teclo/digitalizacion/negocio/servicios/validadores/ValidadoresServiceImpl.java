package mx.com.teclo.digitalizacion.negocio.servicios.validadores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.digitalizacion.negocio.vo.validadores.ValidadorBusquedaVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.ValidadoresHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;
import mx.com.teclo.digitalizacion.negocio.utils.*;

@Service
public class ValidadoresServiceImpl implements ValidadoresService {
	
	@Autowired
	private ValidadoresHDAO validadoresHDAO; 
	

	@Override
	public List<ValidadorBusquedaVO>  getAllValidadores() {
		List<ValidadorBusquedaVO> listaRetorno = new ArrayList<>();
		List<ValidadoresDTO> listaDTO = validadoresHDAO.findAll();
		
		Utils.llenadoDatosListaValidadoresDTOToListaValidadorBusquedaVO(listaDTO, listaRetorno);
		
		return listaRetorno;
	}
	
	

	
}
