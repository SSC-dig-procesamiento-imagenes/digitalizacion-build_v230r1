package mx.com.teclo.digitalizacion.rest.lotes;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.digitalizacion.persistencia.vo.bitacora.BitacoraVO;
import mx.com.teclo.digitalizacion.persistencia.vo.estatusproceso.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.ActualizarFoliosVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.ActualizarLoteVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.LoteBitacoraVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.LoteVO;
import mx.com.teclo.digitalizacion.persistencia.vo.lotes.PersistirLoteLoteVO;
import mx.com.teclo.digitalizacion.service.lote.LotesService;


@RestController
@RequestMapping("/lotes")
public class LotesRestController {
	
	@Autowired
	private LotesService lotesService;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@RequestMapping(value = "/obtenerTodosLotes", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<LoteVO>> getLotesAll(@RequestParam(value = "idstat") Long idstat,
			@RequestParam(value = "fechaIni") String fechaIni, @RequestParam(value = "fechaFin") String fechaFin) {
		//
		List<LoteVO> lotes = null;
		if(idstat==-10L) {
			lotes = lotesService.getLotesAll();
		}else {
			lotes = lotesService.getLotesPorParametros(idstat,fechaIni,fechaFin);
		}
		
		return new ResponseEntity<List<LoteVO>>(lotes,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guardarLote", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<LoteVO> guardarLote(@RequestBody LoteBitacoraVO lotbit) {
		
		PersistirLoteLoteVO persistirLoteLoteVO = new PersistirLoteLoteVO();
		

		persistirLoteLoteVO.setFechaCracionLote(lotbit.getFechaCracionLote());
		persistirLoteLoteVO.setIdConfiguracionEscaner(lotbit.getIdConfiguracionEscaner());
		persistirLoteLoteVO.setIdEtatusProceso(lotbit.getIdEtatusProceso());
		persistirLoteLoteVO.setIdLote(lotbit.getIdLote());
		persistirLoteLoteVO.setMetDigitalizacion(lotbit.getMetDigitalizacion());
		persistirLoteLoteVO.setNombreLote(lotbit.getNombreLote());
		persistirLoteLoteVO.setNumeroTotalAceptadas(lotbit.getNumeroTotalAceptadas());
		persistirLoteLoteVO.setNumeroTotalImagenes(lotbit.getNumeroTotalImagenes());
		persistirLoteLoteVO.setNumeroTotalRechazadas(lotbit.getNumeroTotalRechazadas());
		persistirLoteLoteVO.setRutaAlmacenamiento(lotbit.getRutaAlmacenamiento());
		persistirLoteLoteVO.setNuFolioInicial(lotbit.getNuFolioInicial());
		persistirLoteLoteVO.setNuFolioFinal(lotbit.getNuFolioFinal());
		persistirLoteLoteVO.setIdUsuario(lotbit.getIdUsuario());	
		
		LoteVO loteVO = null;
		
		loteVO = lotesService.guadarLote(persistirLoteLoteVO, lotbit.getModificadoPor());
		
		 BitacoraVO bitacoraVO = new  BitacoraVO();
		 lotbit.setIdRegistro(loteVO.getIdLote());
		 
		if(loteVO.getIdLote()!=null) {
        	int result=bitacoraCambiosService.guardarBitacoraCambiosParametros(
        			lotbit.getTabla()==null?"":lotbit.getTabla(), // Nombre de la tabla
        			lotbit.getComponenteId()==null?1L:lotbit.getComponenteId(), // Componente
        			lotbit.getConceptoId()==null?1L:lotbit.getConceptoId(), // Concepto
        			lotbit.getValorOriginal()==null?"":lotbit.getValorOriginal(), // Valor Original del registro.
        			lotbit.getValorFinal()==null?"":lotbit.getValorFinal(), // Valor final del registro. 
        			lotbit.getModificadoPor()==null?1L:lotbit.getModificadoPor(), // Id del usuario que  realiza el cambio 
        			lotbit.getIdRegistro()==null?"":lotbit.getIdRegistro().toString(), // IdRegistro   
        			lotbit.getRegistroDescripcion()==null?"":lotbit.getRegistroDescripcion(),	// Descripcion del registro modificado.
        			lotbit.getOrigen()==null?"":lotbit.getOrigen()); // Origen W / H
        }
		
		return new ResponseEntity<LoteVO>(loteVO, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/actualizarEstTolImgLote", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Boolean> actualizarLote(@RequestBody ActualizarLoteVO actualizarLoteVO) {
		lotesService.actualizarLote(actualizarLoteVO, actualizarLoteVO.getModificadoPor());
		
		int result=bitacoraCambiosService.guardarBitacoraCambiosParametros(
				actualizarLoteVO.getTabla()==null?"":actualizarLoteVO.getTabla(), // Nombre de la tabla
				actualizarLoteVO.getComponenteId()==null?1L:actualizarLoteVO.getComponenteId(), // Componente
				actualizarLoteVO.getConceptoId()==null?1L:actualizarLoteVO.getConceptoId(), // Concepto
				actualizarLoteVO.getValorOriginal()==null?"":actualizarLoteVO.getValorOriginal(), // Valor Original del registro.
				actualizarLoteVO.getValorFinal()==null?"":actualizarLoteVO.getValorFinal(), // Valor final del registro. 
				actualizarLoteVO.getModificadoPor()==null?1L:actualizarLoteVO.getModificadoPor(), // Id del usuario que  realiza el cambio 
				actualizarLoteVO.getIdLote()==null?"":actualizarLoteVO.getIdLote().toString(), // IdRegistro   
				actualizarLoteVO.getRegistroDescripcion()==null?"":actualizarLoteVO.getRegistroDescripcion(),	// Descripcion del registro modificado.
				actualizarLoteVO.getOrigen()==null?"":actualizarLoteVO.getOrigen()); // Origen W / H
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/actualizarFolios", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ResponseEntity<Boolean> actualizarFolios(@RequestBody ActualizarFoliosVO actualizarFoliosVO) {
		
		lotesService.actualizarFolios(actualizarFoliosVO, actualizarFoliosVO.getModificadoPor());
		
		return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
	}

	
	@RequestMapping(value = "/todosEstadosLotes", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ResponseEntity<List<EstatusProcesoVO>> getTodosEstadosLotes() {
//		List<EstatusProcesoVO> lotes = lotesService.getTodosEstatus();
		List<EstatusProcesoVO> lotes = lotesService.getTodosEstatusDigitalizacion();
		return new ResponseEntity<List<EstatusProcesoVO>>(lotes,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/boletasPorLoteExcel", method = RequestMethod.GET, produces = "application/json")
	@Transactional(readOnly=true)
	public @ResponseBody ResponseEntity<byte[]>getficheroExcelBusqueda(@RequestParam(value = "idLote") Long idLote) {
		HttpStatus estado = HttpStatus.OK;
		String nombreReporte = "Reporte_Boletas_Por_Lote";
		
		ByteArrayOutputStream ficheroExcelBytes = lotesService.getBoletasPorLoteExcel(idLote, nombreReporte);
		
		byte[] bytes = ficheroExcelBytes.toByteArray();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
		headers.add("Content-Disposition", "attachmnt; filename =" + nombreReporte +".xls");
		headers.add("filename", nombreReporte +".xls");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, estado);

		return response;
		
	}
}
