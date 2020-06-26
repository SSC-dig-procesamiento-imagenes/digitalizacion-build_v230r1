package mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.arquitectura.persistencia.comun.dao.BaseDaoHibernate;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;
import mx.com.teclo.digitalizacion.negocio.utils.ValoresEstadosLotes;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;

@Repository
public class LotesHDAOImpl extends BaseDaoHibernate<LotesDTO> implements LotesHDAO {

	@Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Override
	public boolean isCancelable(LotesDTO loteDTO) {
		boolean retorno = true;
		String valor = loteDTO.getIdStatProceso().getCdEstatusProceso();
		
		if(valor.equals(ValoresEstadosLotes.PROCESADO.getValor()) 
				|| valor.equals(ValoresEstadosLotes.INFORMACION_VALIDADA.getValor()) 
				|| valor.equals(ValoresEstadosLotes.FORMADO_PARA_LIBERACION.getValor()))
			retorno = true;
		else
			retorno = false;
		
		return retorno;
	}

	@Override
	public boolean incrementaCantidadImagenesRechazadas(LotesDTO loteDTO) throws Exception {
		boolean generaCambioEstadoLote = false;
		Long valorViejo = loteDTO.getNuTotImgRechazadas();
		Long valorMaximo = loteDTO.getNuTotImagenes();
		Long valorNuevo = valorViejo +1;
		
		if(valorNuevo > valorMaximo) {
			throw new Exception("No se puede sobrepasar la cantidad de imágenes del lote");
		}
		
		loteDTO.setNuTotImgRechazadas(valorNuevo);
		generaCambioEstadoLote = updateEstatusLoteAIformacionValidada(loteDTO);
		update(loteDTO);
		return generaCambioEstadoLote;
	}

	@Override
	public boolean incrementaCantidadImagenesAceptadas(LotesDTO loteDTO) throws Exception {
		boolean generaCambioEstadoLote = false;
		Long valorViejo = loteDTO.getNuTotImgAceptadas();
		Long valorMaximo = loteDTO.getNuTotImagenes();
		Long valorNuevo = valorViejo +1;
		
		if(valorNuevo > valorMaximo) {
			throw new Exception("No se puede sobrepasar la cantidad de imágenes del lote");
		}
		
		loteDTO.setNuTotImgAceptadas(valorNuevo);
		generaCambioEstadoLote = updateEstatusLoteAIformacionValidada(loteDTO);
				
		update(loteDTO);
		
		return generaCambioEstadoLote;
		
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
				+ "						lote.idStatProceso.descripcionStatus, lote.nbLote, lote.nuFolioInicial, 			"
				+ "						lote.nuFolioFinal )																	"
				+ "			FROM LotesDTO lote 																				"
				+ "			WHERE lote.idStatProceso.cdEstatusProceso IN ('PROCESADO', 'LIBERADO', 'VALINFORMACION',		"		
				+ "					'FORMLIBERACION','ENLIBERACION', 'CANCELADO', 'INFOVALIDADA')							"
				+ "			ORDER BY lote.idLote																			";
								
		Query query = getCurrentSession().createQuery(queryJPA);
		listaRetorno = query.list();
		
		return listaRetorno;
	}
	
	@Override
	public List<LotesQuery> getLotesParaExcel(List<Long> idLotes) {
		List<LotesQuery> listaRetorno = new ArrayList<>();
		String queryJPA = "	SELECT NEW 	mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery( 							"
				+ " 					lote.idLote , lote.fhCreacionLote , lote.nuTotImagenes , lote.nuTotImgAceptadas ,	"
				+ "						lote.nuTotImgRechazadas , lote.idStatProceso.idStatProceso , 						"
				+ "						lote.idStatProceso.cdEstatusProceso, lote.idStatProceso.nombreEstatus,				"
				+ "						lote.idStatProceso.descripcionStatus, lote.nbLote, lote.nuFolioInicial,				"
				+ "						lote.nuFolioFinal )																	"
				+ "			FROM LotesDTO lote 																				"
				+ "			WHERE lote.idLote  IN													";
		String queryIds = " ( ";
		String queryOrderBy = "	ORDER BY lote.idLote																		";
		
		for(int i = 0; i < idLotes.size(); i++) {
			Long id = idLotes.get(i);
			if(i != idLotes.size() - 1) {
				queryIds = queryIds + id + " , ";
			}else {
				queryIds = queryIds + id + " ) ";
			}
		}
		
		queryJPA = queryJPA + queryIds + queryOrderBy;
		
		Query query = getCurrentSession().createQuery(queryJPA);
		listaRetorno = query.list();
		
		return listaRetorno;
	}

	@Override
	public List<LotesQuery> getQueryAvanzada(Date fechaInicial, Date fechaFinal, Long idEstatusLote) {
		String queryJPA = "	SELECT NEW 	mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery( 							"
				+ " 					lote.idLote , lote.fhCreacionLote , lote.nuTotImagenes , lote.nuTotImgAceptadas ,	"
				+ "						lote.nuTotImgRechazadas , lote.idStatProceso.idStatProceso , 						"
				+ "						lote.idStatProceso.cdEstatusProceso, lote.idStatProceso.nombreEstatus,				"
				+ "						lote.idStatProceso.descripcionStatus, lote.nbLote, lote.nuFolioInicial,				"
				+ "						lote.nuFolioFinal )									"
				+ "			FROM LotesDTO lote 																				" 
				+ "			WHERE 	lote.idStatProceso.cdEstatusProceso IN ('PROCESADO', 'LIBERADO', 'VALINFORMACION', 		"
				+ "					'FORMLIBERACION','ENLIBERACION', 'CANCELADO', 'INFOVALIDADA')							" ;
		
		String queryIdEstatus = " 	AND lote.idStatProceso.idStatProceso = 													";
		String queryAnd = "  AND  ";
		String queryDates= "		lote.fhCreacionLote BETWEEN :fechaInicial AND :fechaFinal								";
		String queryOrderBy = "	ORDER BY lote.idLote																		";
		Query query;
		
		if(idEstatusLote != null) {
			queryJPA += queryIdEstatus + idEstatusLote.toString() + "  ";
		}
			
		
		if(fechaInicial != null && fechaFinal != null) {
			queryJPA += queryAnd;
			
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
	private boolean updateEstatusLoteAIformacionValidada(LotesDTO loteDTO) {
		
		if(puedeCambiarAInformacionValidada(loteDTO)) {
			
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.VALIDACION_IMAGENES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_VALIDACION_IMAGENES.getValor(), 
					loteDTO.getIdStatProceso()==null?"":loteDTO.getIdStatProceso().getNombreEstatus(),//---
					ValoresEstadosLotes.INFORMACION_VALIDADA.getValor(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					loteDTO.getIdLote()==null?"":loteDTO.getIdLote().toString(),///--
					loteDTO.getNbLote()==null?"":loteDTO.getNbLote(), //--
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			
			setEstatus(loteDTO,ValoresEstadosLotes.INFORMACION_VALIDADA);
			
			return true;
		}else {
			return false;
		}
		
	}

	/*Solo pueden formarse para liberación aquellos lotes que tienen el estado "INFORMACION_VALIDADA" */
	@Override
	public boolean formarParaLiberacion(LotesDTO loteDTO) {
		String cdEstatus=loteDTO.getIdStatProceso()==null?"":loteDTO.getIdStatProceso().getCdEstatusProceso();
		
		if(cdEstatus.equals(ValoresEstadosLotes.INFORMACION_VALIDADA.getValor())) {//--
			bitacoraCambiosService.guardarBitacoraCambiosParametros(
					ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
					BitacoraComponentesEnum.CONSULTA_DE_LOTES.getValor(), 
					BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES.getValor(), 
					loteDTO.getIdStatProceso()==null?"":loteDTO.getIdStatProceso().getNombreEstatus(),
					ValoresEstadosLotes.FORMADO_PARA_LIBERACION.getValor(),
					usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
					loteDTO.getIdLote()==null?"":loteDTO.getIdLote().toString(),///--
					loteDTO.getNbLote()==null?"":loteDTO.getNbLote(),
					ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			setEstatus(loteDTO, ValoresEstadosLotes.FORMADO_PARA_LIBERACION);
			return true;
		}else {
			return false;
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
			String cdEstatus=lote.getIdStatProceso()==null?"":lote.getIdStatProceso().getCdEstatusProceso();
			if(cdEstatus.equals(ValoresEstadosLotes.PROCESADO.getValor())) {//--
				//TODO: Actualizar el estado del lote en la bitácora
				bitacoraCambiosService.guardarBitacoraCambiosParametros(
						ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
						BitacoraComponentesEnum.CONSULTA_DE_LOTES.getValor(), 
						BitacoraConceptosEnum.CAMBIO_DE_ESTATUS_DE_LOTES_CONSULTA_LOTES.getValor(), 
						lote.getIdStatProceso()==null?"":lote.getIdStatProceso().getNombreEstatus(),//---
						ValoresEstadosLotes.VALIDANDO_INFORMACION.getValor(),
						usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1L:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
						lote.getIdLote().toString()==null?"":lote.getIdLote().toString(),//--
						lote.getNbLote()==null?"":lote.getNbLote(), //--
						ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				setEstatus(lote, ValoresEstadosLotes.VALIDANDO_INFORMACION);
				update(lote);
				
			} 
		}
	}

	/*Solo un lote puede tener este estado*/

	@Override
	public void ponerEnLiberacion(List<LotesDTO> lotesDTO) {

	}

	@Override
	public LotesDTO enValidandoInformacion(LotesDTO loteDTO) {
		LotesDTO loteRetorno = new LotesDTO();
		
		if(loteDTO.getIdStatProceso().getCdEstatusProceso().equals(ValoresEstadosLotes.PROCESADO.getValor())) {
			setEstatus(loteDTO, ValoresEstadosLotes.VALIDANDO_INFORMACION);
			loteRetorno = update(loteDTO);
		}
		
		return loteRetorno;
	}

	
}
