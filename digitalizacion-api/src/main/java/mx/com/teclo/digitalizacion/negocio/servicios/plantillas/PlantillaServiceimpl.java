package mx.com.teclo.digitalizacion.negocio.servicios.plantillas;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import mx.com.teclo.OmrMain;
import mx.com.teclo.algorithms.vo.MarcasWebVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.vo.plantillas.ParametrosAreaTrabajo;
@Service
public class PlantillaServiceimpl implements PlantillaService {

	@Override
		public MarcasWebVO obtenerMarcasValidasPx(ParametrosAreaTrabajo parametrosAreaTrabajo) throws Exception {
		MarcasWebVO marcasWeb=new MarcasWebVO();
		
		//BufferedImage im =	 ImageIO.read(new ByteArrayInputStream(parametrosAreaTrabajo.getBoleta()));
		int startPixel=parametrosAreaTrabajo.getMarcasVO().getX();
		int finalPixel=parametrosAreaTrabajo.getMarcasVO().getX()+parametrosAreaTrabajo.getMarcasVO().getWidth();
		String extencionCompleta =parametrosAreaTrabajo.getExtencion();
		String[] parts = extencionCompleta.split("/");
		String cdExtencion = parts[1]; 
		
		 File ruta = File.createTempFile(parametrosAreaTrabajo.getNombre(),"."+cdExtencion);
		 FileOutputStream cer = new FileOutputStream(ruta); 
		 cer.write(parametrosAreaTrabajo.getBoleta());
		 cer.close();
		 
		 marcasWeb=OmrMain.getMarcasPositions(ruta.getPath(), parametrosAreaTrabajo.getNombre(),
				 parametrosAreaTrabajo.getColumnas(), parametrosAreaTrabajo.getFilas(), parametrosAreaTrabajo.getCalidad(),startPixel,finalPixel);
		 ruta.deleteOnExit();
		// TODO Auto-generated method stub
		return marcasWeb;
	}

}		
