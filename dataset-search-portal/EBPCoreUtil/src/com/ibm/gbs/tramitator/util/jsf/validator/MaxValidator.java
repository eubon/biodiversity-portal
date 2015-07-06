package com.ibm.gbs.tramitator.util.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class MaxValidator implements Validator {
 
    public void validate(FacesContext facesContext, UIComponent arg1, Object value) throws ValidatorException {
    	Object value2 = arg1.getValueExpression("value").getValue(facesContext.getELContext());
    	if (value instanceof Integer && value2 instanceof Integer){
    		if (((Integer) value).compareTo((Integer) value2)>0){
    			throw new ValidatorException(new FacesMessage("El valor máximo es " + value2));    			
    		} else if (((Integer) value).compareTo(0)<0){
    			throw new ValidatorException(new FacesMessage("El valor mínimo es 0"));    			
    		}
    	}
    }

}
