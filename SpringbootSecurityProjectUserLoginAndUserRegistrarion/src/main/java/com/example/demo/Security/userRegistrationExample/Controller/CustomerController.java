package com.example.demo.Security.userRegistrationExample.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Security.userRegistrationExample.Service.CustomerService;
import com.example.demo.Security.userRegistrationExample.entity.Customer;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService cusservice;
	@Autowired
	private AuthenticationManager authmanager;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Customer c) {
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(c.getEmail(),c.getPwd());
		//verify login details valid or not
		Authentication auth=authmanager.authenticate(token);
		boolean status=auth.isAuthenticated();
		if(status) {
			return new ResponseEntity<>("welcome",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
		}
		
		
		  
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer c){
		boolean status=cusservice.SaveCustomer(c);
		if(status) {
			return new ResponseEntity<>("Success",HttpStatus.CREATED);//,HttpStatus.CREATED;
		}else {
			return new ResponseEntity<>("Failed",HttpStatus.INTERNAL_SERVER_ERROR);//,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
