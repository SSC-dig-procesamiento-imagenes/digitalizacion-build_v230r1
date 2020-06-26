package mx.com.teclo.digitalizacion.service.imagenes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.ortogonales.util.ResponseConverter;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesBDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes.ImagenesDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lbimagenes.LBImagenesDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesBDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.imagenes.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lbimagen.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.vo.Imagenes.ImagenesGuardarVO;
import mx.com.teclo.digitalizacion.persistencia.vo.Imagenes.LBImagenesNuevaVO;

@Service
public class ImagenesServiceImp implements ImagenesService {

	@Autowired
	private ImagenesDAO imagenesDAO;
	
	@Autowired
	private ImagenesBDAO imagenesBDAO;
	
	@Autowired
	private LBImagenesDAO lbImagenesDAO;
	
	@Autowired
	private LotesDAO lotesDAO;

	@Transactional
	public Boolean guardarInformacionImagen (ImagenesGuardarVO imagenesVO){
		ImagenesDTO imagenesDTO = null;
		
		Short statusInicial = 0;
		LBImagenesDTO lbImagenesDTO =null; 
		
		imagenesDTO = ResponseConverter.copiarPropiedadesFull(imagenesVO, ImagenesDTO.class);
		
		
		imagenesDTO.setLote(lotesDAO.findOne(imagenesVO.getIdlote()));
		
		imagenesDTO.setEstatusLiberada(statusInicial);
		
		//imagenesDTO.setEstatusValidada(statusInicial);
		
		imagenesDTO = imagenesDAO.saveOrUpdate(imagenesDTO);
		
		imagenesDAO.flush();
		
		for(LBImagenesNuevaVO lbImagenesVO : imagenesVO.getLbImagenes()) {
			lbImagenesDTO = new LBImagenesDTO();
			
			lbImagenesDTO = ResponseConverter.copiarPropiedadesFull(lbImagenesVO, LBImagenesDTO.class);
			
			lbImagenesDTO.setImagen(imagenesDTO);
			
			lbImagenesDAO.save(lbImagenesDTO);
		}
		
		/*Guardado de la imagen original en la tabla de bitácora de imágenes*/
		ImagenesBDTO imagenesBDTO= new ImagenesBDTO();
		imagenesBDTO = ResponseConverter.copiarPropiedadesFull(imagenesDTO, ImagenesBDTO.class);
		imagenesBDTO.setLote(lotesDAO.findOne(imagenesVO.getIdlote()));
		imagenesBDTO.setEstatusLiberada(statusInicial);
		imagenesBDTO = imagenesBDAO.saveOrUpdate(imagenesBDTO);
				
		return true;
	}
}
