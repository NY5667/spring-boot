package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/jdbc")
    public void index() {
//		String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
//		int result = jdbcTemplate.update(sql, "Head First Java", "Kathy Sierra", 18.55f);

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

		testUpdate();
		testListAll();
		testFind();
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

}
