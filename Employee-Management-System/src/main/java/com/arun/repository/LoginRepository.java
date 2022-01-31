package com.arun.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arun.model.Employee;
import com.arun.model.Login;
import com.arun.model.UserRole;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	Optional<Login> findByEmail(String email);
	Login findByEmployee(Employee employee);
	long countByUserRoleAndLocked(UserRole role,boolean locked);
}
