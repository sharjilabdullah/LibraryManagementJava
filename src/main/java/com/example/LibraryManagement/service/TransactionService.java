package com.example.LibraryManagement.service;

import com.example.LibraryManagement.entity.Book;
import com.example.LibraryManagement.entity.Transaction;
import com.example.LibraryManagement.entity.User;
import com.example.LibraryManagement.repository.BookRepository;
import com.example.LibraryManagement.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BookRepository bookRepository;

    @Async
    @Transactional
    public CompletableFuture<Transaction> borrowBooks (Long userId, Long bookId){
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isPresent() && book.get().getAvailable()) {
            Book book1 = book.get();
            book1.setAvailable(false);
            bookRepository.save(book1);

            Transaction transaction = new Transaction();
            transaction.setUser(new User(userId));
            transaction.setBook(book1);
            transaction.setBorrowDate(LocalDate.now());
            Transaction savedTransaction = transactionRepository.save(transaction);

            return CompletableFuture.completedFuture(savedTransaction);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found or not available");
    }

    @Async
    @Transactional
    public CompletableFuture<Transaction> returnBooks(Long transactionId) {
        Optional <Transaction> transactionOptional =  transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            transaction.setReturnDate(LocalDate.now());

            Book book = transaction.getBook();
            book.setAvailable(true);
            bookRepository.save(book);

            Transaction savedTransaction = transactionRepository.save(transaction);
            return CompletableFuture.completedFuture(savedTransaction);
        }
        throw new RuntimeException("Transaction not found");
    }


}
