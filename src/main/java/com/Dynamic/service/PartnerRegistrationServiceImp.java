package com.Dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Dynamic.Dao.PartnerRepository;
import com.Dynamic.Entity.PartnerRegistration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Service
public class PartnerRegistrationServiceImp  implements PartnerRegistationService {
	
	@Autowired
	private PartnerRepository partnerRepository;

	@Override
	public PartnerRegistration savePartnerRegistration(PartnerRegistration partnerRegistration) {
		PartnerRegistration savePartner = partnerRepository.save(partnerRegistration);
		return savePartner;
	}

	@Override
	public boolean existEmailCheck(String email) {
		return partnerRepository.existsByEmail(email);
	}

	@Override
	public void removeSessionMassage() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("msg");
	}

	@Override
	public List<PartnerRegistration> getAllPartners() {
		
		return partnerRepository.findAll();
	}



}
