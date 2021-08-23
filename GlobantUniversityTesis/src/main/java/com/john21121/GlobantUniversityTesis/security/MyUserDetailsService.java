package com.john21121.GlobantUniversityTesis.security;

import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User userEntity = userRepository.findByUsername(s);
        if (userEntity==null){
            throw new UsernameNotFoundException(s);
        }

        return org.springframework.security.core.userdetails.User.withUsername
                (userEntity.getUsername()).password(userEntity.getPassword()).authorities("User").build();
    }
}
