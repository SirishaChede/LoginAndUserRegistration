package com.example.demo.Security.userRegistrationExample.Service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Security.userRegistrationExample.Repository.CustomerRepository;
import com.example.demo.Security.userRegistrationExample.entity.Customer;

@Service
public class CustomerService implements UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	// User Details Service is used for load the user record this is automatically implemented through the 
	//unimplemented method of the customerservice class
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer c=customerRepository.findByEmail(email);
		return new User(c.getEmail(),c.getPwd(),Collections.emptyList());
	}
	
	
	
	public boolean SaveCustomer(Customer c) {
		String encodedpwd=pwdEncoder.encode(c.getPwd());
		c.setPwd(encodedpwd);
		Customer Savedcustomer = customerRepository.save(c);
		return Savedcustomer.getCid()!= null;
		
		
		
	}

	

}
