package com.Dynamic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.Dynamic.Dao.UserRepository;
import com.Dynamic.Entity.Contact;
import com.Dynamic.Entity.UserRegistration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Contact saveUser(Contact contact) {
		
		Contact SaveCont=userRepository.save(contact);
		
		return SaveCont;
	}
	
	
	@Override
	public boolean existEmailChack(String email) {
		
		return userRepository.existsByEmail(email);
	}

	@Override
	public void removeSessionMsg() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("msg");
	}

}
