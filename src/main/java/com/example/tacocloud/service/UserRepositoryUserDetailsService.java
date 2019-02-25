package com.example.tacocloud.service;

import com.example.tacocloud.models.User;
import com.example.tacocloud.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private JpaUserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(JpaUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null)
            return  user;
        throw new UsernameNotFoundException("User " + username + " not found!");
    }
}
