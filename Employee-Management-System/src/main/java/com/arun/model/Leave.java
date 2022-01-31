 package com.arun.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp_leave")
public class Leave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="lid")
	private long lid;
	
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name="emp_id")
	private Employee employee;
	
	@Column(name="from_date")
	private Date from;
	
	@Column(name="to_date")
	private Date to;
	
	@Column(name="type")
	private String type;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;

	public long getLid() {
		return lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public Leave(long lid, Employee employee, Date from, Date to, String type, String description, LeaveStatus status) {
		super();
		this.lid = lid;
		this.employee = employee;
		this.from = from;
		this.to = to;
		this.type = type;
		this.description = description;
		this.status = status;
	}
	
	public Leave() {}

	public Leave(Date from, Date to, String type, String description) {
		super();
		this.from = from;
		this.to = to;
		this.type = type;
		this.description = description;
	}

	public Leave(Employee employee, Date from, Date to, String type, String description, LeaveStatus status) {
		super();
		this.employee = employee;
		this.from = from;
		this.to = to;
		this.type = type;
		this.description = description;
		this.status = status;
	}
	
	
}
