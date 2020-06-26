package mx.com.teclo.digitalizacion.negocio.utils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import mx.com.teclo.digitalizacion.negocio.vo.estadisticas.ConsultaGeneralVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.AsignacionesDatosVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesSinBlobVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.VehiculoMarcaVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboComponentesVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ComboConceptosVO;
import mx.com.teclo.digitalizacion.negocio.vo.reportesgenerales.ControlCatalogosVO;
import mx.com.teclo.digitalizacion.negocio.vo.validadores.ValidadorBusquedaVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraComponentesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.TdpBitacoraConceptosDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ValidadoresDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.VehiculoMarcaDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.CtrlReportesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;

public class Utils {
	
	public static void llenadoDatosListaImagenDTOtoListaImagenSinBlobVO(List<ImagenesDTO> listaOrigenDTO, 
			List<ImagenesSinBlobVO> listaDestinoVO) {
		if(listaDestinoVO == null || !listaDestinoVO.isEmpty())
			listaDestinoVO = new ArrayList<ImagenesSinBlobVO>();

		for(ImagenesDTO imagenDTOTmp : listaOrigenDTO) {
			ImagenesSinBlobVO imagenVO = new ImagenesSinBlobVO();
			try {
				Utils.llenadoDatosImagenDTOtoImagenSinBlobVO(imagenDTOTmp, imagenVO);
			}catch(Exception ex) {
				System.out.println("Ocurrió el siguiente error al convertir de ImagenesDTO a ImagenesSinBlobVO: "+ex.getMessage());
				listaDestinoVO = new ArrayList<ImagenesSinBlobVO>();
				return;
			}
			listaDestinoVO.add(imagenVO);
		}
		
	}
	
	public static void llenadoDatosImagenDTOtoImagenSinBlobVO(ImagenesDTO origenDTO, ImagenesSinBlobVO destinoVO) {
		if(destinoVO == null)
			destinoVO = new ImagenesSinBlobVO();
		
		destinoVO.setCodigoPlaca(origenDTO.getCodigoPlaca());
		destinoVO.setCodigoPlacaOficial(origenDTO.getCodigoPlacaOficial());
		destinoVO.setCodigoTipo(origenDTO.getCodigoTipo());
		destinoVO.setFhInfraccion(origenDTO.getFhInfraccion());
		destinoVO.setIdImagen(origenDTO.getIdImagen());
		destinoVO.setIdLote(origenDTO.getIdLote().getIdLote());
		destinoVO.setNbLicExpEn(origenDTO.getNbLicExpEn());
		destinoVO.setNbPlacaExpEn(origenDTO.getNbPlacaExpEn());
		destinoVO.setNbUtDelegacion(origenDTO.getNbUtDelegacion());
		destinoVO.setNombreImagen(origenDTO.getNombreImagen());
		destinoVO.setNuArtInfrac(origenDTO.getNuArtInfrac());
		destinoVO.setNuFraccion(origenDTO.getNuFraccion());
		destinoVO.setNuInciso(origenDTO.getNuInciso());
		destinoVO.setNumeroLicencia(origenDTO.getNumeroLicencia());
		destinoVO.setNuNumeroFolio(origenDTO.getNuNumeroFolio());
		destinoVO.setNuParrafo(origenDTO.getNuParrafo());
		
		if(origenDTO.getStValidada() != null) {
			if(origenDTO.getStValidada() == ValoresImagenValidada.ACEPTADA.getValor().shortValue()) {
				destinoVO.setStValidada("SÍ");
			}else if (origenDTO.getStValidada() == ValoresImagenValidada.RECHAZADA.getValor().shortValue()){
				destinoVO.setStValidada("NO");
			}
		}else {
			destinoVO.setStValidada("No validada");
		}
		
		if(origenDTO.getStLiberada() == 1) {/*Está liberada*/
			destinoVO.setStLiberada("SÍ");
		}else {
			destinoVO.setStLiberada("NO");
		}
		
		destinoVO.setFechaLiberacion(origenDTO.getFechaLiberacion());
		destinoVO.setStDuplicada(origenDTO.getStDuplicada());
		
		VehiculoMarcaVO marca = new VehiculoMarcaVO();
		if(origenDTO.getVehiculoMarca() != null) {
			marca.setCodigoMarca(origenDTO.getVehiculoMarca().getCodigoMarca());
			marca.setIdMarca(origenDTO.getVehiculoMarca().getIdMarca());
			marca.setNombreMarca(origenDTO.getVehiculoMarca().getNombreMarca());
		}
		
		destinoVO.setVehiculoMarca(marca);
	}

	public static void llenadoDatosImagenDTOtoImagenVO(ImagenesDTO origenDTO, ImagenesVO destinoVO) throws Exception {
		if(destinoVO == null)
			destinoVO = new ImagenesVO();
		destinoVO.setCodigoPlaca(origenDTO.getCodigoPlaca());
		destinoVO.setCodigoPlacaOficial(origenDTO.getCodigoPlacaOficial());
		destinoVO.setCodigoTipo(origenDTO.getCodigoTipo());
		destinoVO.setFhInfraccion(origenDTO.getFhInfraccion());
		destinoVO.setIdImagen(origenDTO.getIdImagen());
		destinoVO.setIdLote(origenDTO.getIdLote().getIdLote());
		destinoVO.setNbLicExpEn(origenDTO.getNbLicExpEn());
		destinoVO.setNbPlacaExpEn(origenDTO.getNbPlacaExpEn());
		destinoVO.setNbUtDelegacion(origenDTO.getNbUtDelegacion());
		destinoVO.setNombreImagen(origenDTO.getNombreImagen());
		destinoVO.setNuArtInfrac(origenDTO.getNuArtInfrac());
		destinoVO.setNuFraccion(origenDTO.getNuFraccion());
		destinoVO.setNuInciso(origenDTO.getNuInciso());
		destinoVO.setNumeroLicencia(origenDTO.getNumeroLicencia());
		destinoVO.setNuNumeroFolio(origenDTO.getNuNumeroFolio());
		destinoVO.setNuParrafo(origenDTO.getNuParrafo());
		destinoVO.setStLiberada(origenDTO.getStLiberada());
		destinoVO.setStValidada(origenDTO.getStValidada());
		destinoVO.setFechaLiberacion(origenDTO.getFechaLiberacion());
		destinoVO.setStDuplicada(origenDTO.getStDuplicada());
		/*Llenado de los elementos de la colección*/
		
		int tam = origenDTO.getLbImagenesCollection().size();
		if(tam != 2)
			throw new Exception("Cada boleta a validar debe contener un anverso y un reverso: "+ origenDTO);
		
		for(LBImagenesDTO imagen : origenDTO.getLbImagenesCollection()) {
			if(imagen.getCodigoImagen().equals( PosicionImagen.ANVERSO.getValor())) {
				LBImagenesVO anverso = new LBImagenesVO();
				llenadoDatosLBImagenDTOtoLBImagenVO(imagen, anverso);
				destinoVO.setLbImagenAnverso(anverso);
			}
			if(imagen.getCodigoImagen().equals(PosicionImagen.REVERSO.getValor())) {
				LBImagenesVO reverso = new LBImagenesVO();
				llenadoDatosLBImagenDTOtoLBImagenVO(imagen, reverso);
				destinoVO.setLbImagenReverso(reverso);
			}
		}
		
		VehiculoMarcaVO marca = new VehiculoMarcaVO();
		if(origenDTO.getVehiculoMarca() != null) {
			marca.setCodigoMarca(origenDTO.getVehiculoMarca().getCodigoMarca());
			marca.setIdMarca(origenDTO.getVehiculoMarca().getIdMarca());
			marca.setNombreMarca(origenDTO.getVehiculoMarca().getNombreMarca());
		}
		
		destinoVO.setVehiculoMarca(marca);
		destinoVO.setTxRechazada(origenDTO.getTxRechazada());
		destinoVO.setTxPospuesta(origenDTO.getTxPospuesta());
		String validadorEvaluo = origenDTO.getIdValidador() == null ? "" : origenDTO.getIdValidador().getUsuario().getCdUsuario();
		destinoVO.setIdValidadorEvaluo(validadorEvaluo);
		String validadorPosp = origenDTO.getIdValidadorPosp() == null ? "" : origenDTO.getIdValidadorPosp().getUsuario().getCdUsuario();
		destinoVO.setIdValidadorPospuso(validadorPosp);
	}
	
	public static void llenadoDatosLBImagenDTOtoLBImagenVO(LBImagenesDTO origenDTO, LBImagenesVO destinoVO) {
		if(destinoVO == null)
			destinoVO = new LBImagenesVO();
		
		destinoVO.setCodigoImagen(origenDTO.getCodigoImagen());
		destinoVO.setIdLbImagen(origenDTO.getIdLbImagen());
		
		/*Convertir el arreglo de bytes a String y codificarlo a base64*/
		byte[] bytes = origenDTO.getLbImagen();
		
		String imgCoded = codificaByteArrayToStringBase64(bytes);
		
		destinoVO.setLbImagen(imgCoded);
		destinoVO.setNombreImagen(origenDTO.getNombreImagen());
		destinoVO.setIdImagen(origenDTO.getIdImagen().getIdImagen());
	}
	
	public static String codificaByteArrayToStringBase64(byte[] bytes) {
		String codedString;
		Base64 base64 = new Base64();

		codedString = base64.encodeToString(bytes);

		return codedString;
	}
	
	public static void llenadoDatosImagenVOtoImagenDTO(ImagenesVO origenVO, ImagenesDTO destinoDTO) {
		if(destinoDTO == null)
			destinoDTO = new ImagenesDTO();
		
		destinoDTO.setNumeroLicencia(origenVO.getNumeroLicencia());
		destinoDTO.setCodigoPlaca(origenVO.getCodigoPlaca());
		destinoDTO.setCodigoPlacaOficial(origenVO.getCodigoPlacaOficial());
		destinoDTO.setCodigoTipo(origenVO.getCodigoTipo());
		destinoDTO.setFhInfraccion(origenVO.getFhInfraccion());
		destinoDTO.setNbLicExpEn(origenVO.getNbLicExpEn());
		destinoDTO.setNbPlacaExpEn(origenVO.getNbPlacaExpEn());
		destinoDTO.setNbUtDelegacion(origenVO.getNbUtDelegacion());
		destinoDTO.setNuArtInfrac(origenVO.getNuArtInfrac());
		destinoDTO.setNuFraccion(origenVO.getNuFraccion());
		destinoDTO.setNuInciso(origenVO.getNuInciso());
		destinoDTO.setNuNumeroFolio(origenVO.getNuNumeroFolio());
		destinoDTO.setNuParrafo(origenVO.getNuParrafo());
		destinoDTO.setStDuplicada(origenVO.getStDuplicada());
		
	}
	
	public static void llenadoDatosListaImagenDTOtoListaImagenVO(List<ImagenesDTO> listaOrigenDTO, List<ImagenesVO> listaDestinoVO) {
		if(listaDestinoVO == null || !listaDestinoVO.isEmpty())
			listaDestinoVO = new ArrayList<ImagenesVO>();

		for(ImagenesDTO imagenDTOTmp : listaOrigenDTO) {
			ImagenesVO imagenVO = new ImagenesVO();
			try {
				Utils.llenadoDatosImagenDTOtoImagenVO(imagenDTOTmp, imagenVO);
			}catch(Exception ex) {
				System.out.println("Ocurrió el siguiente error al convertir de ImagenesDTO a ImagenesVO: "+ex.getMessage());
				listaDestinoVO = new ArrayList<ImagenesVO>();
				return;
			}
			listaDestinoVO.add(imagenVO);
		}

	}
	
	public static void llenadoDatosLotesQueryToLotesResultVO(LotesQuery origen, LotesResultVO destino) {
		if(destino == null)
			destino = new LotesResultVO();
		
		destino.setCantImagenesAceptadas(origen.getCantImagenesAceptadas());
		destino.setCantImagenesRechazadas(origen.getCantImagenesRechazadas());
		destino.setFechaLote(origen.getFechaLote());
		destino.setNumeroLote(origen.getNumeroLote());
		destino.setTotalImagenes(origen.getTotalImagenes());
		destino.setNombreLote(origen.getNombreLote());
		destino.setNuFolioInicial(origen.getNuFolioInicial());
		destino.setNuFolioFinal(origen.getNuFolioFinal());
		
		EstatusProcesoVO estatus = new EstatusProcesoVO();
		estatus.setIdEstatus(origen.getIdEstatus());
		estatus.setCodigoEstatus(origen.getCodigoEstatus());
		estatus.setNombreEstatus(origen.getNombreEstatus());
		estatus.setDescripcionStatus(origen.getDescripcionStatus());
		
		destino.setEstatusProceso(estatus);
		
	}
	
	public static void llenadoDatosListaLotesQueryToListaLotesResultVO(List<LotesQuery> listaOrigen,
			List<LotesResultVO> listaDestino) {
		
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(LotesQuery origen: listaOrigen) {
			LotesResultVO destino = new LotesResultVO();
			llenadoDatosLotesQueryToLotesResultVO(origen, destino);
			listaDestino.add(destino);
		}
		
	}
	
	public static void llenadoDatosStatProcesoDTOtoEstatusProcesoVO(EstatusProcesoDTO origen, EstatusProcesoVO destino) {
		if(destino == null)
			destino = new EstatusProcesoVO();
		
		destino.setCodigoEstatus(origen.getCdEstatusProceso());
		destino.setIdEstatus(origen.getIdStatProceso());
		destino.setDescripcionStatus(origen.getDescripcionStatus());
		destino.setNombreEstatus(origen.getNombreEstatus());
	}
	
	public static void llenadoDatosListaStatProcesoDTOtoListaEstatusProcesoVO(List<EstatusProcesoDTO> listaOrigen, 
			List<EstatusProcesoVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(EstatusProcesoDTO origen : listaOrigen) {
			EstatusProcesoVO destino = new EstatusProcesoVO();
			llenadoDatosStatProcesoDTOtoEstatusProcesoVO(origen, destino);
			listaDestino.add(destino);
		}
		
	}
	
	public static void llenadoDatosLotesDTOtoLotesResultVO(LotesDTO origen, LotesResultVO destino) {
		if (destino == null)
			destino = new LotesResultVO();
		
		destino.setNumeroLote(origen.getIdLote());
		destino.setFechaLote(origen.getFhCreacionLote());
		destino.setTotalImagenes(origen.getNuTotImagenes());
		destino.setCantImagenesAceptadas(origen.getNuTotImgAceptadas());
		destino.setCantImagenesRechazadas(origen.getNuTotImgRechazadas());
		
		EstatusProcesoVO estatusProceso = new EstatusProcesoVO();
		llenadoDatosStatProcesoDTOtoEstatusProcesoVO(origen.getIdStatProceso(), estatusProceso);
		
		destino.setEstatusProceso(estatusProceso);
	}
	
	public static void llenadoDatosListaLBImagenDTOtoListaLBImagenVO(List<LBImagenesDTO> listaOrigenDTO, List<LBImagenesVO> listaDestinoVO) {
		if(listaDestinoVO == null)
			listaDestinoVO = new ArrayList<LBImagenesVO>();
		
		for(LBImagenesDTO origen: listaOrigenDTO) {
			LBImagenesVO destino = new LBImagenesVO();
			llenadoDatosLBImagenDTOtoLBImagenVO(origen, destino);
			listaDestinoVO.add(destino);
		}
	}
	
	public static void llenadoDatosConsultaObjToAsignacionesDatosVO(Object[] origen, AsignacionesDatosVO destino ) {
		if(destino == null) {
			destino = new AsignacionesDatosVO();
		}
		
		Long idValidador = (Long) origen[0];
		String nickName = (String) origen[1];
		String nombre = (String) origen[2];
		String apellidoP = (String) origen[3];
		String email = (String) origen[4];
		Long cantAsignadas = (Long) origen[5];
		Long cantValidandose = (Long) origen[6];
		Date fechaUltimaOperacion = (Date) origen[7];
				
		destino.setIdValidador(idValidador.longValue()); 
		destino.setNickName(nickName);
		destino.setNombre(nombre);
		destino.setApellidoP(apellidoP);
		destino.setEmail(email);
		destino.setCantImagenesAsignadas(cantAsignadas.longValue());
		destino.setCantImagenesValidandose(cantValidandose.longValue());
		destino.setFechaUltimaOperacion(fechaUltimaOperacion);
	}
	
	public static void llenadoDatosListaDatosConsultaObjToListaAsignacionesDatosVO(List<Object[]> listaOrigen, List<AsignacionesDatosVO> listaDestino) {
		if(listaDestino == null) {
			listaDestino = new ArrayList<>();
		}
		
		for(int i = 0; i<listaOrigen.size(); i++) {
			AsignacionesDatosVO destino = new AsignacionesDatosVO();
			llenadoDatosConsultaObjToAsignacionesDatosVO(listaOrigen.get(i),destino);
			listaDestino.add(destino);
		}
	}
	
	public static void llenadoDatosConsultaGralObToConsultaGralVO(Object[] origen, ConsultaGeneralVO destino) {
		if(destino == null)
			destino = new ConsultaGeneralVO();
		
		destino.setIdUsuario(((BigDecimal)origen[0]).longValue());
		destino.setIdValidador(((BigDecimal)origen[1]).longValue());
		destino.setCodigoUsuario((String)origen[2]);
		destino.setEmail((String)origen[3]);
		destino.setNombre((String)origen[4]);
		destino.setApellidoP((String)origen[5]);
		destino.setApellidoM((String)origen[6]);
		destino.setEvaluacion((String)origen[7]);
		destino.setCantidad(((BigDecimal)origen[8]).longValue());
	}
	
	public static void llenadoDatosConsultaGralObToConsultaGralVOJPA(Object[] origen, ConsultaGeneralVO destino) {
		if(destino == null)
			destino = new ConsultaGeneralVO();
		
		destino.setIdUsuario((Long) origen[0]);
		destino.setIdValidador((Long) origen[1]);
		destino.setCodigoUsuario((String)origen[2]);
		destino.setEmail((String)origen[3]);
		destino.setNombre((String)origen[4]);
		destino.setApellidoP((String)origen[5]);
		destino.setApellidoM((String)origen[6]);
		destino.setEvaluacion((String)origen[7]);
		destino.setCantidad((Long) origen[8]);
	}
	
	public static void llenadoDatosListaConsultaGralObToListaConsultaGralVO(List<Object[]> listaOrigen, 
						List<ConsultaGeneralVO> listaDestino) {
		
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(Object[] origen : listaOrigen) {
			ConsultaGeneralVO destino = new ConsultaGeneralVO();
			llenadoDatosConsultaGralObToConsultaGralVO(origen, destino);
			listaDestino.add(destino);
		}
		
	}
	
	public static void llenadoDatosVehiculoMarcaDTOtoVehiculoMarcaVO(VehiculoMarcaDTO origen,VehiculoMarcaVO destino) {
		if(destino == null)
			destino = new VehiculoMarcaVO();
		
		destino.setCodigoMarca(origen.getCodigoMarca());
		destino.setIdMarca(origen.getIdMarca());
		destino.setNombreMarca(origen.getNombreMarca());
	}
	
	public static void llenadoDatosListaVehiculoMarcaDTOtoListaVehiculoMarcaVO(List<VehiculoMarcaDTO> listaOrigen,
			List<VehiculoMarcaVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();
		
		for(VehiculoMarcaDTO origen : listaOrigen) {
			VehiculoMarcaVO destino = new VehiculoMarcaVO();
			llenadoDatosVehiculoMarcaDTOtoVehiculoMarcaVO(origen, destino);
			listaDestino.add(destino);
		}
		

	}

	public static void llenadoDatosListaConsultaGralObToListaConsultaGralVOJPA(List<Object[]> listaOrigen, 
			List<ConsultaGeneralVO> listaDestino) {
		if(listaDestino == null)
			listaDestino = new ArrayList<>();

		for(Object[] origen : listaOrigen) {
			ConsultaGeneralVO destino = new ConsultaGeneralVO();
			llenadoDatosConsultaGralObToConsultaGralVOJPA(origen, destino);
			listaDestino.add(destino);
		}

	}
	
	public static void llenadoDatosCtrlReportesDTOToControlCatalogosVO(CtrlReportesDTO origen, CtrlReportesDTO padreOrigen,
			ControlCatalogosVO destino ) {
		destino.setIdCtrlCatReporte(origen.getIdCtrlReporte());
		destino.setIdPadre(origen.getIdPadre());
		destino.setNombre(origen.getNombre());
		destino.setRuta(origen.getRuta());
		if(padreOrigen != null) {
			ControlCatalogosVO padre = new ControlCatalogosVO();
			llenadoDatosCtrlReportesDTOToControlCatalogosVO(padreOrigen, null, padre);
			destino.setObject(padre);
		}else {
			destino.setObject(null);
		}
		
	}
	
	public static void llenadoDatosTdpBitacoraComponentesDTOToComboComponentesVO(List<TdpBitacoraComponentesDTO> listaOrigen,
			List<ComboComponentesVO> listaDestino) {
		
		for(TdpBitacoraComponentesDTO dto : listaOrigen) {
			ComboComponentesVO vo = new ComboComponentesVO();
			vo.setComponenteId(dto.getComponenteId());
			vo.setComponenteNombre(dto.getComponenteNombre());
			
			listaDestino.add(vo);
		}
		
	}
	
	public static void llenadoDatosTdpBitacoraConceptosDTOToComboConceptosVO(List<TdpBitacoraConceptosDTO> listaOrigen, 
			List<ComboConceptosVO> listaDestino) {
		
		for(TdpBitacoraConceptosDTO dto : listaOrigen) {
			ComboConceptosVO vo = new ComboConceptosVO();
			vo.setComponenteId(dto.getBitacoraConponentesDTO().getComponenteId());
			vo.setConceptoId(dto.getPkTcaBitacoraConceptosDTO().getConceptoId());
			vo.setConceptoNombre(dto.getConceptoNombre());
			
			listaDestino.add(vo);
		}
		
	}

	public static void llenadoDatosListaImagenDTOtoListaImagenVOSinBlob(List<ImagenesDTO> listaOrigenDTO, List<ImagenesVO> listaDestinoVO) {
		if(listaDestinoVO == null || !listaDestinoVO.isEmpty())
			listaDestinoVO = new ArrayList<ImagenesVO>();

		for(ImagenesDTO imagenDTOTmp : listaOrigenDTO) {
			ImagenesVO imagenVO = new ImagenesVO();
			try {
				Utils.llenadoDatosImagenDTOtoImagenVOSinBlob(imagenDTOTmp, imagenVO);
			}catch(Exception ex) {
				System.out.println("Ocurrió el siguiente error al convertir de ImagenesDTO a ImagenesVO: "+ex.getMessage());
				listaDestinoVO = new ArrayList<ImagenesVO>();
				return;
			}
			listaDestinoVO.add(imagenVO);
		}
		
	}
	
	public static void llenadoDatosImagenDTOtoImagenVOSinBlob(ImagenesDTO origenDTO, ImagenesVO destinoVO) throws Exception {
		if(destinoVO == null)
			destinoVO = new ImagenesVO();
		destinoVO.setCodigoPlaca(origenDTO.getCodigoPlaca());
		destinoVO.setCodigoPlacaOficial(origenDTO.getCodigoPlacaOficial());
		destinoVO.setCodigoTipo(origenDTO.getCodigoTipo());
		destinoVO.setFhInfraccion(origenDTO.getFhInfraccion());
		destinoVO.setIdImagen(origenDTO.getIdImagen());
		destinoVO.setIdLote(origenDTO.getIdLote().getIdLote());
		destinoVO.setNbLicExpEn(origenDTO.getNbLicExpEn());
		destinoVO.setNbPlacaExpEn(origenDTO.getNbPlacaExpEn());
		destinoVO.setNbUtDelegacion(origenDTO.getNbUtDelegacion());
		destinoVO.setNombreImagen(origenDTO.getNombreImagen());
		destinoVO.setNuArtInfrac(origenDTO.getNuArtInfrac());
		destinoVO.setNuFraccion(origenDTO.getNuFraccion());
		destinoVO.setNuInciso(origenDTO.getNuInciso());
		destinoVO.setNumeroLicencia(origenDTO.getNumeroLicencia());
		destinoVO.setNuNumeroFolio(origenDTO.getNuNumeroFolio());
		destinoVO.setNuParrafo(origenDTO.getNuParrafo());
		destinoVO.setStLiberada(origenDTO.getStLiberada());
		destinoVO.setStValidada(origenDTO.getStValidada());
		destinoVO.setFechaLiberacion(origenDTO.getFechaLiberacion());
		destinoVO.setStDuplicada(origenDTO.getStDuplicada());
		/*Llenado de los elementos de la colección*/
		
		int tam = origenDTO.getLbImagenesCollection().size();
		if(tam != 2)
			throw new Exception("Cada boleta a validar debe contener un anverso y un reverso: "+ origenDTO);
		
		destinoVO.setLbImagenAnverso(null);
		destinoVO.setLbImagenReverso(null);
		
		/*
		 * for(LBImagenesDTO imagen : origenDTO.getLbImagenesCollection()) {
		 * if(imagen.getCodigoImagen().equals( PosicionImagen.ANVERSO.getValor())) {
		 * //LBImagenesVO anverso = new LBImagenesVO();
		 * //llenadoDatosLBImagenDTOtoLBImagenVO(imagen, anverso);
		 * destinoVO.setLbImagenAnverso(null); }
		 * if(imagen.getCodigoImagen().equals(PosicionImagen.REVERSO.getValor())) {
		 * //LBImagenesVO reverso = new LBImagenesVO();
		 * //llenadoDatosLBImagenDTOtoLBImagenVO(imagen, reverso);
		 * destinoVO.setLbImagenReverso(null); } }
		 */
		
		VehiculoMarcaVO marca = new VehiculoMarcaVO();
		if(origenDTO.getVehiculoMarca() != null) {
			marca.setCodigoMarca(origenDTO.getVehiculoMarca().getCodigoMarca());
			marca.setIdMarca(origenDTO.getVehiculoMarca().getIdMarca());
			marca.setNombreMarca(origenDTO.getVehiculoMarca().getNombreMarca());
		}
		
		destinoVO.setVehiculoMarca(marca);
		destinoVO.setTxRechazada(origenDTO.getTxRechazada());
		destinoVO.setTxPospuesta(origenDTO.getTxPospuesta());
		destinoVO.setIdValidadorPospuso( origenDTO.getIdValidadorPosp().getUsuario().getApellidoPaterno() + " " +origenDTO.getIdValidadorPosp().getUsuario().getNombreUsuario());
		
	}

	public static void llenadoDatosListaValidadoresDTOToListaValidadorBusquedaVO(List<ValidadoresDTO> listaOrigen,
			List<ValidadorBusquedaVO> listaDestino) {
		for(ValidadoresDTO origen : listaOrigen) {
			ValidadorBusquedaVO destino = new ValidadorBusquedaVO();
			llenadoDatosValidadoresDTOToValidadorBusquedaVO(origen, destino);
			listaDestino.add(destino);
		}
		
	}
	
	public static void llenadoDatosValidadoresDTOToValidadorBusquedaVO(ValidadoresDTO origen, ValidadorBusquedaVO destino) {
		destino.setCdUsuario(origen.getUsuario().getCdUsuario());
		destino.setIdValidador(origen.getIdValidador());
	}
}
