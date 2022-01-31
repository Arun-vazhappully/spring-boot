package com.arun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arun.exception.ResourceNotFoundException;
import com.arun.model.Department;
import com.arun.model.Notice;
import com.arun.repository.DepartmentRepository;
import com.arun.repository.NoticeRepository;

@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public ResponseEntity<String> send(Notice noticeDetails) {
		if(noticeDetails.getDepartment()==null) {
			List<Department> departments = departmentRepository.findAll();
			for (Department department : departments) {
				Notice notice = new Notice(
							department,
							noticeDetails.getTitle(),
							noticeDetails.getDescription()
						);
				noticeRepository.save(notice);
			}
			return ResponseEntity.ok("Notice sent successfully");
		}
		else {
			noticeRepository.save(noticeDetails);
			return ResponseEntity.ok("Notice sent successfully");
		}
	}

	public ResponseEntity<String> delete(long nid) {
		if(noticeRepository.findById(nid).isPresent()) {
			noticeRepository.deleteById(nid);
			return ResponseEntity.ok("Notice deleted successfully");
		}
		else {
			throw new ResourceNotFoundException("Notice not found with id: "+nid);
		}
	}

	public ResponseEntity<String> update(Notice noticeDetails, long nid) {
		Notice notice = noticeRepository.findById(nid).orElseThrow(()->new ResourceNotFoundException("Notice not found with id: "+nid));
		
		notice.setTitle(noticeDetails.getTitle());
		notice.setDescription(noticeDetails.getDescription());
		
		if(noticeDetails.getDepartment()==null) {
			List<Department> departments = departmentRepository.findAll();
			for (Department department : departments) {
				if(department!=notice.getDepartment()) {
					Notice newNotice = new Notice(
								department,
								noticeDetails.getTitle(),
								noticeDetails.getDescription()
							);
					noticeRepository.save(newNotice);
				}
			}
			
		}
		else {
			notice.setDepartment(noticeDetails.getDepartment());
		}
		noticeRepository.save(notice);
		return ResponseEntity.ok("Notice details updated successfully");
	}
	
}
