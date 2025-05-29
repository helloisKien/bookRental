package com.books.bookRental;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookRentalApplication {

//	@Value("${jwt.secretKey}")
//	private String jwtKey;

	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);

	}

//	@PostConstruct
//	public void Test() {
//		System.out.println("jwtKey: " + jwtKey);
//	}

}
