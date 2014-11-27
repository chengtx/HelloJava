package me.chengtx.webapp.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version Apr 5, 2012
 */
public abstract class AbstractStore {

    @PersistenceContext
    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
