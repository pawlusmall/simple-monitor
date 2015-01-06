package com.pawlusmall.sm.service;

import com.pawlusmall.sm.model.Application;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

/**
 *
 * @author pawlusmall
 */
public class ApplicationService implements Serializable {

    @Inject
    private EntityManager em;
    
    @Transactional
    public void addApplication(Application application) {
        em.persist(application);
    }
    
    public List<Application> findAll() {
        CriteriaQuery<Application> criteria = em.getCriteriaBuilder().createQuery(Application.class);
        return em.createQuery(criteria.select(criteria.from(Application.class))).getResultList();
    }
    
}
