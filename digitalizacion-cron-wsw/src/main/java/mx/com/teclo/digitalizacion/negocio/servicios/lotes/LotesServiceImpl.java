package mx.com.teclo.digitalizacion.negocio.servicios.lotes;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.digitalizacion.negocio.utils.Utils;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.LotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dao.lotes.StatusLotesHDAO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LoteDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;

@Service
public class LotesServiceImpl implements LotesService {
	
	@Autowired
	private LotesHDAO lotesHDAO;
	@Autowired
	private StatusLotesHDAO statusLotesHDAO;

	@Override
	@Transactional(readOnly = true)
	public List<LotesResultVO> getInformacionLotes() {
		List<LotesResultVO> listaRetorno = new ArrayList<>();
		List<LotesQuery> lotesQuery = lotesHDAO.getQueryGeneral();
		Utils.llenadoDatosListaLotesQueryToListaLotesResultVO(lotesQuery, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LotesResultVO> getInformacionLotes(Date fechaInicial, Date fechaFinal, Long idEstatus) {
		
		List<LotesResultVO> listaRetorno = new ArrayList<>();
		List<LotesQuery> lotesQuery = lotesHDAO.getQueryAvanzada(fechaInicial, fechaFinal, idEstatus);
		Utils.llenadoDatosListaLotesQueryToListaLotesResultVO(lotesQuery, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = false)
	public LotesResultVO cancelarLote(Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		
		LotesDTO loteDTO = lotesHDAO.findOne(idLote);
		LotesDTO loteActualizado = lotesHDAO.cancelarLote(loteDTO);
		Utils.llenadoDatosLotesDTOtoLotesResultVO(loteActualizado,retorno);
		
		return retorno;
		
	}
	
	

	@Override
	@Transactional(readOnly = false)
	public LotesResultVO formarParaLiberarLote(Long idLote) {
		LotesResultVO retorno = new LotesResultVO();
		
		LotesDTO loteDTO = lotesHDAO.findOne(idLote);
		LotesDTO loteActualizado = lotesHDAO.formarParaLiberarLote(loteDTO);
		Utils.llenadoDatosLotesDTOtoLotesResultVO(loteActualizado,retorno);
		
		return retorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EstatusProcesoVO> getTodosEstatus() {
		List<EstatusProcesoDTO> listaStat = statusLotesHDAO.findAll();
		List<EstatusProcesoVO> listaRetorno = new ArrayList<>();
		
		Utils.llenadoDatosListaStatProcesoDTOtoListaEstatusProcesoVO(listaStat, listaRetorno);
		
		return listaRetorno;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LotesDTO> getTodosLotesFormadosParaLiberar() {
		List<LotesDTO> listaRetorno = new ArrayList<>();
		
		
		return listaRetorno;
	}
	
	
	
}
