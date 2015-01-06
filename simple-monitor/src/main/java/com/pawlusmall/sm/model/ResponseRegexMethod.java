package com.pawlusmall.sm.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pawlusmall
 */
@Entity
@Table(name = "response_regex_monitor_method")
public class ResponseRegexMethod extends MonitorMethod implements Serializable {
    
    @NotNull
    @Size(min = 1)
    private String url;
    
    @NotNull
    @Size(min = 1)
    private String regex;
    
    public ResponseRegexMethod() {
        method = Method.RESPONSE_REGEX;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

}
