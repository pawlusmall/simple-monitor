package com.pawlusmall.sm.presentation;

import com.pawlusmall.sm.model.Application;
import com.pawlusmall.sm.model.StatusCheck;
import java.io.Serializable;

/**
 *
 * @author pawlusmall
 */
public class ApplicationWrapper implements Serializable {

    private Application application;
    private StatusCheck lasStatusCheck;

    public ApplicationWrapper(Application application, StatusCheck lasStatusCheck) {
        this.application = application;
        this.lasStatusCheck = lasStatusCheck;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public StatusCheck getLasStatusCheck() {
        return lasStatusCheck;
    }

    public void setLasStatusCheck(StatusCheck lasStatusCheck) {
        this.lasStatusCheck = lasStatusCheck;
    }
    
}
