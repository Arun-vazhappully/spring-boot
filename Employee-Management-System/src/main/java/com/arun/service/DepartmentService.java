package com.arun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Department;
import com.arun.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public ResponseEntity<String> update(long depId, Department newDepartment) {
		Department department = departmentRepository.findById(depId).orElseThrow(()->new ResourceNotFoundException("Department not found with id: "+depId));
		department.setName(newDepartment.getName());
		department.setDescription(newDepartment.getDescription());
		
		departmentRepository.save(department);
		return ResponseEntity.ok("Department details updated successfully");
	}
	
	public ResponseEntity<String> delete(long depId) {
		if(departmentRepository.findById(depId).isPresent()) {
			departmentRepository.deleteById(depId);
			return ResponseEntity.ok("Department deleted successfully");
		}
		else {
			throw new ResourceNotFoundException("Department not found with id: "+depId);
		}
	}
	
}
