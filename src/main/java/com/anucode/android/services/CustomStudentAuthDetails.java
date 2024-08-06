package com.anucode.android.services;


import com.anucode.android.entities.Roles;
import com.anucode.android.entities.StudentAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomStudentAuthDetails extends StudentAuth implements UserDetails {

    private final String username;
    private final String password;
    private Collection<?extends GrantedAuthority> authorities;

    public CustomStudentAuthDetails(StudentAuth userInfo) {
        this.username = userInfo.getName();
        this.password = userInfo.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        for(Roles role: userInfo.getRoles()){
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
        }
        this.authorities = auths;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

}
