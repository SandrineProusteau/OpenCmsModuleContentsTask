/**
 * 
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Sandrine Prousteau
 *
 */
public class OpenCmsModuleContentsTask extends Task{

	Document document = null;
	
	String css = "" +
			"body{font-size: 14px;}" +
			"caption{font-size:2em;font-weight: bold;}" +
			"table{width: 100%;font-size: 14px;border-collapse: collapse;}" +
			"table th{width: 20%;}" +
			"#types table th{width: 20%;text-align: left;}" +
			"#types table th.name,#types table th.id{width: 10%;}" +
			"#types table th.description{width: auto;}" +
			"#types table th.data{background: ddd;}" +
			"table th,table td{border:1px solid #999;padding: 0.2em;}" +
			"#types table th.name,#types table th.id,#types table th.description{border:2px solid #999;}" +
			"#types table td.name,#types table td.id,#types table th.expl,#types table td.expl{border:1px solid #999;border-top:2px solid #999;}" +
			"#module,#types{margin : 2em;}";
	
	private String modulename = "";
	private String modulenicename = "";
	private String modulegroup = "";
	private String moduleclass = "";
	private String moduledescription = "";
	private String moduleversion = "";
	private String moduleauthorname = "";
	private String moduleauthoremail = "";
	private String moduleopencmsversion = "";
	private String moduleexportversion = "";
	private String jdkversion = "";
	public void setModulename(String name) {this.modulename = name;}
	public void setModulenicename(String nicename) {this.modulenicename = nicename;}
	public void setModulegroup(String group) {this.modulegroup = group;}
	public void setModuleclass(String moduleclass) {this.moduleclass = moduleclass;}
	public void setModuledescription(String moduledescription) {this.moduledescription = moduledescription;}
	public void setModuleversion(String version) {this.moduleversion = version;}
	public void setModuleauthorname(String authorname) {this.moduleauthorname = authorname;}
	public void setModuleauthoremail(String authoremail) {this.moduleauthoremail = authoremail;}
	public void setOpenCmsVersion(String opencmsversion) {this.moduleopencmsversion = opencmsversion;}
	public void setExportVersion(String exportversion) {this.moduleexportversion = exportversion;}
	public void setJdkVersion(String jdkversion) {this.jdkversion = jdkversion;}
	
	private String resourcetypes = null;
	private String explorertypes = null;
	private String vfs = null;
	private String exportto = null;
	public void setResourcetypes(String resourceTypes) {this.resourcetypes = resourceTypes;}
	public void setExplorertypes(String explorerTypes) {this.explorertypes = explorerTypes;}
	public void setVFS(String vfs) {this.vfs = vfs;}
	public void setExportto(String exportto) {this.exportto = exportto;}
	
	
	public Document createDocument() { 
		
		document = DocumentHelper.createDocument();
        document.setXMLEncoding("UTF-8");
        Element html = document.addElement( "html" );
        
        Element head = html.addElement( "head" );
        Element body = html.addElement( "body" );
        
        head.addElement( "style" ).addText(css);
        
        addModule(body);
        addTypes(body);
        
		return document;
		
	}
	
	
	public void addModule(Element body) { 
		Element module = body.addElement( "div" ).addAttribute("id", "module");
        Element module_table = module.addElement( "table" );
        module_table.addElement( "caption" ).addText("MODULE");
        Element module_tr = module_table.addElement( "tr" );
        module_tr.addElement( "th" ).addText("Name");
        module_tr.addElement( "td" ).addText(modulename);
        module_tr.addElement( "th" ).addText("Nice name");
        module_tr.addElement( "td" ).addText(modulenicename);
        module_tr = module_table.addElement( "tr" );
        module_tr.addElement( "th" ).addText("Version");
        module_tr.addElement( "td" ).addText(moduleversion);
        module_tr.addElement( "th" ).addText("Group");
        module_tr.addElement( "td" ).addText(modulegroup);
        module_tr = module_table.addElement( "tr" );
        module_tr.addElement( "th" ).addText("Class");
        module_tr.addElement( "td" ).addText(moduleclass);
        module_tr.addElement( "th" ).addText("JDK version");
        module_tr.addElement( "td" ).addText(jdkversion);
        module_tr = module_table.addElement( "tr" );
        module_tr.addElement( "th" ).addText("Author name");
        module_tr.addElement( "td" ).addText(moduleauthorname);
        module_tr.addElement( "th" ).addText("Author email");
        module_tr.addElement( "td" ).addText(moduleauthoremail);
        module_tr = module_table.addElement( "tr" );
        module_tr.addElement( "th" ).addText("OpenCms version");
        module_tr.addElement( "td" ).addText(moduleopencmsversion);
        module_tr.addElement( "th" ).addText("Export version");
        module_tr.addElement( "td" ).addText(moduleexportversion);
        module_tr = module_table.addElement( "tr" );
        module_tr.addElement( "th" ).addText("Description");
        module_tr.addElement( "td" ).addAttribute("colspan", "3").addText(moduledescription);
	}
	
	
	private void addTypes(Element body) {
		
		List<Element> explorertypeslist = new ArrayList<Element>();
		if(null != explorertypes){
			File xml = new File(explorertypes);
			SAXReader reader = new SAXReader();
			Document doc;
			try {
				doc = reader.read(xml);
				Element root = doc.getRootElement();
				Iterator<Element> it = root.elementIterator(); 
				while(it.hasNext()){
					Element el = (Element)it.next();
					explorertypeslist.add(el);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
				System.out.println(e); 
			}
		}
		
		List<Element> resourcetypeslist = new ArrayList<Element>();
		if(null != resourcetypes){
			File xml = new File(resourcetypes);
			SAXReader reader = new SAXReader();
			Document doc;
			try {
				doc = reader.read(xml);
				Element root = doc.getRootElement();
				Iterator<Element> it = root.elementIterator(); 
				while(it.hasNext()){
					Element el = (Element)it.next();
					resourcetypeslist.add(el);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
				System.out.println(e); 
			}
		}
		
		try {
			Element types = body.addElement( "div" ).addAttribute("id", "types");
	        Element types_table = types.addElement( "table" );
	        types_table.addElement( "caption" ).addText("TYPES");
	        
	        Element types_tr = types_table.addElement( "tr" );
			types_tr.addElement( "th" ).addAttribute("class", "name").addText("Name");
			types_tr.addElement( "th" ).addAttribute("class", "id").addText("ID");
			types_tr.addElement( "th" ).addAttribute("class", "description").addAttribute("colspan", "2").addText("Description");
	        
			Iterator<Element> it = resourcetypeslist.iterator(); 
			while(it.hasNext()){
				Element el = (Element)it.next();
				
				String name = el.attribute("name").getText();
				String id = el.attribute("id").getText();
				
				int nb_lines = 0;
				
				//check exploretypes list
				int foundInExplorer = 0;
				String reference = "";
				Iterator<Element> it_expl = explorertypeslist.iterator(); 
				while(it_expl.hasNext()){
					Element el_expl = (Element)it_expl.next();
					String expl_name = el_expl.attribute("name").getText();
					String expl_reference = el_expl.attribute("reference")!=null ? el_expl.attribute("reference").getText() : "";
					if(name.equals(expl_name)){
						reference = expl_reference;
						foundInExplorer++;
					}
				}
				String exploretype_case = foundInExplorer==0 ? "not found" : (foundInExplorer==1 ? "found" : (foundInExplorer>1 ? "found several times!" : ""));
				
				types_tr = types_table.addElement( "tr" );
				Element tdname = types_tr.addElement( "td" ).addAttribute("class", "name").addText(name);
				Element tdid = types_tr.addElement( "td" ).addAttribute("class", "id").addText(id);
				types_tr.addElement( "th" ).addAttribute("class", "expl").addText("Explorer type");
				types_tr.addElement( "td" ).addAttribute("class", "expl").addText(exploretype_case + (!reference.equals("") ? " -- ref : " + reference : ""));
				nb_lines++;
				
				//classe de gestion 
				String classe = el.attribute("class").getText();
				types_tr = types_table.addElement( "tr" );
				types_tr.addElement( "th" ).addText("Class");
				types_tr.addElement( "td" ).addText(classe);
				nb_lines++;
				
				//XSD
				types_tr = types_table.addElement( "tr" );
				types_tr.addElement( "th" ).addAttribute("colspan", "2").addAttribute("class", "data").addText("XSD");
				nb_lines++;
				
				//schemafile
				String schemafile = "";
				List<Element> params = el.elements("param");
				Iterator<Element> it_params = params.iterator(); 
				while(it_params.hasNext()){
					Element el_param = (Element)it_params.next();
					if(el_param.attribute("name").getText().equals("schema")){
						schemafile = el_param.getText();
					}
				}
				types_tr = types_table.addElement( "tr" );
				types_tr.addElement( "th" ).addText("Schema file");
				types_tr.addElement( "td" ).addText(schemafile);
				nb_lines++;
				
				if(!schemafile.equals("")){
					
					
					File xml = new File(vfs+schemafile);
					SAXReader reader = new SAXReader();
					Document doc;
					try {
						doc = reader.read(xml);
						Element xsdroot = doc.getRootElement();
						
						//schemaname
						String schemaname = xsdroot.element("element").attribute("name").getText();
						types_tr = types_table.addElement( "tr" );
						types_tr.addElement( "th" ).addText("Schema name");
						types_tr.addElement( "td" ).addText(schemaname);
						nb_lines++;
						
						//resourcebundle
						String resourcebundle = xsdroot.element("annotation").element("appinfo").element("resourcebundle").attribute("name").getText();
						types_tr = types_table.addElement( "tr" );
						types_tr.addElement( "th" ).addText("Resource bundle");
						types_tr.addElement( "td" ).addText(resourcebundle);
						nb_lines++;
						
						//schemaformatters
						types_tr = types_table.addElement( "tr" );
						types_tr.addElement( "th" ).addAttribute("colspan", "2").addText("Formatters");
						nb_lines++;
						List<Element> el_formatters = xsdroot.element("annotation").element("appinfo").element("formatters").elements("formatter");
						Iterator<Element> it_formatters = el_formatters.iterator(); 
						while(it_formatters.hasNext()){
							Element el_formatter = (Element)it_formatters.next();
							String formatter_type = el_formatter.attribute("type").getText();
							String formatter_uri = el_formatter.attribute("uri").getText();
							types_tr = types_table.addElement( "tr" );
							types_tr.addElement( "td" ).addText(formatter_type);
							types_tr.addElement( "td" ).addText(formatter_uri);
							nb_lines++;
						}
						
						//schemasettings
						types_tr = types_table.addElement( "tr" );
						types_tr.addElement( "th" ).addAttribute("colspan", "2").addText("Settings");
						nb_lines++;
						List<Element> el_settings = xsdroot.element("annotation").element("appinfo").element("settings").elements("setting");
						Iterator<Element> it_settings = el_settings.iterator(); 
						while(it_settings.hasNext()){
							Element el_setting = (Element)it_settings.next();
							String setting_name = el_setting.attribute("name").getText();
							String setting_widgetconfig = el_setting.attribute("widget-config").getText();
							types_tr = types_table.addElement( "tr" );
							types_tr.addElement( "td" ).addText(setting_name);
							types_tr.addElement( "td" ).addText(setting_widgetconfig);
							nb_lines++;
						}
					} catch (DocumentException e) {
						//e.printStackTrace();
						System.out.println(e); 
					}
					
				}
				
				//properties
				types_tr = types_table.addElement( "tr" );
				types_tr.addElement( "th" ).addAttribute("colspan", "2").addAttribute("class", "data").addText("PROPERTIES");
				nb_lines++;
				Element el_properties = el.element("properties");
				List<Element> propertieslist = el_properties.elements("property");
				Iterator<Element> it_properties = propertieslist.iterator(); 
				while(it_properties.hasNext()){
					Element el_property = (Element)it_properties.next();
					String prop_name = el_property.element("name").getText();
					String prop_type = el_property.element("value").attribute("type").getText();
					String prop_value = el_property.element("value").getText();
					types_tr = types_table.addElement( "tr" );
					types_tr.addElement( "td" ).addText(prop_name + " (" + prop_type + ")");
					types_tr.addElement( "td" ).addText(prop_value);
					nb_lines++;
				}
				
				tdname.addAttribute("rowspan", nb_lines+"");
				tdid.addAttribute("rowspan", nb_lines+"");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e); 
		}
		
	}
	
	
	public void write() throws IOException {
		File tmpFile = new File(exportto + "/index.html");
		FileOutputStream fos = new FileOutputStream(tmpFile);
        XMLWriter writer = new XMLWriter(fos);
        writer.write( document );
        writer.close();
    }
	
	
	public void init () {

		//nothing to do

	}
	
	public void execute() throws BuildException {

		System.out.println("start contents..."); 
		super.execute();
        createDocument();
        try {
			write();
			System.out.println("contents ok!"); 
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("contents ko! "+e); 
		}
	}
}
