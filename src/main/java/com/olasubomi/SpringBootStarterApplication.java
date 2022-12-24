package com.olasubomi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class SpringBootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

	@GetMapping("/")
	public String greet(){
		return "Hi Soft";
	}

	@GetMapping("/greet")
	public GreetResponse grit(){
		GreetResponse greetResponse = new GreetResponse("Hi, Mr Soft", List.of("java", "python", "javascript"), new Person("Adewale", 30, 20000));
		return greetResponse;
	}

	record GreetResponse(String greet, List<String> favProgrammingLanguage, Person person){}
	record Person(String name, int age, double savings){}
}
