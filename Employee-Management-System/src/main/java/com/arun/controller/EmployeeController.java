package com.arun.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.arun.dto.EmployeeCountResponse;
import com.arun.dto.EmployeeRegistrationRequest;
import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Employee;
import com.arun.repository.EmployeeRepository;
import com.arun.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Employee> getEmployees(){
		return employeeRepository.findAll();
	}
	
	@GetMapping("/employee/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long empId){
		Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new ResourceNotFoundException("Could not find employee with id: "+empId));
		return ResponseEntity.ok(employee);
	}
	
	@GetMapping("/employees/byDepartment/{depId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable long depId){
		return employeeService.getByDepartment(depId);
	}
	
	@PostMapping("/employee/register")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> registerEmployee(@RequestPart EmployeeRegistrationRequest request,@RequestPart MultipartFile file){
		return employeeService.register(request,file);
	}
	
	@PutMapping("/employee/update/{empId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateEmployee(@RequestPart Employee employee,@RequestPart MultipartFile file,@PathVariable long empId) {
		return employeeService.update(employee,file,empId);
	}
	
	@PutMapping("/employee/lock/{empId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> lockEmployee(@PathVariable long empId) {
		return employeeService.lock(empId);
	}
	
	@PutMapping("/employee/unlock/{empId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> unlockEmployee(@PathVariable long empId) {
		return employeeService.unlock(empId);
	}
	
	@PutMapping("employee/changePassword/{empId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> changeAccountPassword(@RequestParam String currentPassword,@RequestParam String newPassword,@PathVariable long empId) {
		return employeeService.changePassword(currentPassword,newPassword,empId);
	}
	
	@GetMapping("/employees/count")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<EmployeeCountResponse> activeEmployees() {
		return ResponseEntity.ok(employeeService.count());
	}
	
	@PostMapping("/employee/forgotPassword")
	public ResponseEntity<String> forgotPasswordEmployee(@RequestParam String email) {
		return employeeService.forgotPassword(email);
	}
	
	@GetMapping("/employee/validateToken")
	public ResponseEntity<String> validateToken(@RequestParam String token){
		return employeeService.validateToken(token);
	}
	
	@PutMapping("/employee/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestParam String token,@RequestParam String password){
		return employeeService.resetPassword(token,password);
	}
}
