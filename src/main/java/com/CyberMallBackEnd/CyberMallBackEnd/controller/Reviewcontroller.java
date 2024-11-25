package com.CyberMallBackEnd.CyberMallBackEnd.controller;

import com.CyberMallBackEnd.CyberMallBackEnd.entity.Review;
import com.CyberMallBackEnd.CyberMallBackEnd.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin(origins = "*")
public class Reviewcontroller {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("")
    Review newReview(@RequestBody Review newReview){
        return reviewService.createReview(newReview);
    }

    @GetMapping("")
    List<Review> getAllReviews(){
        return reviewService.getAllReview();
    }

    @GetMapping("/{id}")
    Review getReviewById(@PathVariable Long id){
        return reviewService.getReviewById(id);
    }

    @PutMapping("/{id}")
    Review updateReview(@PathVariable Long id, @RequestBody Review newReview){
        return reviewService.updateReview(id,newReview);
    }

    @DeleteMapping("/{id}")
    String deleteReview(@PathVariable Long id){
        return reviewService.deleteReview(id);
    }

}



