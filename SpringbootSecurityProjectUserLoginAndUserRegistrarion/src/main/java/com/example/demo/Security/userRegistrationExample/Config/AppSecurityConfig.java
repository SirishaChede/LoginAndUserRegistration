package com.example.demo.Security.userRegistrationExample.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.Security.userRegistrationExample.Service.CustomerService;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	@Autowired
	private CustomerService customerservice;
	@Bean
	// used for to encrypt the password
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	//AuthProvider is used for to load the user record and give to auth Manager
	public DaoAuthenticationProvider authprovider() {
		DaoAuthenticationProvider authprovider=new DaoAuthenticationProvider();
		authprovider.setPasswordEncoder(pwdEncoder());
		authprovider.setUserDetailsService(customerservice);
		return authprovider;
	}
	//authentication manager is responsible for check the login details are valid or not and perform authentication
	@Bean
	public AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	@SneakyThrows
	public SecurityFilterChain security(HttpSecurity http) {
		http.
		authorizeHttpRequests ((req)->{
		req.requestMatchers("/register","/login")
		.permitAll()
		.anyRequest()
		.authenticated();
		});
		
		return http.csrf().disable().build();
	}

}
