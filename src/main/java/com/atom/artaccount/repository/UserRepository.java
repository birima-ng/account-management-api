package com.atom.artaccount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.User;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByUsername(String username);
 	//User findById(String id);
 	//@Query("SELECT u FROM user u where u.username=?1 and u.password=?2")
 	User findByUsernameAndPassword(String username,String password);
 	User findByCodeactivationAndToken(String codeacctivation,String token);
 	User findByEmail(String email);
 	List<User> findByPaysIdAndSystemeId(String paysId, String systemeId);
}