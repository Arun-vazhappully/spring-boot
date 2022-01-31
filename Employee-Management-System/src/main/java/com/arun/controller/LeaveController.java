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
import com.arun.model.Leave;
import com.arun.model.LeaveStatus;
import com.arun.repository.LeaveRepository;
import com.arun.service.LeaveService;

@RestController
public class LeaveController {
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private LeaveService leaveService;
	
	@GetMapping("/leaves")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Leave>> getLeaves(){
		return ResponseEntity.ok(leaveRepository.findAll());
	}
	
	@GetMapping("/leave/{lid}")
	public ResponseEntity<Leave> getLeave(@PathVariable long lid){
		Leave leave = leaveRepository.findById(lid).orElseThrow(()->new ResourceNotFoundException("Leave not found with id: "+lid));
		return ResponseEntity.ok(leave);
	}
	
	@GetMapping("/leaves/pending")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Leave>> getPendingLeaves(){
		return ResponseEntity.ok(leaveRepository.findByStatus(LeaveStatus.PENDING));
	}
	
	@GetMapping("/leaves/approved")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Leave>> getApprovedLeaves(){
		return ResponseEntity.ok(leaveRepository.findByStatus(LeaveStatus.APPROVED));
	}
	
	@GetMapping("/leaves/rejected")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Leave>> getRejectedLeaves(){
		return ResponseEntity.ok(leaveRepository.findByStatus(LeaveStatus.REJECTED));
	}
	
	@GetMapping("/leaves/byEmployee/{empId}")
	public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable long empId) {
		return leaveService.getByEmployee(empId);
	}
	
	@PostMapping("/leave/apply/{empId}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> applyForLeave(@RequestBody Leave leave,@PathVariable long empId) {
		return leaveService.apply(leave,empId);
	}
	
	@PutMapping("/leave/update/{lid}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> updateLeave(@RequestBody Leave leave,@PathVariable long lid) {
		return leaveService.update(leave,lid);
	}
	
	@PutMapping("/leave/approve/{lid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> approveLeave(@PathVariable long lid) {
		return leaveService.approve(lid);
	}
	
	@PutMapping("/leave/reject/{lid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> rejectLeave(@PathVariable long lid) {
		return leaveService.reject(lid);
	}
	
	@DeleteMapping("/leave/delete/{lid}")
	@PreAuthorize("hasAuthority('USER')")
	public ResponseEntity<String> deleteLeave(@PathVariable long lid) {
		return leaveService.delete(lid);
	}
}

