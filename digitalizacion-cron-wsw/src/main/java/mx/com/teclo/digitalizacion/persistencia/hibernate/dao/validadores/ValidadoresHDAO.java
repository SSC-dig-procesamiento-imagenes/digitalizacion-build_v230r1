package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.*;

public interface ValidadoresHDAO extends BaseDao<ValidadoresDTO>{
	Long getIdUsuario(Long idValidador);
	ValidadoresDTO getValidadorByIdValidador(Long idValidador);
	ValidadoresDTO getValidadorByIdUsuario(Long idUsuario);
	

}
