package com.Dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Dynamic.Dao.JoinUsRepository;
import com.Dynamic.Entity.JoinUs;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class JoinUsServiceImpl implements JoinUsService {
    
    @Autowired
    private JoinUsRepository joinUsRepository;

    @Override
    public JoinUs saveJoinUs(JoinUs partReg) {
        JoinUs savedPartner = joinUsRepository.save(partReg);
        return savedPartner;
    }

    @Override
    public boolean existEmailCheck(String email) {
        return joinUsRepository.existsByEmail(email);
    }

    @Override
    public void removeSessionMessage() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute("msg");
            }
        }
    }

    @Override
    public List<JoinUs> getAllPartners() {
        return joinUsRepository.findAll();
    }
}
