package com.Dynamic.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Dynamic.Entity.PartnerRegistration;

public interface PartnerRepository extends JpaRepository<PartnerRegistration, Integer> {
	public boolean existsByEmail(String email);

	public PartnerRegistration findByEmail(String email);
}
