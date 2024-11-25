package com.CyberMallBackEnd.CyberMallBackEnd.exception;

public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(Long id){
        super("could not found the review with id" + id);
    }
}
