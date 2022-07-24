package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
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