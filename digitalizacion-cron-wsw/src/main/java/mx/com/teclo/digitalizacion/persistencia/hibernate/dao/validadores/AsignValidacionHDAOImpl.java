package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;

@Repository
public class AsignValidacionHDAOImpl extends BaseDaoHibernate<AsignValidacionDTO>
		implements AsignValidacionHDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<AsignValidacionDTO> getTodasAsignacionesActivas(ValidadoresDTO validador) {
		List<AsignValidacionDTO> listaAsignaciones = new ArrayList<>();
		String queryJPA = "	SELECT a 							"
				+ "			FROM AsignValidacionDTO a 			"
				+ "			WHERE a.idValidador = :validador 	"
				+ "					AND a.stActivo = 1 			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		listaAsignaciones = query.list();
		
		return listaAsignaciones;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenesDTO> getTodasImagenesAsignadasActivas(ValidadoresDTO validador) {
		List<ImagenesDTO> listaImagenes = new ArrayList<>();
		String queryJPA = "	SELECT a.idImagen 					"
				+ "			FROM AsignValidacionDTO a 			"
				+ "			WHERE  a.idValidador = :validador 	"
				+ "				AND a.stActivo = 1 				";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		listaImagenes =  query.list();

		return listaImagenes;
	}

	@Override
	public Long asignarImagenValidador(ImagenesDTO imagenAdicionar, ValidadoresDTO validador) {
		AsignValidacionDTO asignacion = new AsignValidacionDTO();
		Long idRetornado;
		
		asignacion.setIdImagen(imagenAdicionar);
		asignacion.setIdValidador(validador);
		asignacion.setStActivo((short) 1);
		idRetornado = (Long) getCurrentSession().save(asignacion);
	
		return idRetornado;
	}

	@Override
	public void asignarImagenesValidador(List<ImagenesDTO> imagenesAdicionar, ValidadoresDTO validador) {
		
		for(ImagenesDTO imagen: imagenesAdicionar) {
			asignarImagenValidador(imagen,validador);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#deleteAsignacion(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.AsignValidacionDTO)
	 */
	@Override
	public void deleteAsignacion(AsignValidacionDTO asign) {
		String queryJPA = " DELETE FROM AsignValidacionDTO asign	"
				+ "			WHERE asign.idAsignValidador = :idAsignValidador ";
		
		getCurrentSession().createQuery(queryJPA)
		.setParameter("idAsignValidador", asign.getIdAsignValidador())
		.executeUpdate();

	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#getAsignacionActivaByImagen(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO)
	 */
	@Override
	public AsignValidacionDTO getAsignacionActivaByImagen(ImagenesDTO imagenDTO) {
		String queryJPA = " SELECT asign 						"
				+ "			FROM AsignValidacionDTO asign  		"
				+ "			WHERE  asign.idImagen = :imagenDTO	"
				+ "				AND asign.stActivo = 1			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
						.setParameter("imagenDTO", imagenDTO);
		AsignValidacionDTO asignValidacionDTO = (AsignValidacionDTO) query.list().get(0);
		
		return asignValidacionDTO;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#getTodasImagenesEnUI(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ImagenesDTO> getTodasImagenesEnUI(ValidadoresDTO validador) {
		String queryJPA = "	SELECT asign.idImagen					"
				+ "			FROM AsignValidacionDTO asign 			"
				+ "			WHERE asign.idValidador = :validador 	"
				+ "				AND asign.stActivo = 1 				"
				+ "				AND asign.imagenEnUI = 1 			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("validador", validador);
		List<ImagenesDTO> imagenesEnUI = query.list();
		
		
		return imagenesEnUI;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenesEnUI(java.util.List, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenesEnUI(List<ImagenesDTO> imagenes, ValidadoresDTO validador) {
		for(ImagenesDTO imagen :imagenes) {
			setImagenEnUI(imagen, validador);
		}
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenEnUI(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenEnUI(ImagenesDTO imagen, ValidadoresDTO validador) {
		String queryJPA = "	UPDATE AsignValidacionDTO asign				"
				+ "			SET asign.imagenEnUI = 1 					"
				+ "				WHERE asign.idImagen = :imagen 			"
				+ "					AND asign.idValidador = :validador	"
				+ "					AND asign.stActivo = 1 				";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("imagen", imagen)
				.setParameter("validador", validador);
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#isAsignadaImagenAValidador(mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public boolean isAsignadaImagenAValidadorEnUI(ImagenesDTO imagen, ValidadoresDTO validador) {
		String queryJPA = " SELECT asign							"
				+ "			FROM AsignValidacionDTO asign  			"
				+ "			WHERE asign.idImagen = :imagen 			"
				+ "				AND asign.idValidador = :validador 	"
				+ "				AND asign.stActivo = 1				"
				+ "				AND asign.imagenEnUI = 1 			";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("imagen", imagen)
				.setParameter("validador", validador);
		boolean isAsignada = query.list().size() > 0;
		
		return isAsignada;
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenesEnUIById(java.util.List, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenesEnUIById(List<Long> idImagenes, ValidadoresDTO validador) {
		for (Long idImagen : idImagenes) {
			setImagenEnUIById(idImagen, validador);
		}
		
	}

	/* (non-Javadoc)
	 * @see mx.com.teclo.digitalizacion.persistencia.hibernate.dao.validadores.AsignValidacionHDAO#setImagenEnUIById(java.lang.Long, mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO)
	 */
	@Override
	public void setImagenEnUIById(Long idImagen, ValidadoresDTO validador) {
		String queryJPA = "	UPDATE AsignValidacionDTO asign					"
				+ "			SET asign.imagenEnUI = 1 						"
				+ "				WHERE asign.idImagen.idImagen = :idImagen	"
				+ "					AND asign.idValidador = :validador 		"
				+ "					AND asign.stActivo = 1 					";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idImagen", idImagen)
				.setParameter("validador", validador);
		query.executeUpdate();
		
	}
	
	
	
	
	
}
