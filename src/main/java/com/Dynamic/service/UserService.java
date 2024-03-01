package com.Dynamic.service;

import com.Dynamic.Entity.Contact;

public interface UserService {
	public Contact saveUser(Contact contact);
	
	
	public boolean existEmailChack(String email);
	
	public void removeSessionMsg();
}
