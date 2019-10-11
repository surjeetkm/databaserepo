package com.db;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="/getcust/{id}",method=RequestMethod.GET)
	public Customer getCustomer(@PathVariable("id") int id) {
		System.out.println("================Find By Id==========================");
		return repository.findById(id).get();
	}
	@RequestMapping(value="/getallcust",method=RequestMethod.GET)
	public List<Customer> getAllCustomer() {
		System.out.println("================Find By Id==========================");
		return repository.findAll();
	}
}
