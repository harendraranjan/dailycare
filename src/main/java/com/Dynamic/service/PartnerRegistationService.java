package com.Dynamic.service;

import java.util.List;

import com.Dynamic.Entity.PartnerRegistration;


public interface PartnerRegistationService {
	public PartnerRegistration  savePartnerRegistration(PartnerRegistration partReg);
	
	public boolean existEmailCheck(String email);
	
	public void removeSessionMassage();

	List<PartnerRegistration> getAllPartners();
}
