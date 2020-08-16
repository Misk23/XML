package com.xml.publications.security;

import com.xml.publications.model.User.User;
import com.xml.publications.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user == null) throw new UsernameNotFoundException("No user found");
        grantedAuthorities.add(new SimpleGrantedAuthority("AUTHOR_ROLE"));
        if(user.getRole().toString().equals("REVIEWER_ROLE")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("REVIEWER_ROLE"));
        }
        if(user.getRole().toString().equals("EDITOR_ROLE")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("REVIEWER_ROLE"));
            grantedAuthorities.add(new SimpleGrantedAuthority("EDITOR_ROLE"));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername().toString(),
                user.getPassword().toString(),
                grantedAuthorities);
    }
}
