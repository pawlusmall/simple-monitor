package com.pawlusmall.sm.service;

import com.pawlusmall.sm.model.Application;
import com.pawlusmall.sm.model.StatusCheck;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author pawlusmall
 */
public class StatusCheckService implements Serializable {
    
    @Inject
    private EntityManager em;

    @Transactional
    public void addStatusCheck(StatusCheck status) {
        em.persist(status);
    }
    
    public StatusCheck getLastStatusCheck(Application application) {
        TypedQuery<StatusCheck> query = em.createNamedQuery("StatusCheck.getLastStatusCheck", StatusCheck.class);
        query.setParameter("application", application);
        List<StatusCheck> statusChecks = query.getResultList();
        return statusChecks.isEmpty() ? null : statusChecks.get(0);
    }
    
}
