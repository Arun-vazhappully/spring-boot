package com.arun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arun.repository.LoginRepository;

@Service
public class LoginService implements UserDetailsService{

	private final static String USER_NOT_FOUND = "user with email %s not found";
	@Autowired
	private LoginRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return repository.findByEmail(email)
				.orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND,email)));
	}
	

}
