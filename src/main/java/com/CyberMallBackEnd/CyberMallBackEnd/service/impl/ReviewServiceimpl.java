package com.CyberMallBackEnd.CyberMallBackEnd.service.impl;

import com.CyberMallBackEnd.CyberMallBackEnd.exception.ReviewNotFoundException;
import com.CyberMallBackEnd.CyberMallBackEnd.Entity.Review;
import com.CyberMallBackEnd.CyberMallBackEnd.repositry.Reviewrepositry;
import com.CyberMallBackEnd.CyberMallBackEnd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceimpl implements ReviewService {
    @Autowired
    private Reviewrepositry reviewrepositry;

    @Override
    public Review createReview(Review review) {
        return reviewrepositry.save(review);
    }

    @Override
    public List<Review> getAllReview() {
        return reviewrepositry.findAll();
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewrepositry.findById(id).orElseThrow(()->new ReviewNotFoundException(id));
    }

    @Override
    public Review updateReview(Long id, Review review) {
        Review existReview = reviewrepositry.findById(id).orElseThrow(()->new ReviewNotFoundException(id));

        if (existReview != null){
            existReview.setRating(review.getRating());
            existReview.setComment(review.getComment());
            reviewrepositry.save(existReview);
            return existReview;
        }
        return null;
    }

    @Override
    public String deleteReview(Long id) {
        if (!reviewrepositry.existsById(id)){
            throw new ReviewNotFoundException(id);
        } else {
            reviewrepositry.deleteById(id);
            return "Review with id "+id+" has been deleted success";
        }
    }
}
