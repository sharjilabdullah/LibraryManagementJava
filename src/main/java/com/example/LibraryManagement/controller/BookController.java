package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.dto.BookDTO;
import com.example.LibraryManagement.service.BookService;
import org.glassfish.jaxb.core.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //Check how can we add multiple books together
    @PostMapping("/addBook")
    public ResponseEntity<BookDTO> addBook (@RequestBody BookDTO bookDTO){
        BookDTO addBook = bookService.addBooks(bookDTO);
        return  ResponseEntity.ok(addBook);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    //If the book is not found by author, return with some error
    @GetMapping("/author")
    public ResponseEntity<?> getBooksByAuthor(@RequestParam String author){
        List<BookDTO> books = bookService.getBookByAuthor(author);
        if(books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No books found for author :" +author);
        }
        return ResponseEntity.ok(books);
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        boolean isDeleted = bookService.deleteBook(id);
        if(isDeleted){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<BookDTO> editBook(@RequestParam Long id, @RequestBody BookDTO bookDTO){
       BookDTO updatedBook =  bookService.editBook(id,bookDTO);
       return ResponseEntity.ok(updatedBook);
    }
}
