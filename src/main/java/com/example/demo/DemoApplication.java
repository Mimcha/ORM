package com.example.demo;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		// Заполнение таблицы данными
		personRepository.save(new Person("Иван", "Иванов", 30, "Москва", "1234567890"));
		personRepository.save(new Person("Петр", "Петров", 25, "Санкт-Петербург", "0987654321"));
		personRepository.save(new Person("Светлана", "Сидорова", 28, "Казань", "1122334455"));
	}
}
