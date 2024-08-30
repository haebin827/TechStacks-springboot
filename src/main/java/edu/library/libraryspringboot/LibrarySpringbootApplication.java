package edu.library.libraryspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibrarySpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySpringbootApplication.class, args);
	}
}
