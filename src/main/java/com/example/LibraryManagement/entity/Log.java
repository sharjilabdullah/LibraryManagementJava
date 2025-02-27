package com.example.LibraryManagement.entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collation = "logs")
public class Log {
    @Id
    private String id;
    private String userId;
    private String action;
    private Date timestamp;
}
