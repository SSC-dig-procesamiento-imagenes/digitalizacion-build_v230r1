package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresImagenValidada;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
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
	public List<ImagenesDTO> getImagenesDTOByLote(Long loteDTO) {
		List<ImagenesDTO> listaRetornar = new ArrayList<>();
		String queryJPA = "	SELECT img 					"
				+ "			FROM ImagenesDTO img 		"
				+ "			WHERE img.idLote.idLote = :lote  	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("lote", loteDTO);
		
		listaRetornar = query.list();
		
		return listaRetornar;
	}

	@Override
	public ImagenesDTO getImagenDTOById(Long idImagen) {
		ImagenesDTO img = null;//getCurrentSession().get(ImagenesDTO.class, idImagen);
		String queryJPA =
				"	SELECT img 						"
				+ "	FROM ImagenesDTO img 			"
				+ "	WHERE img.idImagen = :idImagen	";
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idImagen", idImagen);
		img = (ImagenesDTO) query.list().get(0);
		return img;
	}

	@Override
	public void marcarImagenDTOComoRechazada(ImagenesDTO imagen, ValidadoresDTO validadorActivo,String causa) throws Exception {
		//marcarImagenValidada(imagen,ValoresImagenValidada.RECHAZADA, validadorActivo);
		imagen.setStValidada(ValoresImagenValidada.RECHAZADA.getValor().shortValue());
		imagen.setIdValidador(validadorActivo);
		imagen.setTxRechazada(causa);
		saveOrUpdate(imagen);
	}

	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public void marcarImagenDTOComoAceptada(ImagenesDTO imagen) throws Exception {
		//marcarImagenValidada(imagen,ValoresImagenValidada.ACEPTADA);
		imagen.setStValidada( ValoresImagenValidada.ACEPTADA.getValor().shortValue());
		
	}
	
	private void marcarImagenValidada(ImagenesDTO imagen, ValoresImagenValidada valorSet, ValidadoresDTO validadorActivo) {
		//int valor = (valorSet == ValoresImagenValidada.ACEPTADA) ? 1 : 0;
		String queryJPA = " UPDATE ImagenesDTO imagen				"
				+ "			SET imagen.stValidada = :valor,			"
				+ "			imagen.idValidador = :validadorActivo	"
				+ "			WHERE imagen.idImagen = :idImagen		";
		
		getCurrentSession().createQuery(queryJPA)
		.setParameter("idImagen", imagen.getIdImagen())
		.setParameter("valor", valorSet.getValor().shortValue())
		.setParameter("validadorActivo", validadorActivo)
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
	public List<ImagenesDTO> getTodasLasImagenesDTONoAsignadas(Long cantidadTotalMaximaAsignar) {
		
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
				+ "				AND	img.stValidada IS NULL														"
				+ "				AND img.idLote.idStatProceso.cdEstatusProceso = :estatusValidandoInfo 			"
				+ "			ORDER BY img.idImagen, img.idLote													";
		
		try {
			query = getCurrentSession().createQuery(queryJPA)
					.setParameter("estatusValidandoInfo",  ValoresEstadosLotes.VALIDANDO_INFORMACION.getValor());
			listaRetornar = query.setMaxResults(cantidadTotalMaximaAsignar.intValue())
					.setFirstResult(0)
					.list();
			
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
		InputStream inputStream = null;
		
		for(int i=1; i<=30; i++) {
			String nombreImg = "P"+i+".jpg";
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(nombreImg);
			buf = fillByteArrayFromInputStream(inputStream);
			lBImagenesHDAO.setByteArrayToLBImagenDTO(buf, (long) i);
		}
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

	@Override
	public List<ImagenesDTO> getImagenesDTOByLote(LoteDTO loteDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LotesDTO getLoteByIdImagen(Long idImagen) {
		Query query;
		LotesDTO loteDTO = null;
		String queryJPA =
				"SELECT img.idLote				"
			  + "FROM ImagenesDTO img			"
			  + "WHERE img.idImagen = :idImagen	";
		
		query = getCurrentSession().createQuery(queryJPA)
		.setParameter("idImagen", idImagen);
		
		loteDTO = (LotesDTO) query.list().get(0);
		
		return loteDTO;
	}

}
