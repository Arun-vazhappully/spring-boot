package com.arun.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="departments")
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dep_id")
	private long depId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	public long getDepId() {
		return depId;
	}

	public void setDepId(long depId) {
		this.depId = depId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department(long depId,String name, String description) {
		super();
		this.depId = depId;
		this.name = name;
		this.description = description;
	}
	
	public Department(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	
	public Department() {}

	@Override
	public String toString() {
		return "Department [depId=" + depId + ", name=" + name + ", description=" + description + "]";
	}
	
	
}
