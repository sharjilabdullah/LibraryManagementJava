package com.example.LibraryManagement.repository;

import com.example.LibraryManagement.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {
}