package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.entity.Transaction;
import com.example.LibraryManagement.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/borrow")
    public CompletableFuture<ResponseEntity<Transaction>> borrowBooks (@RequestParam Long userId, @RequestParam Long bookId){
        return transactionService.borrowBooks(userId, bookId)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/return")
    public CompletableFuture<ResponseEntity<Transaction>> returnBook(@RequestParam Long transactionId) {
        return transactionService.returnBooks(transactionId)
                .thenApply(ResponseEntity::ok);
    }

    //TODO: If the book is already returned then print some error
}
