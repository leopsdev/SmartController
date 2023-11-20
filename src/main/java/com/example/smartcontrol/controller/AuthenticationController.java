package com.example.smartcontrol.controller;


import jakarta.validation.Valid;
import com.example.smartcontrol.domain.user.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usarnamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var authe = this.authenticationManager.authenticate(usarnamePassword);

        return ResponseEntity.ok().build();
    }

}
