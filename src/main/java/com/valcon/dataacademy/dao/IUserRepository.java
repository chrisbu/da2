package com.valcon.dataacademy.dao;

import com.valcon.dataacademy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
