package com.atom.artaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atom.artaccount.model.Message;

public interface MessageRepository extends JpaRepository<Message, String>, JpaSpecificationExecutor<Message> {

}