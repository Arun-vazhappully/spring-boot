package com.arun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arun.model.Department;
import com.arun.model.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{
	List<Notice> findByDepartment(Department department);
}
