package com.pawlusmall.sm.presentation;

import com.pawlusmall.sm.model.Application;
import com.pawlusmall.sm.model.Method;
import com.pawlusmall.sm.model.PingMethod;
import com.pawlusmall.sm.model.ResponseRegexMethod;
import com.pawlusmall.sm.service.ApplicationService;
import com.pawlusmall.sm.service.StatusCheckService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author pawlusmall
 */
@Named
@ViewScoped
public class ApplicationController extends Controller implements Serializable {

    private List<ApplicationWrapper> applications;
    private Application application;
    private Method method;
    
    @Inject
    private ApplicationService applicationService;
    @Inject
    private StatusCheckService statusCheckService;
    
    @PostConstruct
    public void init() {
        application = new Application();
        applications = new ArrayList<>();
        for(Application a : applicationService.findAll()) {
            applications.add(new ApplicationWrapper(a, statusCheckService.getLastStatusCheck(a)));
        }
    }
    
    public String createApplication() {
        application.setCreationDate(new Date());
        applicationService.addApplication(application);
        addInformationMessage(null, application.getName() + " was added successfully");
        init();
        return "index.xhtml?faces-redirect=true";
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
        if(method.equals(Method.PING)) {
            application.setMethod(new PingMethod());
        } else {
            application.setMethod(new ResponseRegexMethod());  
        }
    }
    
    public Method[] getMonitoringMethods() {
        return Method.values();
    }
    
    public Short[] getMonitoringPeriods() {
        return new Short[] {1, 2, 5, 10, 30, 60, 120, 300, 1440};
    }
    
    public List<ApplicationWrapper> getApplications() {
        return applications;
    }
    
}
