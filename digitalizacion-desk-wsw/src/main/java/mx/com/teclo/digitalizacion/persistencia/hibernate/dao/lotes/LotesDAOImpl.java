package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.lotes.LotesDTO;

@Repository
public class LotesDAOImpl extends BaseDaoHibernate<LotesDTO> implements LotesDAO {
	@Override
	public List<LotesDTO> getLotesPorParametros(Long idstat,String fechaIni,String fechaFin) {

		String queryJPA ="SELECT lotes FROM LotesDTO lotes";
		String queryConstrain="";
		if(idstat!=null||fechaIni!=null||fechaIni.trim()!=""||
				fechaFin!=null||fechaFin.trim()!="") {
			queryConstrain+=" WHERE ";
			if(idstat!=null) {
				if(idstat<1L) {
					if(idstat==-13L){//Todos los estatus
						queryConstrain+="ID_STAT_PROCESO>="+1;
					}
				}else {
					if(idstat>=7L) {//Estatus procesado
						queryConstrain+="ID_STAT_PROCESO>=7";
					}else {//Estatus especifico
						queryConstrain+="ID_STAT_PROCESO="+idstat;
					}
				}
			}
						
			if(fechaIni!=null&&fechaFin==null||fechaFin.trim()=="") {
				queryConstrain+=" AND lotes.fechaCracionLote between TO_DATE('"+fechaIni+" 00:00:00', 'YYYY/MM/DD HH24:MI:SS') and TO_DATE('"+fechaIni+" 23:59:59', 'YYYY/MM/DD HH24:MI:SS')";
			}
			if(fechaIni!=null&&fechaFin!=null&&fechaFin.trim()!="") {
				queryConstrain+=" AND lotes.fechaCracionLote between TO_DATE('"+fechaIni+" 00:00:00', 'YYYY/MM/DD HH24:MI:SS') and TO_DATE('"+fechaFin+" 23:59:59', 'YYYY/MM/DD HH24:MI:SS')";
			}
		}
		queryJPA+=queryConstrain;
		
		
		Query query = getCurrentSession().createQuery(queryJPA);

		List<LotesDTO> listaRetorno = query.list();
		
		return listaRetorno;
	}
}
