package com.example.demo.repository;

import com.example.demo.entity.Person;
import com.example.demo.entity.PersonKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonKey> {

    List<Person> findByCityOfLiving(String city);

    List<Person> findByAgeGreaterThanOrderByAgeDesc(int age);
}