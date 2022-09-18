package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootApplication
@RestController
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
//		int result = jdbcTemplate.update(sql, "Head First Java", "Kathy Sierra", 18.55f);
//
//		if (result > 0) {
//			System.out.println("Insert successfully.");
//		}

//		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
//		insertActor.withTableName("books").usingGeneratedKeyColumns("id");
//
//		Book book = new Book("Effective Java", "Joshua Bloch", 29.99f);
//		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);
//
//		int result = insertActor.execute(paramSource);
//
//		if (result > 0) {
//			System.out.println("Insert successfully.");
//		}

//		testUpdate();
//		testListAll();
//		testFind();
	}



	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/messages")
	public String messages(@RequestParam(value = "name", defaultValue = "World") String name) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.US);
		String message = bundle.getString("label");
		return message;
	}

}