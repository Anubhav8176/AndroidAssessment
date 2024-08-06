package com.anucode.android.services;


import com.anucode.android.entities.StudentAuth;
import com.anucode.android.repository.StudentAuthRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Data
public class StudentAuthServices implements UserDetailsService {

    @Autowired
    private StudentAuthRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentAuth studentAuth = repository.findByName(username);
        if (studentAuth == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(studentAuth.getName(), studentAuth.getPassword(),
                studentAuth.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }
}
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        StudentAuth user = repository.findByName(username);
//
//        if(user == null){
//            throw new UsernameNotFoundException("Username is null");
//        }
//        return new CustomStudentAuthDetails(user);
//    }
