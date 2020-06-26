package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;
import java.util.List;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

public interface ImagenesHDAO extends BaseDao<ImagenesDTO>{
	
	/**
	 * 
	 * @param loteDTO Lote a la que pertenecen las imágenes
	 * @return Lista de imágenes del lote
	 */
	List<ImagenesDTO> getImagenesDTOByLote(LoteDTO loteDTO);

	/**
	 * 
	 * @param idImagen Id de la imagen
	 * @return El objeto imagen cuyo id es pasado como parámetro
	 */
	ImagenesDTO getImagenDTOById(Long idImagen);
	
	/**
	 * 
	 * @param imagen Imagen a marcar como rechazada.
	 * Marca el campo ST_VALIDADA = false.
	 * @param validadorActivo 
	 * @throws Exception 
	 */
	void marcarImagenDTOComoRechazada(ImagenesDTO imagen, ValidadoresDTO validadorActivo,String causa) throws Exception;
	
	/**
	 * 
	 * @param imagen Imagen a marcar como aceptada.
	 * Marca el campo ST_VALIDADA = true.
	 * @throws Exception 
	 */
	void marcarImagenDTOComoAceptada(ImagenesDTO imagen) throws Exception;
	

	/**
	 * 
	 * @return el lista de todos los lotes.
	 * Método de prueba para después borrarlo
	 */
	List<LotesDTO> metodoPrueba();
	
	/**
	 * 
	 * @param cantidadTotalMaximaAsignar 
	 * @return La lista de todas las imágenes sin asignar
	 */
	List<ImagenesDTO> getTodasLasImagenesDTONoAsignadas(Long cantidadTotalMaximaAsignar);
	
	/**
	 * Marca las imágenes pasadas como parámetros como dependientes una de otra.
	 * @param imagen1
	 * @param imagen2
	 */
//	void marcarImagenesDTODependientes(ImagenesDTO imagen1, ImagenesDTO imagen2);
	
	/**
	 * Las imágenes liberadas son aquellas que ya terminaron todo el proceso y están listas
	 * para ser pasadas al próximo sistema de la cadena.
	 * @param imagen
	 */
	void marcarImagenDTOComoLiberada(ImagenesDTO imagen);
	
	void pruebaPersistencia();

	List<ImagenesDTO> getImagenesDTOByLote(Long loteDTO);
	
	LotesDTO getLoteByIdImagen(Long idImagen);
}
