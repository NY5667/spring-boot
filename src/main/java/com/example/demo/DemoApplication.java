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

		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("books").usingGeneratedKeyColumns("id");

		Book book = new Book("Effective Java", "Joshua Bloch", 29.99f);
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(book);

		int result = insertActor.execute(paramSource);

		if (result > 0) {
			System.out.println("Insert successfully.");
		}

//		testUpdate();
//		testListAll();
//		testFind();
	}

	void testUpdate() {
		String sql = "UPDATE books SET price=? WHERE title=?";
		Object[] params = {9.99f, "Effective Java"};
		int result = jdbcTemplate.update(sql, params);

		if (result > 0) {
			System.out.println("Update successfully.");
		}
	}

	void testDelete(){
		String sql = "DELETE FROM books WHERE title=?";
		Object[] params = {"Effective Java"};
		int result = jdbcTemplate.update(sql, params);

		if (result > 0) {
			System.out.println("Delete successfully.");
		}
	}

	void testListAll() {
		String sql = "SELECT * FROM books";

		List<Book> listBooks = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(Book.class));

		for (Book book : listBooks) {
			System.out.println(book);
		}
	}

	void testFind() {
		String sql = "SELECT * FROM books WHERE id = 1";
		Book book = jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(Book.class));

		System.out.println(book);
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

	@GetMapping("/readLog")
	public String readLog(@RequestParam(value = "path") String path) throws IOException {

		// File path is passed as parameter
		File file = new File(
				"C:\\Users\\ludunyue\\Desktop\\1\\test.txt");

		// Note:  Double backquote is to avoid compiler
		// interpret words
		// like \test as \t (ie. as a escape sequence)

		// Creating an object of BufferedReader class
		BufferedReader br
				= new BufferedReader(new FileReader(file));

		// Declaring a string variable
		String st;
		// Condition holds true till
		// there is character in a string
		while ((st = br.readLine()) != null)

			// Print the string
			System.out.println(st);

		return st;
	}

}