package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

@Repository
public class LotesHDAOImpl extends BaseDaoHibernate<LotesDTO> implements LotesHDAO {

	@Override
	public boolean isCancelable(LotesDTO loteDTO) {
		boolean retorno = true;
		String valor = loteDTO.getIdStatProceso().getCdEstatusProceso();
		
		if(valor.equals(ValoresEstadosLotes.PROCESADO.getValor()) 
				|| valor.equals(ValoresEstadosLotes.VALIDANDO_INFORMACION.getValor())
				|| valor.equals(ValoresEstadosLotes.INFORMACION_VALIDADA.getValor()) 
				|| valor.equals(ValoresEstadosLotes.FORMADO_PARA_LIBERACION.getValor()))
			retorno = true;
		else
			retorno = false;
		
		return retorno;
	}

	@Override
	public void incrementaCantidadImagenesRechazadas(LotesDTO loteDTO) throws Exception {
		Long valorViejo = loteDTO.getNuTotImgRechazadas();
		Long valorMaximo = loteDTO.getNuTotImagenes();
		Long valorNuevo = valorViejo +1;
		
		if(valorNuevo > valorMaximo) {
			throw new Exception("No se puede sobrepasar la cantidad de imágenes del lote");
		}
		
		loteDTO.setNuTotImgRechazadas(valorNuevo);
		updateEstatusLoteAIformacionValidada(loteDTO);
		update(loteDTO);
	}

	@Override
	public void incrementaCantidadImagenesAceptadas(LotesDTO loteDTO) throws Exception {
		Long valorViejo = loteDTO.getNuTotImgAceptadas();
		Long valorMaximo = loteDTO.getNuTotImagenes();
		Long valorNuevo = valorViejo +1;
		
		if(valorNuevo > valorMaximo) {
			throw new Exception("No se puede sobrepasar la cantidad de imágenes del lote");
		}
		
		loteDTO.setNuTotImgAceptadas(valorNuevo);
		updateEstatusLoteAIformacionValidada(loteDTO);/*No está actualizando la información del lote*/
				
		update(loteDTO);
		
	}

	@Override
	public LotesDTO cancelarLote(LotesDTO loteDTO) {
		LotesDTO loteRetorno = null;
		
		if(isCancelable(loteDTO)) {
			loteDTO.setFechaCancelacion(new Date(System.currentTimeMillis()));
			setEstatus(loteDTO, ValoresEstadosLotes.CANCELADO);
			loteRetorno = update(loteDTO);
		}
		return loteRetorno;
	}
	
	

	@Override
	public LotesDTO formarParaLiberarLote(LotesDTO loteDTO) {
		LotesDTO loteRetorno = null;
		
		if(isFormableParaLiberacion(loteDTO)) {
			loteDTO.setFechaLiberacion(new Date(System.currentTimeMillis()));
			setEstatus(loteDTO, ValoresEstadosLotes.FORMADO_PARA_LIBERACION);
			loteRetorno = update(loteDTO);
		}
		
		return loteRetorno;
	}
	
	private boolean isFormableParaLiberacion(LotesDTO loteDTO) {
		
		if(loteDTO.getIdStatProceso().getCdEstatusProceso().equals(ValoresEstadosLotes.INFORMACION_VALIDADA.getValor())) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<LotesQuery> getQueryGeneral() {
		List<LotesQuery> listaRetorno = new ArrayList<>();
		String queryJPA = "	SELECT NEW 	mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery( 							"
				+ " 					lote.idLote , lote.fhCreacionLote , lote.nuTotImagenes , lote.nuTotImgAceptadas ,	"
				+ "						lote.nuTotImgRechazadas , lote.idStatProceso.idStatProceso , 						"
				+ "						lote.idStatProceso.cdEstatusProceso, lote.idStatProceso.nombreEstatus,				"
				+ "						lote.idStatProceso.descripcionStatus )												"
				+ "			FROM LotesDTO lote 																				"
				+ "			ORDER BY lote.idLote																			";
								
		Query query = getCurrentSession().createQuery(queryJPA);
		listaRetorno = query.list();
		
		return listaRetorno;
	}

	@Override
	public List<LotesQuery> getQueryAvanzada(Date fechaInicial, Date fechaFinal, Long idEstatusLote) {
		boolean existsWhere = false;
		String queryJPA = "	SELECT NEW 	mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery( 							"
				+ " 					lote.idLote , lote.fhCreacionLote , lote.nuTotImagenes , lote.nuTotImgAceptadas ,	"
				+ "						lote.nuTotImgRechazadas , lote.idStatProceso.idStatProceso , 						"
				+ "						lote.idStatProceso.cdEstatusProceso, lote.idStatProceso.nombreEstatus,				"
				+ "						lote.idStatProceso.descripcionStatus )												"
				+ "			FROM LotesDTO lote 																				";
		String queryWhere= " WHERE 																							";
		String queryIdEstatus = " 	lote.idStatProceso.idStatProceso = 														";
		String queryAnd = "  AND  ";
		String queryDates= "		lote.fhCreacionLote BETWEEN :fechaInicial AND :fechaFinal								";
		String queryOrderBy = "	ORDER BY lote.idLote																		";
		Query query;
		
		if(idEstatusLote != null) {
			existsWhere = true;
			queryJPA += queryWhere + queryIdEstatus + idEstatusLote.toString() + "  ";
		}
			
		
		if(fechaInicial != null && fechaFinal != null) {
			if(existsWhere)
				queryJPA += queryAnd;
			else
				queryJPA += queryWhere;
			
			queryJPA += queryDates;
			queryJPA += queryOrderBy;
			query = getCurrentSession().createQuery(queryJPA)
					.setParameter("fechaInicial", fechaInicial)
					.setParameter("fechaFinal", fechaFinal);
		}
		else {
			queryJPA += queryOrderBy;
			query = getCurrentSession().createQuery(queryJPA);
		}
			
		List<LotesQuery> listaRetorno = query.list();
		
		return listaRetorno;
	}

	@Override
	public void setEstatus(LotesDTO loteDTO, ValoresEstadosLotes valorEstado) {
		String queryJPA = "	SELECT estatusDTO 								"
				+ "			FROM EstatusProcesoDTO estatusDTO 				"
				+ "			WHERE estatusDTO.cdEstatusProceso = :cdEstatus 	";
		
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("cdEstatus", valorEstado.getValor());
		EstatusProcesoDTO estatusProcesoDTO = (EstatusProcesoDTO) query.list().get(0);
		setEstatus(loteDTO, estatusProcesoDTO);
	}

	@Override
	public void setEstatus(LotesDTO loteDTO, EstatusProcesoDTO estatusProcesoDTO) {
		loteDTO.setIdStatProceso(estatusProcesoDTO);
		//update(loteDTO);
		
	}

	/*Si el lote está en "Validando Información" y ya se han validado todas sus imágenes
	 * debe cambiarse su estado a "Información Validada".
	 * Este método debe llamarse cada vez que se incrementen las cantidades de imágenes aceptadas
	 * o rechazadas.*/
	private void updateEstatusLoteAIformacionValidada(LotesDTO loteDTO) {
		
		if(puedeCambiarAInformacionValidada(loteDTO)) {
			setEstatus(loteDTO,ValoresEstadosLotes.INFORMACION_VALIDADA);
			//loteDTO.getIdStatProceso().setIdStatProceso(ValoresEstadosLotes.INFORMACION_VALIDADA.getValor());
		}
		
	}

	/*Solo pueden formarse para liberación aquellos lotes que tienen el estado "INFORMACION_VALIDADA" */
	@Override
	public void formarParaLiberacion(LotesDTO loteDTO) {
		if(loteDTO.getIdStatProceso().getCdEstatusProceso().equals(ValoresEstadosLotes.INFORMACION_VALIDADA.getValor())) {
			setEstatus(loteDTO, ValoresEstadosLotes.FORMADO_PARA_LIBERACION);
		}
		
	}
	
	private boolean puedeCambiarAInformacionValidada(LotesDTO loteDTO) {
		boolean retorno = false;
		
		if(loteDTO.getNuTotImagenes() == loteDTO.getNuTotImgAceptadas() + loteDTO.getNuTotImgRechazadas()
			&& loteDTO.getIdStatProceso().getCdEstatusProceso().equals(ValoresEstadosLotes.VALIDANDO_INFORMACION.getValor())) {
			
			retorno = true;
		}else {
			retorno = false;
		}
		
		return retorno;
	}

	/*Este método se ejecuta para los lotes de los cuales se han asignado imágenes.
	 * Se usa desde ImagenesService. 
	 * Si se asigna para validar una imagen de un lote en estado "PROCESADO", debe pasar a estado
	 * "VALIDANDO_INFORMACION"
	 * */
	@Override
	public void actualizaEstatusLotesDespuesSeleccion(List<LotesDTO> lotesDTO) {
		for(LotesDTO lote : lotesDTO) {
			if(lote.getIdStatProceso().getCdEstatusProceso().equals(ValoresEstadosLotes.PROCESADO.getValor())) {
				setEstatus(lote, ValoresEstadosLotes.VALIDANDO_INFORMACION);
				update(lote);
			} 
		}
	}

	/*Solo un lote puede tener este estado*/
	@Override
	public void ponerEnLiberacion(LotesDTO loteDTO) throws Exception {
		setEstatus(loteDTO, ValoresEstadosLotes.EN_LIBERACION);
		update(loteDTO);
		getCurrentSession().flush();
	}

	@Override
	public List<LotesDTO> getLotesPorEstado(ValoresEstadosLotes valorEstado) {
		List<LotesDTO> listaRetornar = new ArrayList<LotesDTO>();
		String queryJPA = "	SELECT  lote												"
				+ "			FROM  LotesDTO lote											"
				+ "			WHERE lote.idStatProceso.cdEstatusProceso = :estatusProceso	";
		
		Query query = getCurrentSession().createQuery(queryJPA)
				.setParameter("estatusProceso", valorEstado.getValor());
		listaRetornar = query.list();
		
		return listaRetornar;
	}
	
	

	
}
