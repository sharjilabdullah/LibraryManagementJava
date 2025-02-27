package com.example.LibraryManagement.service;

import com.example.LibraryManagement.dto.BookDTO;
import com.example.LibraryManagement.entity.Book;
import com.example.LibraryManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO addBooks(BookDTO bookDTO){
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setAvailable(bookDTO.getAvailable());
        Book addedBook = bookRepository.save(book);
        return new BookDTO(addedBook.getId(),addedBook.getTitle(),addedBook.getAuthor(),addedBook.getCategory(),addedBook.getAvailable());
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll().stream().map(book -> new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getCategory(),book.getAvailable())).collect(Collectors.toList());
    }

    public List<BookDTO> getBookByAuthor(String author){
        return bookRepository.findByAuthor(author).stream().map(book -> new BookDTO(book.getId(),book.getTitle(),book.getAuthor(),book.getCategory(),book.getAvailable())).collect(Collectors.toList());
    }

    public boolean deleteBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public BookDTO editBook(Long id, BookDTO bookDTO){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(bookDTO.getTitle());
            book.setAvailable(bookDTO.getAvailable());
            book.setAuthor(bookDTO.getAuthor());
            book.setCategory(bookDTO.getCategory());

            Book updatedBook = bookRepository.save(book);
            return new BookDTO(updatedBook.getId(), updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getCategory(), updatedBook.getAvailable());
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Book not found");
        }
    }
}
