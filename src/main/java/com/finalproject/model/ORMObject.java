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
    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
    
    public void save() {
        EntityManager entityManager =emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public void delete() {
        EntityManager entityManager =emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(this);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public static <T> List<T> find(Class<T> type) {
        EntityManager entityManager =emf.createEntityManager();
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        return entityManager.createQuery(criteria).getResultList();
    }
}
