package com.Dynamic.service;

import com.Dynamic.Entity.UserRegistration;

public interface UserRegistrationServise {
	public UserRegistration saveUserRegistration(UserRegistration userRegistrion);
	
	public boolean existEmailChack(String email);
	
	public void removeSessionMsg();
}
