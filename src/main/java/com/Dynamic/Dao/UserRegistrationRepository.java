package com.Dynamic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dynamic.Entity.UserRegistration;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer>{
	public boolean existsByEmail(String email);
	public UserRegistration findByEmail(String email);
	
}
