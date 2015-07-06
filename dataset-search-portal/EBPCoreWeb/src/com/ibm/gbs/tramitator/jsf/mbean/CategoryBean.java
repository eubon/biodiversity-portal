package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.richfaces.component.SortOrder;

import com.ibm.gbs.eubon.ebp.core.ejb.CategoryService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.util.Constantes;


@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean extends MantoBean implements Serializable {

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;

	@EJB 
	private CategoryService categorySrv;
	
	private CategoryDataModel dataModel;
	private SortingBean sortingBean; 
	private Collection<Object> selection;
	
    private Category category;
    
	
	public CategoryBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
	}

	public CategoryBean() {
	}

	@PostConstruct
	public void create() {
		dataModel = new CategoryDataModel(categorySrv);
		sortingBean = new SortingBean();
		sortingBean.sortBy("id", SortOrder.ascending);
	}

	
	private void cargarCategories(ArrayList<SelectItem> lista) {
		List<Category> listado = srvMetadatos.getCategories();
		for (Category aux : listado) {
			SelectItem selectItem = new SelectItem(aux.getId(),	aux.getName());
			lista.add(selectItem);
		}
	}


	public List<Category> getCategories() {
		return srvMetadatos.getCategories();
	}

	
	public String listCategories() {
		
		return "listCategories";
	}
	
	public String consult() {
		category = readCategory();
		
		if (category == null)
		{
			return "";
		}
		else
			return "consultCategory";
	}
	
	public String view(){
		return "consultCategory";
	}

	private Category readCategory() 
	{
		Category cat = null;
		String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
				 
		logger.debug("CategoryBean.getCategory - id=" + idStr + "-" );
		Long l = new Long(idStr);
		cat = categorySrv.read(l.longValue()); 
					
		if (cat != null)
		{
			logger.debug("CategoryBean.getCategory - Category=" + cat.getId() + "-" + cat.getName());
		}				
		
		return cat;
	}
	
	public String edit() 
	{
		if (category == null)
		{
			return "";
		}
			
		return "editCategory";
	}
	
	public String delete() 
	{
		if (category == null)
		{
			return "";
		}
		
		logger.debug("CategoryBean.delete(): id " + category.getId());
		
		try {
			categorySrv.delete(category);
			category = null;
		} catch (Exception e) {
			if (e != null )
			{
				logger.info("CategoryBean.delete Category:" + category.getId() + " could not be deleted -" + e +"-" +e.getMessage()+"-");

				FacesMessage message = new FacesMessage( " Category could not be deleted. It probably has content associated.", " Category could not be deleted. It probably has content associated.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
		}
			
		return "listCategories";
	}
	
	public String newBean() {
		
		category = new Category();
		
		return "newCategory";
	}
		
	
	public String saveBean()
	{		
		logger.debug("CategoryBean.saveBean - Category=" + category.getId() + "-" + category.getName() + "-");
				   
	    category = categorySrv.guardar(category);	

	    clearCache();
	    
        return "consultCategory";
	}

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public CategoryDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(CategoryDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public SortingBean getSortingBean() {
		return sortingBean;
	}

	public void setSortingBean(SortingBean sortingBean) {
		this.sortingBean = sortingBean;
	}
	
	
}
