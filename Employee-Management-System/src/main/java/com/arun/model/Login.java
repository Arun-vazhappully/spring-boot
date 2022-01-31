package com.arun.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="login")
public class Login implements UserDetails{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="log_id")
	private long logId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="user")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@Column(name="locked")
	private Boolean locked = false;
	
	@OneToOne
	@JoinColumn(name="emp_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Employee employee;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(authority);
	}

	
	public long getLogId() {
		return logId;
	}


	public void setLogId(long logId) {
		this.logId = logId;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public Boolean getLocked() {
		return locked;
	}


	public void setLocked(Boolean locked) {
		this.locked = locked;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public Login() {}
	
	

	public Login(String email, String password, UserRole userRole, Employee employee) {
		super();
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.employee = employee;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
