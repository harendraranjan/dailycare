package com.Dynamic.service;

import java.util.List;

import com.Dynamic.Entity.Contact;
import com.Dynamic.Entity.UserRegistration;

public interface UserService {
	public Contact saveUser(Contact contact);
	
	
	public boolean existEmailChack(String email);
	
	
	public void removeSessionMsg();
}
