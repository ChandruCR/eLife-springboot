package com.goldenducks.elife.exception;


// Simple Custom Exception defined for eLifeApplication
public class ELifeException extends RuntimeException {

	public ELifeException(String message){
        super(message);
    }
}