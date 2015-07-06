package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;

import org.richfaces.PanelMenuMode;
import org.richfaces.component.UICommandLink;
import org.richfaces.component.UIPanelMenu;
import org.richfaces.component.UIPanelMenuGroup;

import com.ibm.gbs.tramitator.jsf.mbean.menu.ElementoMenu;
import com.ibm.gbs.tramitator.jsf.mbean.menu.MenuParser;
import com.ibm.gbs.tramitator.util.jsf.JSFUtil;

@ManagedBean(name = "menuBean") 
@SessionScoped
public class MenuBean implements Serializable{

	private UIPanelMenu menu = null;

	public MenuBean() {
		List<ElementoMenu> elementosMenu = new MenuParser().getMenu();
		FacesContext fc = FacesContext.getCurrentInstance();
		Application application = FacesContext.getCurrentInstance().getApplication();
		menu = (UIPanelMenu) application.createComponent(fc, UIPanelMenu.COMPONENT_TYPE, "org.richfaces.PanelMenuRenderer");
		generarGrupos(menu, elementosMenu, fc, application);
		
		HtmlGraphicImage img = new HtmlGraphicImage();
		img.setValue("/images/lt.png");
		
		UICommandLink btnCollapse = new UICommandLink();
		btnCollapse.setId("cmdCollapse");
		btnCollapse.setStyle("float:right;  padding: 2px 10px 3px; color: #000000; border-color: #C4C0C9; border-style: solid; border-width: 0px; margin-right: 4px; margin-top: 2px;");
		btnCollapse.setOnclick("document.getElementById('frmMenu:pmMenu').style.display='none';document.getElementById('divShowMenu').style.display='block';document.getElementById('divContent').style.marginLeft = '50px';return false;");
		btnCollapse.getChildren().add(img);
		
		menu.getChildren().add(0, btnCollapse);
	}

	private void generarGrupos(UIComponent elementoMenu, List<ElementoMenu> elementosMenu, FacesContext fc, Application application) {
		if (elementosMenu!=null)
		for (ElementoMenu grupo : elementosMenu){

			if (isUserInRoles(fc, grupo.getRoles())) {	
				//Los elementos del primer nivel siempre son grupos
				if (elementoMenu instanceof UIPanelMenu|| grupo.getElementos()!=null && grupo.getElementos().size()>0){
					UIPanelMenuGroup group = (UIPanelMenuGroup) application.createComponent(fc, UIPanelMenuGroup.COMPONENT_TYPE,"org.richfaces.PanelMenuGroupRenderer");
					if (!(elementoMenu instanceof UIPanelMenu)){
						group.setLeftCollapsedIcon("/images/collapsed.png");
						group.setLeftExpandedIcon("/images/expanded.png");
					}
					group.setLabel(grupo.getLabel());
					group.setId(grupo.getId());
					group.setExpanded(true);
					elementoMenu.getChildren().add(group);
					generarGrupos(group, grupo.getElementos(), fc, application);
				} else {
						UIPanelMenuGroup item = (UIPanelMenuGroup) application.createComponent(fc, UIPanelMenuGroup.COMPONENT_TYPE,"org.richfaces.PanelMenuGroupRenderer");
//						UIPanelMenuItem item = (UIPanelMenuItem) application.createComponent(fc, UIPanelMenuItem.COMPONENT_TYPE, "org.richfaces.PanelMenuItemRenderer");
						item.setLabel(grupo.getLabel());
						item.setId(grupo.getId());
						//El modo debe ser server, si no, los h:commandButton no funcionarán, raro...
						item.setMode(PanelMenuMode.server);
						if (grupo.getAction()!=null && !"".equals(grupo.getAction())){
							item.setActionExpression(JSFUtil.createMethodExpression(grupo.getAction()));
//							item.setAction(JSFUtil.createMethodBinding(grupo.getAction()));
							item.setSelectable(true);
						}
						elementoMenu.getChildren().add(item);
				}
			}
		}
	}

	private boolean isUserInRoles(FacesContext fc, List<String> roles) {
		boolean groupInRole = false;
		//Si roles es null, cualquier usuario tiene acceso, 
		//sin embargo si roles es la lista vacía nadie tiene acceso
		if (roles!=null){
			for (int i = 0;!groupInRole && i<roles.size();i++){
				groupInRole = fc.getExternalContext().isUserInRole(roles.get(i));
			}
		} else {
			groupInRole = true;
		}
		return groupInRole;
	}


	public void setMenu(UIPanelMenu menu) {
		this.menu = menu;
	}

	public UIPanelMenu getMenu() {
		return menu;
	}

 }
