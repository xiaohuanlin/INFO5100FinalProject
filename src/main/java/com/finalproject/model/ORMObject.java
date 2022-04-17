/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalproject.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ORMObject {
//    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    protected static EntityManager entityManager = Persistence.createEntityManagerFactory("jpa").createEntityManager();
    
    public void save() {
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
    }
    
    public void flush() {
        entityManager.getTransaction().begin();
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
    
    public void refresh() {
        entityManager.getTransaction().begin();
        entityManager.refresh(this);
        entityManager.getTransaction().commit();
    }
    
    public void delete() {
        entityManager.getTransaction().begin();
        entityManager.remove(this);
        entityManager.getTransaction().commit();
    }
    
    public static <T> List<T> find(Class<T> type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return entityManager.createQuery(criteria).getResultList();
    }
    
    public static <T> T findById(Class<T> type, int id) {
        return entityManager.find(type, id);
    }
}
