package com.valcon.dataacademy.security;

import com.valcon.dataacademy.model.User;

public interface ISecurityService {
    User getLoggedInUser();
}
