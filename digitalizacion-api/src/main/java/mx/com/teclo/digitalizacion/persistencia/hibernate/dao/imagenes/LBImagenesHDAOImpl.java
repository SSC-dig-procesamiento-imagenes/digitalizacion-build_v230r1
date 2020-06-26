package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.imagenes;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.utils.PosicionImagen;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import org.springframework.stereotype.Repository;

@Repository
public class LBImagenesHDAOImpl extends BaseDaoHibernate<LBImagenesDTO> implements LBImagenesHDAO{

	@Override
	public void marcarLBImagenDTOComoAnverso(LBImagenesDTO imagen) {
		marcarPosicionPagina(imagen, PosicionImagen.ANVERSO);
	}

	@Override
	public void marcarLBImagenDTOComoReverso(LBImagenesDTO imagen) {
		marcarPosicionPagina(imagen, PosicionImagen.REVERSO);
	}

	private void marcarPosicionPagina(LBImagenesDTO lbImagen, PosicionImagen posicion) {
		lbImagen.setCodigoImagen(posicion.getValor());
		update(lbImagen);
		
		LBImagenesDTO otherLbImagen = getLBImagenDTOHermana(lbImagen);
		
		/*Actualicemos la página hermana en dependencia del valor anterior:*/
		String valor = (posicion == PosicionImagen.ANVERSO) ? 
							PosicionImagen.REVERSO.getValor()  
							: PosicionImagen.ANVERSO.getValor();
		otherLbImagen.setCodigoImagen(valor);
		update(otherLbImagen);
	}

	@Override
	public void marcarLBImagenDTOComoAnverso(Long idLBImagen) {
		LBImagenesDTO imagen = findOne(idLBImagen);
		marcarLBImagenDTOComoAnverso(imagen);
		
	}

	@Override
	public void marcarLBImagenDTOComoReverso(Long idLBImagen) {
		LBImagenesDTO imagen = findOne(idLBImagen);
		marcarLBImagenDTOComoReverso(imagen);
		
	}

	@Override
	public LBImagenesDTO getLBImagenDTOHermana(LBImagenesDTO lBImagen) {
		String queryJPA = " SELECT	lbImagen							"
				+ "			FROM 	LBImagenesDTO lbImagen				"
				+ "			WHERE 	lbImagen.idImagen = :idImagen		"
				+ "				AND		lbImagen.idLbImagen <> :otherId	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
		.setParameter("idImagen", lBImagen.getIdImagen())
		.setParameter("otherId",lBImagen.getIdLbImagen());
		
		LBImagenesDTO lbImagenHermana = (LBImagenesDTO) query.list().get(0);
		
		return lbImagenHermana;
	}

	@Override
	public LBImagenesDTO getLBImagenDTOHermana(Long idLBImagen) {
		LBImagenesDTO lBImagen = findOne(idLBImagen);
		LBImagenesDTO lbImagenHermana = getLBImagenDTOHermana(lBImagen);
		return lbImagenHermana;
	}

	@Override
	public void setByteArrayToLBImagenDTO(byte[] bytes, Long idLBImagen) {
		LBImagenesDTO toModify = findOne(idLBImagen);
		toModify.setLbImagen(bytes);
		update(toModify);
		
	}

	@Override
	public List<LBImagenesDTO> getListaLBImagenDTOPorIdImagen(Long idImagenDTO) {
		List<LBImagenesDTO> listaRetornar = new ArrayList<>();
		String queryJPA = "	SELECT lbImg 									"
				+ "			FROM LBImagenesDTO lbImg 						"
				+ "			WHERE lbImg.idImagen.idImagen = :idImagenDTO 	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("idImagenDTO", idImagenDTO);
		listaRetornar = query.list();
		
		return listaRetornar;
	}
	
	

	
}
