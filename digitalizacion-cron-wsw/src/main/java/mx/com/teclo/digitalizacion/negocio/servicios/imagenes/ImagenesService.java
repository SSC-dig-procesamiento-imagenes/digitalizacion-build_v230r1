package mx.com.teclo.digitalizacion.negocio.servicios.imagenes;
import java.util.ArrayList;
import java.util.List;

import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

public interface ImagenesService {
	List<LotesVO> getLotes();
	List<ImagenesSimplesVO> getImagenesIniciales(Long idUsuario);
	List<ImagenesVO> getImagenesInicialesVO(Long idUsuario);
	//void marcarImagenComoAceptada(ImagenesSimplesVO imagenSimpleVO) throws Exception;
	void marcarImagenVOComoAceptada(ImagenesVO imagenVO) throws Exception;
	//void marcarImagenComoRechazada(ImagenesSimplesVO imagenSimpleVO);
	ImagenesVO getImagenVO(ImagenesSimplesVO imagenSimpleVO) throws Exception;
	List<ImagenesSimplesVO> getProximoGrupoImagenesAsignadas(Long idUsuario);
	ImagenesVO getImagenVOPorId(Long idImagenSimpleVO);
	List<ImagenesVO> getProximoGrupoImagenesAsignadasVO(Long idUsuario);
	void marcarImagenComoRechazada(Long idImagen) throws Exception;
	void marcarLBImagenComoAnverso(Long idLBImagen);
	void marcarLBImagenComoReverso(Long idLBImagen);
	/*Cambia la posición de la imagen especificada y la de su hermana, devuelve las nuevas imágenes*/
	List<LBImagenesVO> cambiarPosicionImagen(Long idLbImagen);
	void pruebaPersistencia();
	void actualizarImagenesPorLoteCancelado(LotesDTO loteDTO);

}
