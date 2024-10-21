/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.nkandasamyrajkumar.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author naveenarajkumar
 * @param <T>
 */
public abstract class AbstractService<T> {

    /**
     *
     */
    @PersistenceContext(name = "itmd4515PU")
    public EntityManager em;

    /**
     *
     */
    protected Class<T> entityClass;

    /**
     *
     * @param entityClass
     */
    protected AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     *
     * @param entity
     */
    public void create(T entity) {
        /* transaction begins when we invoke the method */
        em.persist(entity);
    }

    /**
     *
     * @param id
     * @return
     */
    public T read(Long id) {
        return em.find(entityClass, id);
    }

    /**
     *
     * @param entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     *
     * @param entity
     */
    public void delete(T entity) {
        em.remove(em.merge(entity));
    }

    /**
     *
     * @param namedQuery
     * @return
     */
    protected List<T> findAll(String namedQuery) {
        return em.createNamedQuery(namedQuery, entityClass).getResultList();
    }

}
