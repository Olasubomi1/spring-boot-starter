package com.olasubomi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class SpringBootStarterApplication {

	private final CustomerRepository customerRepository;

	public SpringBootStarterApplication(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
	}

	@GetMapping
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
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
