package com.Dynamic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Dynamic.Dao.UserRegistrationRepository;
import com.Dynamic.Entity.UserRegistration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserRegistrationServiseImp implements UserRegistrationServise {
    
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserRegistration saveUserRegistration(UserRegistration userRegistration) {
        userRegistration.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userRegistration.setRoll("ROLE_USER");
        return userRegistrationRepository.save(userRegistration);
    }

    @Override
    public boolean existEmailChack(String email) {
        return userRegistrationRepository.existsByEmail(email);
    }

    @Override
    public void removeSessionMsg() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("msg");
    }


}
