package com.ibm.gbs.tramitator.jsf.mbean.menu;

import java.net.URL;
import java.util.List;

import javax.faces.FacesException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@ManagedBean
@ApplicationScoped
public class MenuParser {

	List<ElementoMenu> menu;
	
    @XmlRootElement(name = "root")
    private static final class Menu {
        
        private List<ElementoMenu> elementos;

    	
        @XmlElement(name = "elemento")
    	public List<ElementoMenu> getElementos() {
			return elementos;
		}

		public void setElementos(List<ElementoMenu> elementos) {
			this.elementos = elementos;
		}

    }
    
    public synchronized List<ElementoMenu> getMenu() {
    	if (menu==null){
            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            URL resource = ccl.getResource("com/ibm/gbs/tramitator/navigation.xml");
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(Menu.class);
                Menu tmp = (Menu) context.createUnmarshaller().unmarshal(resource);
                menu = tmp.getElementos();
            } catch (JAXBException e) {
                throw new FacesException(e.getMessage(), e);
            }
    	}
        return menu;
    }
    
}
