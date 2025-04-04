package com.example.demo.Security.userRegistrationExample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Security.userRegistrationExample.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);

	
}