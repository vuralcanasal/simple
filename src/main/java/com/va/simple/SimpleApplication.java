package com.va.simple;

import com.va.simple.model.Customer;
import com.va.simple.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleApplication implements CommandLineRunner {
	private final CustomerRepository customerRepository;

	public SimpleApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimpleApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Customer customer = customerRepository.save(new Customer("John","Karabeyler"));
		System.out.println(customer);
	}
}
