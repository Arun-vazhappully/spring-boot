package com.arun.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="token")
public class ResetPasswordToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tid")
	private long tid;
	
	@Column(name="token")
	private String token;
	
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name="emp_id")
	private Employee employee;
	
	@Column(name="expires_at")
	private LocalDateTime expiresAt;

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	public ResetPasswordToken(String token, Employee employee, LocalDateTime expiresAt) {
		super();
		this.token = token;
		this.employee = employee;
		this.expiresAt = expiresAt;
	}

	public ResetPasswordToken(long tid, String token, Employee employee, LocalDateTime expiresAt) {
		super();
		this.tid = tid;
		this.token = token;
		this.employee = employee;
		this.expiresAt = expiresAt;
	}
	
	public ResetPasswordToken() {}


}
