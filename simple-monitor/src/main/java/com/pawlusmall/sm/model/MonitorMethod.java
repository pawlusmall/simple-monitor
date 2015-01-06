package com.pawlusmall.sm.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author pawlusmall
 */
@Entity
@Table(name = "monitor_method")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MonitorMethod implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    protected Method method;

    public Method getMethod() {
        return method;
    }
    
}
