package com.example.project.jpadb.repository;

import com.example.project.jpadb.dto.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message,Integer>{}
