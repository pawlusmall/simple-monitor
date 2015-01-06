package com.pawlusmall.sm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author pawlusmall
 */
@Entity
@Table(name = "ping_monitor_method")
public class PingMethod extends MonitorMethod implements Serializable {

    @NotNull
    @Size(min = 1)
    @Column(name = "ip")
    private String ip;
    
    public PingMethod() {
        method = Method.PING;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
}
