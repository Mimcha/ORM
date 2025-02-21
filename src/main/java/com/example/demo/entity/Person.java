package com.example.demo.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private int age;
    private String cityOfLiving;
    private String phoneNumber;

    public Person() {
    }

    // Конструктор
    public Person(String name, String surname, int age, String cityOfLiving, String phoneNumber) {
        this.name = name; // Инициализация имени
        this.surname = surname;   // Инициализация фамилии
        this.age = age;             // Инициализация возраста
        this.cityOfLiving = cityOfLiving;     // Инициализация адреса
        this.phoneNumber = phoneNumber; // Инициализация номера телефона
    }

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCityOfLiving() {
        return cityOfLiving;
    }

    public void setCityOfLiving(String cityOfLiving) {
        this.cityOfLiving = cityOfLiving;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
