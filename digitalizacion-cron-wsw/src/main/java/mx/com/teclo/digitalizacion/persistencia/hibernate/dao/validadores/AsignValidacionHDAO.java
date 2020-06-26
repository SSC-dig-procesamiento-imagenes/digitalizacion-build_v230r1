package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

public interface AsignValidacionHDAO extends BaseDao<AsignValidacionDTO>{

	List<AsignValidacionDTO> getTodasAsignacionesActivas(ValidadoresDTO validador);
	List<ImagenesDTO> getTodasImagenesAsignadasActivas(ValidadoresDTO validador);
	Long asignarImagenValidador(ImagenesDTO imagenAdicionar,ValidadoresDTO validador);
	void asignarImagenesValidador(List<ImagenesDTO> imagenesAdicionar,ValidadoresDTO validador);
	void deleteAsignacion(AsignValidacionDTO asign);
	AsignValidacionDTO getAsignacionActivaByImagen(ImagenesDTO imagenDTO);
	List<ImagenesDTO> getTodasImagenesEnUI(ValidadoresDTO validador);
	void setImagenesEnUI(List<ImagenesDTO> imagenes,ValidadoresDTO validador);
	void setImagenEnUI(ImagenesDTO imagen, ValidadoresDTO validador);
	boolean isAsignadaImagenAValidadorEnUI(ImagenesDTO imagen, ValidadoresDTO validador);
	void setImagenesEnUIById(List<Long> idImagenes,ValidadoresDTO validador);
	void setImagenEnUIById(Long idImagen, ValidadoresDTO validador);

}
