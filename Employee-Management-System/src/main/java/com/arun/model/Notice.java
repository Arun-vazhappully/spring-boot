package com.arun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="notice")
public class Notice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nid")
	private long nid;
	
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name="dep_id")
	private Department department;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;

	public long getNid() {
		return nid;
	}

	public void setNid(long nid) {
		this.nid = nid;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Notice(long nid,Department department, String title, String description) {
		super();
		this.nid = nid;
		this.department = department;
		this.title = title;
		this.description = description;
	}

	public Notice(Department department, String title, String description) {
		super();
		this.department = department;
		this.title = title;
		this.description = description;
	}
	
	public Notice() {}
}
