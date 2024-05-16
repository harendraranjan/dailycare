package com.Dynamic.service;

import java.util.List;

import com.Dynamic.Entity.JoinUs;

public interface JoinUsService {
    JoinUs saveJoinUs(JoinUs partReg);
    
    boolean existEmailCheck(String email);
    
    void removeSessionMessage();

    List<JoinUs> getAllPartners();
    
    public boolean deletePartner(int id);

	JoinUs getPartnerById(int id);

	boolean updatePartner(JoinUs user);
}
