package mx.com.teclo.digitalizacion.negocio.servicios.imagenes;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import mx.com.teclo.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.AsignacionesDatosVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSimplesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.VehiculoMarcaVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

public interface ImagenesService {
	List<LotesVO> getLotes();
	List<ImagenesSimplesVO> getImagenesIniciales(Long idUsuario);
	List<ImagenesVO> getImagenesInicialesVO(Long idUsuario);
	boolean marcarImagenVOComoAceptada(ImagenesVO imagenVO, Long idUsuario) throws Exception;
	boolean marcarImagenComoRechazada(Long idImagen, Long idUsuario, String causa) throws Exception;
	ImagenesVO getImagenVO(ImagenesSimplesVO imagenSimpleVO) throws Exception;
	List<ImagenesSimplesVO> getProximoGrupoImagenesAsignadas(Long idUsuario);
	ImagenesVO getImagenVOPorId(Long idImagenSimpleVO);
	List<ImagenesVO> getProximoGrupoImagenesAsignadasVO(Long idUsuario);
	void marcarLBImagenComoAnverso(Long idLBImagen);
	void marcarLBImagenComoReverso(Long idLBImagen);
	/*Cambia la posición de la imagen especificada y la de su hermana, devuelve las nuevas imágenes*/
	List<LBImagenesVO> cambiarPosicionImagen(Long idLbImagen);
	void pruebaPersistencia();
	void actualizarImagenesPorLoteCancelado(LotesDTO loteDTO);
	List<LBImagenesVO> getLBImagenesPorIdImagenDTO(Long idImagenDTO);
	List<AsignacionesDatosVO> getAsignacionesActivasData();
	void deleteAsignacionesActivasPorValidador(Long idValidador);
	List<VehiculoMarcaVO> getTodasMarcasVehiculos();
	Boolean tieneAsignacionesElUsuario(Long idUsuario);
	Boolean isImagenAceptable( ImagenesVO imagenMarcar) throws Exception;
	Long getIdLoteByIdImagen(Long idImagen);
	Boolean posponerBoleta(Long idImagen, String causa, Long idUsuario); 
	List<ImagenesVO> getTodasImagenesPospuestasByidUsuario(UsuarioFirmadoVO usuario);
	List<ImagenesVO> getTodasImagenesPospuestas();
	Boolean tieneAsignacionesPospuestasElUsuario(Long idUsuario);
	List<ImagenesVO> getImagenesPospuestasParam(Long idValidador, Date fechaInicial, Date fechaFinal);
	Boolean existenAsignacionesPospuestas();
	}
