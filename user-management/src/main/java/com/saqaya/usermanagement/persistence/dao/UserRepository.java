package com.saqaya.usermanagement.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saqaya.usermanagement.persistence.entity.User;


public interface UserRepository extends JpaRepository<User, String> {
     
     User findByEmail(String email);

}
