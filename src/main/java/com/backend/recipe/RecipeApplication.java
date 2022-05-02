package com.backend.recipe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class RecipeApplication {
	public static JsonData jsonData = null;

	public static void main(String[] args) {
		SpringApplication.run(RecipeApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			// read json
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
			try {
				jsonData = mapper.readValue(inputStream,JsonData.class);
//				jsonData.getRecipes().forEach(r -> System.out.println(r));
			} catch (IOException e){
				System.out.println("Unable to read recipes: " + e.getMessage());
			}
		};
	}
}
