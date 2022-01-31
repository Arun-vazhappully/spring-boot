package com.arun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arun.model.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
	
}
