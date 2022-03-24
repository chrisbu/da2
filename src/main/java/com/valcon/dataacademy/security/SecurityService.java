package com.valcon.dataacademy.security;

import com.valcon.dataacademy.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements ISecurityService {

    @Override
    public User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal principal = (MyUserPrincipal)auth.getPrincipal();
        return principal.getUser();
    }
}
