package com.example.smartfarming.service;

import com.example.smartfarming.entity.Client;
import com.example.smartfarming.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username).orElseThrow();
        return new User(client.getEmail(), client.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}