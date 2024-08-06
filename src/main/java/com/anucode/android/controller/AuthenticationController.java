package com.anucode.android.controller;

import com.anucode.android.model.JwtResponseDTO;
import com.anucode.android.model.StudentAuthDTO;
import com.anucode.android.services.JwtService;
import com.anucode.android.services.StudentAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentAuthServices studentAuthServices;

    @PostMapping("/api/login")
    public ResponseEntity AuthenticateAndGetToken(@RequestBody StudentAuthDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getName(), authRequestDTO.getPassword()));
        System.out.println(authentication.isAuthenticated());
        if(authentication.isAuthenticated()){

            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .token(jwtService.generateToken(authRequestDTO.getName()))
                    .build(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
