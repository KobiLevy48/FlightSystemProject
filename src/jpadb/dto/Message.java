package com.example.project.jpadb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Data Transfer Object
public class Message {
    @Id
    private int messageId;
    private String senderName;
    private String content;
}
