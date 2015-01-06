package com.pawlusmall.sm.presentation;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author pawlusmall
 */
public abstract class Controller {
    
    @Inject
    private FacesContext facesContext;

    protected void addInformationMessage(String component, String message, Object... args) {
        facesContext.addMessage(component, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    protected void addWarningMessage(String component, String message, Object... args) {
        facesContext.addMessage(component, new FacesMessage(FacesMessage.SEVERITY_WARN, message, null));
    }

    protected void addErrorMessage(String component, String message, Object... args) {
        facesContext.addMessage(component, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    protected String getParam(String param) {
        Map<String, String> map = facesContext.getExternalContext().getRequestParameterMap();
        return map.get(param);
    }

    protected Long getParamId(String param) {
        return Long.valueOf(getParam(param));
    }

}
