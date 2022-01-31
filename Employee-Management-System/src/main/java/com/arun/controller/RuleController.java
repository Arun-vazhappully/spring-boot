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
import com.arun.model.Rule;
import com.arun.repository.RuleRepository;
import com.arun.service.RuleService;

@RestController
public class RuleController {
	@Autowired
	private RuleRepository ruleRepository;
	@Autowired
	private RuleService ruleService;
	
	@GetMapping("/rules")
	public ResponseEntity<List<Rule>> getRules(){
		return ResponseEntity.ok(ruleRepository.findAll());
	}
	
	@GetMapping("/rule/{rid}")
	public ResponseEntity<Rule> getRule(@PathVariable long rid){
		Rule rule = ruleRepository.findById(rid).orElseThrow(()->new ResourceNotFoundException("Rule not found with id: "+rid));
		return ResponseEntity.ok(rule);
	}
	
	@PostMapping("/rule/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> addRule(@RequestBody Rule rule){
		ruleRepository.save(rule);
		return ResponseEntity.ok("Rule added successfully");
	}
	
	@PutMapping("/rule/update/{rid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateRule(@RequestBody Rule rule,@PathVariable long rid){
		return ruleService.update(rule,rid);
	}
	
	@DeleteMapping("/rule/delete/{rid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteRule(@PathVariable long rid){
		return ruleService.delete(rid);
	}
}
