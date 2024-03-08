package com.Dynamic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Dynamic.Dao.UserRegistrationRepository;
import com.Dynamic.Entity.UserRegistration;

@Service
public class CustomUserRegistrionDetailService implements UserDetailsService {
    
    private final UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public CustomUserRegistrionDetailService(UserRegistrationRepository userRegistrationRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRegistration user = userRegistrationRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            return new CustomUser(user);
        }
    }
}
