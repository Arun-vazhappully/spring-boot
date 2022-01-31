package com.arun.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rules")
public class Rule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rid")
	private long rid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
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

	public Rule(long rid, String name, String description) {
		super();
		this.rid = rid;
		this.name = name;
		this.description = description;
	}
	
	public Rule(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public Rule() {}
}
