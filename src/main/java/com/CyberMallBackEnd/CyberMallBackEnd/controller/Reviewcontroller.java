package com.CyberMallBackEnd.CyberMallBackEnd.controller;



import com.CyberMallBackEnd.CyberMallBackEnd.entity.Review;
import com.CyberMallBackEnd.CyberMallBackEnd.exception.ReviewNotFoundException;
import com.CyberMallBackEnd.CyberMallBackEnd.repositry.Reviewrepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Reviewcontroller {
    @Autowired

    private Reviewrepositry reviewrepositry;

    @PostMapping("/review")
    Review newReview(@RequestBody Review newReview){
        return reviewrepositry.save(newReview);
    }

    @GetMapping("/reviews")
    List<Review> getAllReviews(){
        return reviewrepositry.findAll();
    }

    @GetMapping("/review/{id}")
    Review getReviewById(@PathVariable Long id){
        return reviewrepositry.findById(id)
                .orElseThrow(()->new ReviewNotFoundException(id));
    }

    @PutMapping("/review/{id}")
    Review updateReview(@RequestBody Review newReview ,@PathVariable Long id){
        return reviewrepositry.findById(id)
                .map(review -> {
                    review.setComment(newReview.getComment());
                    review.setRating(newReview.getRating());
                    return reviewrepositry.save(review);
                }).orElseThrow(()->new ReviewNotFoundException(id));
    }

    @DeleteMapping("/review/{id}")
    String deleteReview(@PathVariable Long id){
        if (!reviewrepositry.existsById(id)){
            throw new ReviewNotFoundException(id);
        }
        reviewrepositry.deleteById(id);
        return "Review with id "+id+" has been deleted success";
    }

}



