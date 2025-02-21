package com.example.demo.repository;

import com.example.demo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAllByCity(String city) {
        String hql = "FROM Person p WHERE p.city = :city";
        TypedQuery<Person> query = entityManager.createQuery(hql, Person.class);
        query.setParameter("city", city);
        return query.getResultList();
    }
}