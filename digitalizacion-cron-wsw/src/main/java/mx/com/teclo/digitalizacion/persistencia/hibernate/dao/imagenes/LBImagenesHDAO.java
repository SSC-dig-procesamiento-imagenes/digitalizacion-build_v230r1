package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDao;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;

public interface LBImagenesHDAO extends BaseDao<LBImagenesDTO>{
	/**
	 * 
	 * @param imagen Imagen a marcar como anverso.
	 * Marca el campo CD_IMAGEN=A.
	 */
	void marcarLBImagenDTOComoAnverso(LBImagenesDTO lBImagen);

	/**
	 * 
	 * @param imagen Imagen a marcar como reverso.
	 * Marca el campo CD_IMAGEN=R.
	 */
	void marcarLBImagenDTOComoReverso(LBImagenesDTO lBImagen);
	
	/**
	 * 
	 * @param imagen Imagen a marcar como anverso.
	 * Marca el campo CD_IMAGEN=A.
	 */
	void marcarLBImagenDTOComoAnverso(Long idLBImagen);

	/**
	 * 
	 * @param imagen Imagen a marcar como reverso.
	 * Marca el campo CD_IMAGEN=R.
	 */
	void marcarLBImagenDTOComoReverso(Long idLBImagen);
	
	LBImagenesDTO getLBImagenDTOHermana(LBImagenesDTO lBImagen);
	LBImagenesDTO getLBImagenDTOHermana(Long idLBImagen);
	
	void setByteArrayToLBImagenDTO(byte[] bytes, Long idLBImagen);

}
