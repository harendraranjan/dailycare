package com.Dynamic.service;

import java.util.List;

import com.Dynamic.Entity.UserRegistration;

public interface UserRegistrationServise {
	public UserRegistration saveUserRegistration(UserRegistration userRegistrion);
	
	public boolean existEmailChack(String email);
	
	List<UserRegistration> getAllUser();
	
	public void removeSessionMsg();
	
	public boolean deleteUser(int id);
}
