package com.example.project.jpadb.service;

import com.example.project.jpadb.dto.Message;
import com.example.project.jpadb.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAllMessage(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message getById(int id){
        return messageRepository.findById(id).orElseThrow();
    }

    public void addMessage (Message message){
        messageRepository.save(message);
    }
}
