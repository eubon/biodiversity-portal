package com.ibm.gbs.tramitator.jsf.mbean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.richfaces.component.SortOrder;

import com.ibm.gbs.eubon.ebp.core.ejb.ContentService;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Category;
import com.ibm.gbs.eubon.ebp.core.jpa.entity.Content;
import com.ibm.gbs.tramitator.ejb.ServicioMetadatos;
import com.ibm.gbs.tramitator.ejb.crud.ServicioCRUD;
import com.ibm.gbs.tramitator.util.Constantes;

@ManagedBean(name = "contentBean")
@SessionScoped
public class ContentBean extends MantoBean implements Serializable {

	private static final long serialVersionUID = 708679843476943945L;

	Logger logger = Logger.getLogger("com.ibm.gbs");
	
	@EJB
	private ServicioMetadatos srvMetadatos;

	@EJB 
	private ContentService contentSrv;
	
	@EJB
	private ServicioCRUD crud;

	
	private ContentDataModel dataModel;
	private SortingBean sortingBean; 
	private Collection<Object> selection;
	
    private Content content;
    
    private long category;
    
    List<Category> listCategories;
    
    List<Content> listContent;
    
	
	public ContentBean(ServicioMetadatos srvMetadatos) {
		super();
		this.srvMetadatos = srvMetadatos;
		
		
	}

	public ContentBean() {
	}

	@PostConstruct
	public void create() {
		dataModel = new ContentDataModel(contentSrv);
		sortingBean = new SortingBean();
		sortingBean.sortBy("id", SortOrder.ascending);
		category = 0;
		listContent = crud.listadoFiltroOrdenado(Content.class, " FLG_PUBLISH = '1' "," publish_date " );
		
	}


	
	public String listContents() {
		category = 0;
		return "listContents";
	}
	
	public String searchPublicContent(ValueChangeEvent event) 
	{
		String where = "";
		
		if (null != event.getNewValue())
		{
			Long cat = (Long)event.getNewValue();
			if (cat > 0)
			{
			   where = " and category = " + event.getNewValue() + " ";
			}
		}
		listContent = crud.listadoFiltroOrdenado(Content.class, " FLG_PUBLISH = '1' " + where," publish_date " );
		
		return "homePublic";
	}
	
	public String consult() {
		content = readContent();
		
		if (content == null)
		{
			return "";
		}
		else
			return "consultContent";
	}
	
	public String view(){
		return "consultContent";
	}

	private Content readContent() 
	{
		Content c = null;
		String idStr = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
				 
		logger.debug("ContentBean.getContent - id=" + idStr + "-" );
		Long l = new Long(idStr);
		c = contentSrv.read(l.longValue()); 
					
		if (c != null)
		{
			logger.debug("ContentBean.getContent - Content=" + c.getId() + "-" + c.getTitle());
		}				
		
		return c;
	}
	
	public String edit() 
	{
		
		if (content == null)
		{
			return "";
		}
		
		category = 0;
		if (content.getCategory() != null)
		{
		   category = content.getCategory().getId();
		}
		listCategories = crud.listadoOrdenado(Category.class, " NAME " );

		return "editContent";
	}
	
 
	
	public String newBean() {
		
		category = -1;
		
		content = new Content();
		
		return "newContent";
	}
		
	
	public String saveBean()
	{		
		logger.debug("ContentBean.saveBean - Content=" + content.getId() + "-" + content.getTitle() + "-");
				   
		
		if (category == -1)
		{
			FacesMessage message = new FacesMessage("You must select one Category for the content.", "You must select one Category for the content.");
			message.setSeverity( FacesMessage.SEVERITY_ERROR);
			
			FacesContext.getCurrentInstance().addMessage(null, message);
			return Constantes.cadena_vacia;
		}
		
		Category categoryAux = null;
		categoryAux = (Category) crud.obtenerObjeto(Category.class, category);
		content.setCategory(categoryAux);
		
		content.setPublishDate(Calendar.getInstance().getTime());
		
	    content = contentSrv.guardar(content);	
	    
        return "consultContent";
	}

	
	public String delete() 
	{
		if (content == null)
		{
			return "";
		}
		
		logger.debug("CotentBean.delete(): id " + content.getId());
		
		try {
			contentSrv.delete(content);
			content = null;
		} catch (Exception e) {
			if (e != null )
			{
				logger.info("ContentBean.delete Content:" + content.getId() + " could not be deleted -" + e +"-" +e.getMessage()+"-");

				FacesMessage message = new FacesMessage( " Content could not be deleted.", " Content could not be deleted.");
				message.setSeverity( FacesMessage.SEVERITY_ERROR);
				
				FacesContext.getCurrentInstance().addMessage(null, message);
				return Constantes.cadena_vacia;
			}			
		}
			
		return "listContents";
	}
	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}



	public SortingBean getSortingBean() {
		return sortingBean;
	}

	public void setSortingBean(SortingBean sortingBean) {
		this.sortingBean = sortingBean;
	}

	public ContentDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(ContentDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Category> getListCategories() 
	{
		listCategories = crud.listadoOrdenado(Category.class, " NAME " );
		
		return listCategories;
	}

	public void setListCategories(List<Category> listCategories) {
		this.listCategories = listCategories;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public List<Content> getListContent() {
		return listContent;
	}

	public void setListContent(List<Content> listContent) {
		this.listContent = listContent;
	}
	
	
}
