package com.employee.management.service;

import com.employee.management.entity.MyUser;
import com.employee.management.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUseRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUser = myUseRepository.findByUsername(username);
        if (myUser.isPresent()) {
           MyUser userObj=myUser.get();

           return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole())
                    .build();
        }
        else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
