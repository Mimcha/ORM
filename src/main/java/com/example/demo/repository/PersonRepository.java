package com.example.demo.repository;

import com.example.demo.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Метод для поиска по городу
    List<Person> findByCityOfLiving(String city);

    // Метод для поиска по возрасту
    List<Person> findByAgeLessThanOrderByAgeAsc(int age);

    // Метод для поиска по имени и фамилии
    Optional<Person> findByNameAndSurname(String name, String surname);
}
