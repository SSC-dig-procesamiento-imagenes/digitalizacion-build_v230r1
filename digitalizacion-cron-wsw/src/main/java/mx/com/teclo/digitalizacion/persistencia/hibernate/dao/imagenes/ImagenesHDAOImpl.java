package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.utils.PosicionImagen;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresImagenValidada;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresImagenesLiberadas;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

@Repository
public class ImagenesHDAOImpl extends BaseDaoHibernate<ImagenesDTO> implements ImagenesHDAO {
	
	@Autowired
	private LotesHDAO lotesHDAO;
	
	@Autowired
	private LBImagenesHDAO lBImagenesHDAO;

	@Override
	public List<ImagenesDTO> getImagenesDTOByLote(LoteDTO loteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImagenesDTO getImagenDTOById(Long idImagen) {
		ImagenesDTO img = getCurrentSession().get(ImagenesDTO.class, idImagen);
		
		return img;
	}

	@Override
	public void marcarImagenDTOComoRechazada(ImagenesDTO imagen) throws Exception {
		marcarImagenValidada(imagen,ValoresImagenValidada.RECHAZADA);
		
	}

	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public void marcarImagenDTOComoAceptada(ImagenesDTO imagen) throws Exception {
		marcarImagenValidada(imagen,ValoresImagenValidada.ACEPTADA);
		
	}
	
	private void marcarImagenValidada(ImagenesDTO imagen, ValoresImagenValidada valorSet) {
		//int valor = (valorSet == ValoresImagenValidada.ACEPTADA) ? 1 : 0;
		String queryJPA = " UPDATE ImagenesDTO imagen			"
				+ "			SET imagen.stValidada = :valor		"
				+ "			WHERE imagen.idImagen = :idImagen	";
		
		getCurrentSession().createQuery(queryJPA)
		.setParameter("idImagen", imagen.getIdImagen())
		.setParameter("valor", valorSet.getValor().shortValue())
		.executeUpdate();
	}
	
	private void actualizaLote(ImagenesDTO imagenDTO) {
		String queryJPA = "";
		
	}


	@Override
	//@Transactional(readOnly=true)
	public List<LotesDTO> metodoPrueba() {
		Query query = getCurrentSession().createQuery("select l from LotesDTO l where l.nuTotImagenes = 8 ");
		
		return query.list();
	}

	@Override
	public List<ImagenesDTO> getTodasLasImagenesDTONoAsignadas() {
		Query query;
		List<ImagenesDTO> listaRetornar = new ArrayList<>();
		String queryJPA = "	SELECT img 																			"
				+ "			FROM ImagenesDTO img 																"
				+ "			WHERE img.idImagen																	"
				+ "		 		NOT IN (																		"
				+ "						SELECT  av.idImagen 													"
				+ "			 			FROM AsignValidacionDTO av 												"
				+ "						WHERE av.stActivo = 1 													"
				+ "				) 																				"
				+ "				AND img.stLiberada = 0  														"
				+ "				AND	img.stValidada = 2  														"
				+ "				AND ( img.idLote.idStatProceso.cdEstatusProceso = :estatusProcesado 			"
				+ "						OR																		"
				+ "					  img.idLote.idStatProceso.cdEstatusProceso = :estatusValidandoInfo ) 		"
				+ "			ORDER BY img.idImagen, img.idLote													";
		
		try {
			query = getCurrentSession().createQuery(queryJPA)
					.setParameter("estatusProcesado", ValoresEstadosLotes.PROCESADO.getValor())
					.setParameter("estatusValidandoInfo",  ValoresEstadosLotes.VALIDANDO_INFORMACION.getValor());
			listaRetornar = query.list();
			
			return listaRetornar;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			listaRetornar = new ArrayList<>();
		}
		
		return listaRetornar;
	}


	@Override
	public void marcarImagenDTOComoLiberada(ImagenesDTO imagen) {
		String queryJPA = " UPDATE ImagenesDTO imagen					"
				+ "			SET imagen.stLiberada = 1					"
				+ "			WHERE imagen.idImagen = :idImagen	";
		
		getCurrentSession().createQuery(queryJPA)
		.setParameter("idImagen", imagen.getIdImagen())
		.executeUpdate();
	}

	@Override
	public void pruebaPersistencia() {
		/*Las imágenes usadas deben estar en la carpeta  "WEB-INF/classes" para que se puedan cargar
		 * vía "getContextClassLoader" */
		byte[] buf;
		InputStream inputStream = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("SS01.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 1);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS02.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 2);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS03.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 3);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS04.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 4);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS05.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 5);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS06.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 6);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS07.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 7);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS01.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 8);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS02.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 9);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS03.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 10);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS04.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 11);
		
		inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("SS05.jpg");
		buf = fillByteArrayFromInputStream(inputStream);
		lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) 12);
	}
	
	private byte[] fillByteArrayFromInputStream(InputStream inputStream) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		int size = 1024;
		int len;
		byte[] buf = new byte[size];
		
		try {
			while ((len = inputStream.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			
			buf = bos.toByteArray();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return buf;
	}

	/* Les asigna a las imágenes del lote correspondiente y que ya estén aceptadas
	 * una fecha de liberación y se asigna ST_LIBERADA = 1
	 * 	*/
	@Override
	public List<ImagenesDTO> liberarImagenesPorLote(LotesDTO lote) {
		String queryJPA = "	UPDATE ImagenesDTO img  						"
				+ "			SET img.stLiberada = :valorLiberada,			"
				+ "				img.fechaLiberacion = :fechaHoy				"
				+ "			WHERE img.idLote = :lote 						"
				+ "				  AND img.stValidada = :imagenAceptada 	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("valorLiberada", (short)ValoresImagenesLiberadas.LIBERADA.getValor())
				.setParameter("imagenAceptada", ValoresImagenValidada.ACEPTADA.getValor().shortValue())
				.setParameter("fechaHoy", new Date(System.currentTimeMillis()))
				.setParameter("lote", lote);
		
		query.executeUpdate();
		//getCurrentSession().flush();
		List<ImagenesDTO> listaRetorno = getImagenesListasParaLiberarPorLoteId(lote.getIdLote());
		return listaRetorno;
	}

	@Override
	public List<ImagenesDTO> getImagenesListasParaLiberarPorLoteId(Long idLote) {
		String queryJPA =
				" 	SELECT img										"
				+ "	FROM ImagenesDTO img							"
				+ " WHERE img.idLote.idLote = :idLote 				"
				+ "			AND img.stValidada = :imagenAceptada	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idLote", idLote)
				.setParameter("imagenAceptada", ValoresImagenValidada.ACEPTADA.getValor().shortValue());
		
		List<ImagenesDTO> listaRetorno = query.list();
		
		return listaRetorno;
	}

	
}
