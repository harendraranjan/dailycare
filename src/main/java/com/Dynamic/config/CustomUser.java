package com.Dynamic.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Dynamic.Entity.UserRegistration;

public class CustomUser implements UserDetails{
	
	private UserRegistration userRegistration;

	public CustomUser(UserRegistration userRegistration) {
		super();
		this.userRegistration = userRegistration;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority=new SimpleGrantedAuthority(userRegistration.getRoll());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return userRegistration.getPassword();
	}

	@Override
	public String getUsername() {
		
		return userRegistration.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
