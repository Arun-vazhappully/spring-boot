package com.arun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Employee;
import com.arun.model.Leave;
import com.arun.model.LeaveStatus;
import com.arun.repository.EmployeeRepository;
import com.arun.repository.LeaveRepository;

@Service
public class LeaveService {
	@Autowired
	private LeaveRepository leaveRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public ResponseEntity<String> apply(Leave leaveDetails, long empId) {
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not found with id: "+empId));
		Leave leave = new Leave(
				employee,
				leaveDetails.getFrom(),
				leaveDetails.getTo(),
				leaveDetails.getType(),
				leaveDetails.getDescription(),
				LeaveStatus.PENDING
				);
		leaveRepository.save(leave);
		return ResponseEntity.ok("Leave applied successfully");
	}

	public ResponseEntity<String> update(Leave leaveDetails, long lid) {
		Leave leave = leaveRepository.findById(lid).orElseThrow(()->new ResourceNotFoundException("Leave not found with id: "+lid));
		
		leave.setFrom(leaveDetails.getFrom());
		leave.setTo(leaveDetails.getTo());
		leave.setType(leaveDetails.getType());
		leave.setDescription(leaveDetails.getDescription());
		
		leaveRepository.save(leave);
		return ResponseEntity.ok("Leave details updated successfully");
	}

	public ResponseEntity<String> approve(long lid) {
		Leave leave = leaveRepository.findById(lid).orElseThrow(()->new ResourceNotFoundException("Leave not found with id: "+lid));
		leave.setStatus(LeaveStatus.APPROVED);
		leaveRepository.save(leave);
		return ResponseEntity.ok("Leave approved");
	}

	public ResponseEntity<String> reject(long lid) {
		Leave leave = leaveRepository.findById(lid).orElseThrow(()->new ResourceNotFoundException("Leave not found with id: "+lid));
		leave.setStatus(LeaveStatus.REJECTED);
		leaveRepository.save(leave);
		return ResponseEntity.ok("Leave rejected");
	}

	public ResponseEntity<String> delete(long lid) {
		if(leaveRepository.findById(lid).isPresent()) {
			leaveRepository.deleteById(lid);
			return ResponseEntity.ok("Leave request deleted successfully");
		}
		else{
			throw new ResourceNotFoundException("Leave not with found with id: "+lid);
		}
	}

	public ResponseEntity<List<Leave>> getByEmployee(long empId) {
		Employee employee = employeeRepository.findById(empId).orElseThrow(()->new ResourceNotFoundException("Employee not found with id: "+empId));
		List<Leave> leaves = leaveRepository.findByEmployee(employee);
		return ResponseEntity.ok(leaves);
	}
	
}
