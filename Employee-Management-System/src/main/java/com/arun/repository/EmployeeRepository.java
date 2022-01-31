package com.arun.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arun.model.Department;
import com.arun.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	List<Employee> findByDepartment(Department department);
	Optional<Employee> findByEmail(String email);
}
