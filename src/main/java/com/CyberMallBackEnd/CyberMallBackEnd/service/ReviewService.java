package com.CyberMallBackEnd.CyberMallBackEnd.service;
import com.CyberMallBackEnd.CyberMallBackEnd.model.Review;

import org.springframework.stereotype.Service;


import java.util.List;
@Service
public interface ReviewService {
    Review createReview(Review review);
    List<Review> getAllReview();
    Review getReviewById(Long id);
    Review updateReview(Long id, Review review);
    String deleteReview(Long id);

}
