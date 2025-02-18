package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PERSONS")
public class Person {

    @EmbeddedId
    private PersonKey id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "city_of_living", nullable = false)
    private String cityOfLiving;

    // Геттер для доступа к возрасту через PersonKey
    public int getAge() {
        return id.getAge();
    }

    // Геттеры и сеттеры
    public PersonKey getId() {
        return id;
    }

    public void setId(PersonKey id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityOfLiving() {
        return cityOfLiving;
    }

    public void setCityOfLiving(String cityOfLiving) {
        this.cityOfLiving = cityOfLiving;
    }
}