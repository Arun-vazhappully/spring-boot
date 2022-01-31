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
import com.arun.model.Department;
import com.arun.model.Notice;
import com.arun.repository.DepartmentRepository;
import com.arun.repository.NoticeRepository;
import com.arun.service.NoticeService;

@RestController
public class NoticeController {
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/notices")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Notice>> getNotices(){
		return ResponseEntity.ok(noticeRepository.findAll());
	}
	
	@GetMapping("/notice/{nid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Notice> getNotice(@PathVariable long nid){
		Notice notice = noticeRepository.findById(nid).orElseThrow(()->new ResourceNotFoundException("Notice not found with id: "+nid));
		return ResponseEntity.ok(notice);
	}
	@GetMapping("/notice/byDepartment/{depId}")
	public ResponseEntity<List<Notice>> getByDepartment(@PathVariable long depId){
		Department department = departmentRepository.findById(depId).orElseThrow(()->new ResourceNotFoundException("Department not found with id: "+depId));
		return ResponseEntity.ok(noticeRepository.findByDepartment(department));
	}
	
	@PostMapping("/notice/send")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> sendNotice(@RequestBody Notice notice){
		return noticeService.send(notice);
	}
	
	@PutMapping("/notice/update/{nid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateNotice(@RequestBody Notice notice,@PathVariable long nid){
		return noticeService.update(notice,nid);
	}
	
	@DeleteMapping("/notice/delete/{nid}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteNotice(@PathVariable long nid){
		return noticeService.delete(nid);
	}
}
