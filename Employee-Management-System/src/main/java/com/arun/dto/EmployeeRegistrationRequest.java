package com.arun.dto;

import java.util.Date;

import com.arun.model.Department;

public class EmployeeRegistrationRequest {

	private Department department;
	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String martialStatus;
	private String employeeType;
	private String designation;
	private Date joiningDate;
	private String country;
	private String state;
	private String city;
	private String address;
	private String pin;
	private String phone;
	private String email;
	private String qualification;
	private String password;
	
	
	public String getMartialStatus() {
		return martialStatus;
	}
	public void setMartialStatus(String martialStatus) {
		this.martialStatus = martialStatus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getFirstname() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public EmployeeRegistrationRequest(Department department, String firstName, String lastName, Date dob,String gender,String martialStatus,
			String employeeType, String designation, Date joiningDate, String country, String state, String city,
			String address, String pin, String phone, String email, String qualification, String password) {
		super();
		this.department = department;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.martialStatus = martialStatus;
		this.dob = dob;
		this.employeeType = employeeType;
		this.designation = designation;
		this.joiningDate = joiningDate;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.pin = pin;
		this.phone = phone;
		this.email = email;
		this.qualification = qualification;
		this.password = password;
	}
	@Override
	public String toString() {
		return "EmployeeRegistrationRequest [department=" + department + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", gender=" + gender + ", martialStatus=" + martialStatus
				+ ", employeeType=" + employeeType + ", designation=" + designation + ", joiningDate=" + joiningDate
				+ ", country=" + country + ", state=" + state + ", city=" + city + ", address=" + address + ", pin="
				+ pin + ", phone=" + phone + ", email=" + email + ", qualification=" + qualification + ", password="
				+ password + "]";
	}
	
	
}
