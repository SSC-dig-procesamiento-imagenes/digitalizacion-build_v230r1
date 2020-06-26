package mx.com.teclo.digitalizacion.api.rest.procesamientojnlp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.teclo.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;
import mx.com.teclo.arquitectura.ortogonales.service.comun.UsuarioFirmadoService;
import mx.com.teclo.digitalizacion.bitacora.BitacoraComponentesEnum;
import mx.com.teclo.digitalizacion.bitacora.BitacoraConceptosEnum;
import mx.com.teclo.digitalizacion.bitacora.ParametrosBitacoraEnum;

@RestController
@RequestMapping("/jnlp")
public class JnlpRestController {	
	@Autowired
    private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	 private Environment env;

    @Autowired
	private BitacoraCambiosService  bitacoraCambiosService;
	
    @Value("${jnlp.app.context}")
    private String contextAppJnlp;
    
    @Value("${jnlp.dir.desktop}")
    private String contextJnlp;
    
    @Value("${jnlp.dir.index}")
    private String contextIndexJnlp;
    
    @Value("${jnlp.dir.route}")
    private String contextRouteJnlp;
    
	@RequestMapping(value = "/creararchivo", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('LANZAM_APP')")
	@Transactional(readOnly=false)
	public  ResponseEntity<Map> crearArchivo() {
		
		Map map = new HashMap();
		map.put("success", true);
		  
		System.out.println("estas en el controlador JNLP");
		Long idUsuario = usuarioFirmadoService.getUsuarioFirmadoVO().getId();
		System.out.println("usuario firmado: "+usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilNombre());
		
		Document document = new Document();
		Element root = new Element("jnlp");
		root.setAttribute(new Attribute("spec", "1.0+"));
		root.setAttribute(new Attribute("codebase",contextAppJnlp ));
		root.setAttribute(new Attribute("href", "jnlp/"+"aplicacion.jnlp")); 

		document.setRootElement(root);
		System.out.println("Estas seteando contenido");
		Element information = new Element("information");
		information.addContent(new Element("title").setText("Aplicacion Digitalizacion"));
		information.addContent(new Element("vendor").setText("Teclo Mexicana S.A. de C.V."));
		information.addContent(new Element("homepage").setAttribute("href", "http://teclomexicana.mx"));
		information.addContent(new Element("description").setText("Aplicacion Digitalizacion"));
		Element shortcut = new Element("shortcut");
		shortcut.setAttribute("online", "true");
		information.addContent(shortcut);
		Element shortcut2 = new Element("shortcut");
		shortcut2.setAttribute("online", "true");
		shortcut2.setAttribute("install", "true");
		Element desktop = new Element("desktop");
		shortcut2.addContent(desktop);
		
		Element menu = new Element("menu");
		menu.setAttribute("submenu", "Aplicacion Digitalizacion");
		shortcut2.addContent(menu);
		information.addContent(shortcut2);
		
		Element offline = new Element("offline-allowed");
		information.addContent(offline);
		
		document.getRootElement().addContent(information);

		Element security = new Element("security");
		security.addContent(new Element("all-permissions")); //all-permissions
		document.getRootElement().addContent(security);
		
		Element update = new Element("update");
		update.setAttribute("check", "background"); //background
		update.setAttribute("policy", "prompt-update");
		document.getRootElement().addContent(update);
		
		Element resourcesx86 = new Element("resources");
		resourcesx86.setAttribute("arch", "x86"); 
		
		Element resourcesx64 = new Element("resources");
		resourcesx64.setAttribute("arch", "amd64"); 
		
		Element j2sex86 = new Element("j2se");
		j2sex86.setAttribute("version", "1.7+");
		j2sex86.setAttribute("href", "http://java.sun.com/products/autodl/j2se");
		j2sex86.setAttribute("initial-heap-size", "32m");
		j2sex86.setAttribute("max-heap-size", "128m");
		j2sex86.setAttribute("java-vm-args", "-ea");
		
		Element j2sex64 = new Element("j2se");
		j2sex64.setAttribute("version", "1.7+");
		j2sex64.setAttribute("href", "http://java.sun.com/products/autodl/j2se");
		j2sex64.setAttribute("initial-heap-size", "32m");
		j2sex64.setAttribute("max-heap-size", "128m");
		j2sex64.setAttribute("java-vm-args", "-ea");
		
		Element jarx86 = new Element("jar");
		jarx86.setAttribute("href", "jnlp/jar/digitalizacion-app-1.1.0.jar"); //jnlp/centrodepagos-app-0.0.1-SNAPSHOT.jar, jar.setAttribute("href", "file:///c:/pagos/centrodepagos-app-0.0.1-SNAPSHOT.jar");
		jarx86.setAttribute("main", "true");
		
		Element jarx64 = new Element("jar");
		jarx64.setAttribute("href", "jnlp/jar/digitalizacion-app-1.1.0.jar"); //jnlp/centrodepagos-app-0.0.1-SNAPSHOT.jar, jar.setAttribute("href", "file:///c:/pagos/centrodepagos-app-0.0.1-SNAPSHOT.jar");
		jarx64.setAttribute("main", "true");
		
		Element propertyx86 = new Element("property");
		propertyx86.setAttribute("name", "jnlp.packEnabled");
		propertyx86.setAttribute("value", "true");
		
		Element propertyx64 = new Element("property");
		propertyx64.setAttribute("name", "jnlp.packEnabled");
		propertyx64.setAttribute("value", "true");
		
		
		resourcesx86.addContent(j2sex86);
		resourcesx86.addContent(jarx86);
		resourcesx86.addContent(propertyx86);
		document.getRootElement().addContent(resourcesx86);
		
		resourcesx64.addContent(j2sex64);
		resourcesx64.addContent(jarx64);
		resourcesx64.addContent(propertyx64);
		document.getRootElement().addContent(resourcesx64);

		Element application_desc = new Element("application-desc");
		application_desc.setAttribute("name", "Digitalizacion");
		application_desc.setAttribute("main-class", "mx.com.frames.ConsultaLotesFrame");
		application_desc.addContent(new Element("argument").setText(usuarioFirmadoService.getUsuarioFirmadoVO().getPerfilNombre()));
		application_desc.addContent(new Element("argument").setText(usuarioFirmadoService.getUsuarioFirmadoVO().getId()+""));
		document.getRootElement().addContent(application_desc);
		System.out.println("Terminas de setear archivo");
		
		XMLOutputter xmlOutput = new XMLOutputter();
		xmlOutput.setFormat(Format.getPrettyFormat());
		String directory = context.getRealPath("/");
		try {
			int posi=directory.indexOf(contextIndexJnlp);
			String newDirectory=directory.substring(0, posi);
			newDirectory+=contextRouteJnlp+File.separator+contextJnlp+"aplicacion.jnlp";
			//newDirectory=directory.replace("\\digitalizacion-api\\src\\main\\webapp", "\\digitalizacion-app\\src\\main\\webapp");
			//xmlOutput.output(document, new FileWriter(newDirectory + File.separator + "jnlp\\"+"aplicacion.jnlp"));
			xmlOutput.output(document, new FileWriter(newDirectory));
		} catch (IOException e) {
			System.out.println("entro al catch");
			map.put("success", false);
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		}
		
		crearBitacoraLanzamientoApp(); 
		
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	
	}
	
	private void crearBitacoraLanzamientoApp() {
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TDP_BITACORA_CAMBIOS.getParametro(),
				BitacoraComponentesEnum.DIGITALIZACION_Y_PROCESAMIENTO.getValor(), 
				BitacoraConceptosEnum.INICIAR_AP_DIGITALIZACION_Y_PROC_DE_IMAGENES.getValor(), 
				"",
				usuarioFirmadoService.getUsuarioFirmadoVO().getUsername()==null?"":usuarioFirmadoService.getUsuarioFirmadoVO().getUsername(),
				usuarioFirmadoService.getUsuarioFirmadoVO().getId()==null?1:usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				"",
				"", 
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
	}
}
