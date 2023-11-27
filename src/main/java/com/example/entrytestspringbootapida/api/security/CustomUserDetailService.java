package com.example.entrytestspringbootapida.api.security;

import com.example.entrytestspringbootapida.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.FindByUsername(username).orElseThrow();
//        System.out.println("name: " + user.getUsername());
//        System.out.println("pass: " + user.getPassword());
        return new UserPrincipal(user.getUserId(), user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority(user.getRole())));
    }
}
