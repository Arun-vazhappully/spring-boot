package com.arun.dto;

public class EmployeeCountResponse {
	private long active;
	private long inActive;
	private long total;
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}
	public long getInActive() {
		return inActive;
	}
	public void setInActive(long inActive) {
		this.inActive = inActive;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public EmployeeCountResponse(long active, long inActive, long total) {
		super();
		this.active = active;
		this.inActive = inActive;
		this.total = total;
	}
	
	
}
