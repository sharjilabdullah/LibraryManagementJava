package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.entity.Review;
import com.example.LibraryManagement.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<Review> addReview (@RequestBody Review review){
        return ResponseEntity.ok(reviewService.addReview(review));
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable String bookId) {
        return ResponseEntity.ok(reviewService.getReviewsByBookId(bookId));
    }
}
