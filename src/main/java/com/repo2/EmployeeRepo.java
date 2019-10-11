package com.repo2;

import org.springframework.data.repository.CrudRepository;

import com.domain2.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

}
