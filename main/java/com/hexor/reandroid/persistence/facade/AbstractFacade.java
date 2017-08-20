package com.hexor.reandroid.persistence.facade;

import com.hexor.reandroid.persistence.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by karan on 23/9/14.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractFacade<T> {

    @Autowired Dialect dialect;

    @PersistenceContext(unitName = "ReAndroidPersistenceUnit")
    private EntityManager entityManager;

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
        if(entityManager != null && dialect != null) {
            entityManager.setProperty("hibernate.dialect", dialect.getDialect());
        }
    }

    public AbstractFacade() {
    }

    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void create(T entity) {
        this.entityManager.persist(entity);
    }

    public T createAndReturn(T entity) {
        this.entityManager.persist(entity);
        return entity;
    }

    public void edit(T entity) {
        this.entityManager.merge(entity);
    }

    public void remove(T entity) {
        this.entityManager.remove(this.entityManager.merge(entity));
    }

    public T find(Object primaryKey) {
        return this.entityManager.find(entityClass, primaryKey);
    }

    public T findByCompositeKey(Object primaryKey) {
        return this.entityManager.find(entityClass, primaryKey);
    }

    public List<T> findAll() {
        CriteriaQuery cq = this.entityManager.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return this.entityManager.createQuery(cq).getResultList();
    }

}
