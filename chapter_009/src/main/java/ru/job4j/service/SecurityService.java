package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SecurityService {
    private AuthenticationManager authenticationManager;
    private UserDetailsService detailsService;

    @Autowired
    public SecurityService(AuthenticationManager authenticationManager, UserDetailsService detailsService) {
        this.authenticationManager = authenticationManager;
        this.detailsService = detailsService;
    }
    @Transactional(readOnly = true)
    public String findLoggedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    @Transactional(readOnly = true)
    public void autoLogin(final String userName, final String password) {
        UserDetails userDetails = this.detailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        }
    }
}
