package com.example.project.jpadb.controller;

import com.example.project.jpadb.dto.Message;
import com.example.project.jpadb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/contactus")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public List<Message> getAll(){
        return messageService.getAllMessage();
    }

    @GetMapping("/message/{id}")
    public Message getById(@PathVariable int id){
        return messageService.getById(id);
    }

    @PutMapping("add_message")
    public void addMessage(@RequestBody Message message){
        messageService.addMessage(message);
    }
}
