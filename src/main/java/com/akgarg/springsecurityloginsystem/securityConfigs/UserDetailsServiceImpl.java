package com.akgarg.springsecurityloginsystem.securityConfigs;

import com.akgarg.springsecurityloginsystem.entity.User;
import com.akgarg.springsecurityloginsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(username);

        System.out.println("user is: " + user);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with " + username + " email");
        }

        return new UserDetailsImpl(user);
    }
}
