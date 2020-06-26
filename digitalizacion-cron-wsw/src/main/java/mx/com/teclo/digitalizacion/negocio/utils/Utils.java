package mx.com.teclo.digitalizacion.negocio.utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import mx.com.teclo.digitalizacion.negocio.vo.imagenes.ImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.imagenes.LBImagenesVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.EstatusProcesoVO;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesQuery;
import mx.com.teclo.digitalizacion.negocio.vo.lotes.LotesResultVO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.ImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LBImagenesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.LotesDTO;
import mx.com.teclo.digitalizacion.persistencia.hibernate.dto.EstatusProcesoDTO;

public class Utils {

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

		//String.valueOf(bytes);
		//byte[] bytesEncoded = Base64.encodeBase64(bytes);
		String codedString;
		Base64 base64 = new Base64();
		codedString = base64.encodeToString(bytes);
		//String codedString= new String(bytesEncoded, StandardCharsets.UTF_8);
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

}
