package com.arun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arun.model.Employee;
import com.arun.model.Leave;
import com.arun.model.LeaveStatus;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
	List<Leave> findByEmployee(Employee employee);
	List<Leave> findByStatus(LeaveStatus status);
}
