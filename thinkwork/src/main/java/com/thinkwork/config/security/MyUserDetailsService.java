package com.thinkwork.config.security;

import com.thinkwork.Service.UserService;
import com.thinkwork.repository.UserRepository;
import com.thinkwork.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);
        if (user == null) {
            //throw exception inform front end not this user
            throw new UsernameNotFoundException("user + " + username + "not found.");
        }
        List<String> roleCodeList = userService.queryUserOwnedRoles(username);
        List<GrantedAuthority> authorities =
                roleCodeList.stream().map(e -> new SimpleGrantedAuthority(e)).collect(Collectors.toList());

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUserName(),user.getPassword(),authorities);

        return userDetails;
    }
}
