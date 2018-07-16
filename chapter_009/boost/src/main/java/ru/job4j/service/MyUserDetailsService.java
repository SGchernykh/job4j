package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.domain.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private UserService userService;

    @Autowired
    public MyUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        User user = this.userService.getByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException(login);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRole()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }


}
