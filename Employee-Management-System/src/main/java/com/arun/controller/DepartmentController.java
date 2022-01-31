package com.arun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Department;
import com.arun.repository.DepartmentRepository;
import com.arun.service.DepartmentService;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/departments")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Department>> getDepartments(){
		return ResponseEntity.ok(departmentRepository.findAll());
	}
	
	@GetMapping("/department/{depId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Department> getDepartment(@PathVariable long depId){
		Department department = departmentRepository.findById(depId).orElseThrow(()->new ResourceNotFoundException("Department not found with id: "+depId));
		return ResponseEntity.ok(department);
	}
	
	@PostMapping("/department/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addDepartment(@RequestBody Department department) {
		departmentRepository.save(department);
		return ResponseEntity.ok("Department added successfully");
	}
	
	@PutMapping("/department/update/{depId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateDepartment(@RequestBody Department department,@PathVariable long depId) {
		return departmentService.update(depId,department);
	}
	
	@DeleteMapping("/department/delete/{depId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteDepartment(@PathVariable long depId) {
		return departmentService.delete(depId);
	}
}
