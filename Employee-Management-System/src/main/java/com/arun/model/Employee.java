package com.arun.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private long empId;
	
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name="department",referencedColumnName="dep_id")
	private Department department;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_of_birth")
	private Date dob;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="martial_status")
	private String martialStatus;
	
	@Column(name="photo")
	@Lob
	private byte[] photo;
	
	@Column(name="employee_type")
	private String employeeType;
	
	@Column(name="designation")
	private String designation;
	
	@Column(name="joining_date")
	private Date joiningDate;
	
	@Column(name="country")
	private String country;
	
	@Column(name="state")
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="address")
	private String address;
	
	@Column(name="pin")
	private String pin;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="qualification")
	private String qualification;
	
	

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMartialStatus() {
		return martialStatus;
	}

	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joinedDate) {
		this.joiningDate = joinedDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Employee(String firstName, Department department, String lastName, Date dob, String gender,
			String martialStatus, byte[] photo, String employeeType, String designation, Date joinedDate,
			String country, String state, String city, String address, String pin, String phone, String email,
			String qualification) {
		super();
		this.firstName = firstName;
		this.department = department;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.martialStatus = martialStatus;
		this.photo = photo;
		this.employeeType = employeeType;
		this.designation = designation;
		this.joiningDate = joinedDate;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.pin = pin;
		this.phone = phone;
		this.email = email;
		this.qualification = qualification;
	}
	public Employee(String firstName, Department department, String lastName, Date dob, String gender,
			String martialStatus,String employeeType, String designation, Date joinedDate,
			String country, String state, String city, String address, String pin, String phone, String email,
			String qualification) {
		super();
		this.firstName = firstName;
		this.department = department;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.martialStatus = martialStatus;
		this.employeeType = employeeType;
		this.designation = designation;
		this.joiningDate = joinedDate;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.pin = pin;
		this.phone = phone;
		this.email = email;
		this.qualification = qualification;
	}
	
	

	public Employee() {}

	
}
