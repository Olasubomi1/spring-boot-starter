package com.olasubomi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

	record NewCustomerRequest(String name, String email, Integer age){}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}

	record UpdateCustomerRequest(String name, String email, Integer age){}
	@PutMapping("{customerId}")
	public void updateCustomer(@RequestBody  Customer customerUpdate, @PathVariable("customerId") Integer id){
		customerRepository.findById(id).map(customer -> {
			customer.setName(customerUpdate.getName());
			customer.setEmail(customerUpdate.getEmail());
			customer.setAge(customerUpdate.getAge());
			return customerRepository.save(customer);
		});
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
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
