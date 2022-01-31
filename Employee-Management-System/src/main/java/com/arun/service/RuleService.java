package com.arun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Rule;
import com.arun.repository.RuleRepository;

@Service
public class RuleService {
	@Autowired
	private RuleRepository ruleRepository;

	public ResponseEntity<String> update(Rule ruleDetails, long rid) {
		Rule rule = ruleRepository.findById(rid).orElseThrow(()->new ResourceNotFoundException("rule not found with id: "+rid));
		rule.setName(ruleDetails.getName());
		rule.setDescription(ruleDetails.getDescription());
		
		ruleRepository.save(rule);
		return ResponseEntity.ok("Rule updated successfully");
	}

	public ResponseEntity<String> delete(long rid) {
		if(ruleRepository.findById(rid).isPresent()) {
			ruleRepository.deleteById(rid);
			return ResponseEntity.ok("Rule deleted successfully");
		}
		else {
			throw new ResourceNotFoundException("Rule not found with id: "+rid);
		}
	}
	
	
}
