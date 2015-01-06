package com.pawlusmall.sm.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author pawlusmall
 */
@Entity
@Table(name = "status_check")
@NamedQueries({
    @NamedQuery(
            name = "StatusCheck.getLastStatusCheck",
            query = "SELECT sc FROM StatusCheck sc WHERE sc.application = :application ORDER BY sc.date DESC"
    )
})
public class StatusCheck implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "check_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
    
}
