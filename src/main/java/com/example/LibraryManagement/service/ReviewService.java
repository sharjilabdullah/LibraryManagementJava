package com.example.LibraryManagement.service;

import com.example.LibraryManagement.entity.Review;
import com.example.LibraryManagement.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review addReview(Review review){
        review.setDate(new Date());
        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByBookId(String bookId) {
        return reviewRepository.findByBookId(bookId);
    }


}
