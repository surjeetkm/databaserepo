package com.db;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.Customer;
import com.domain2.Employee;
import com.repo.CustomerRepository;
import com.repo2.EmployeeRepo;

@RestController
public class CustomerResource {

	@Autowired
	CustomerRepository repository;

	@Autowired
	EmployeeRepo employeerepo;

	@GetMapping("/bulkcreate")
	public String bulkcreate() {
		System.out.println("==========================CONTROLLER REACHED===================");
		// save a single Customer
		Customer cust=new Customer("Jagdish", "Mohanty");
		repository.save(cust);
		// save a list of Customers
		repository.saveAll(Arrays.asList(new Customer("Daba", "Baral"), new Customer("Aravinda", "Swain"),
				new Customer("Tarzan", "Patra"), new Customer("Subodh", "Rout")));
		Employee emp=new Employee("Jagdish","Mohanty");
		employeerepo.save(emp);
		return "Customers are created";
	}
}
