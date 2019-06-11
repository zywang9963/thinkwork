package com.thinkwork.config.security;


import com.thinkwork.controller.AccountController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailsService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails userDetails = userService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Username not found.");
        }

        //加密过程在这里体现
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        if (!encoder.matches(password,(userDetails.getPassword()))) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, encoder.encode(password), authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
