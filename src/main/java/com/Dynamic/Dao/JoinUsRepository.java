package com.Dynamic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dynamic.Entity.JoinUs;



public interface JoinUsRepository extends JpaRepository<JoinUs, Integer> {
	public boolean existsByEmail(String email);

	public JoinUs findByEmail(String email);
}