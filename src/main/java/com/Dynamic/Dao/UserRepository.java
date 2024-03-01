package com.Dynamic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dynamic.Entity.Contact;

public interface UserRepository extends JpaRepository<Contact, Integer> {
	public boolean existsByEmail(String email);
	
	public Contact findByEmail(String email);
} 
