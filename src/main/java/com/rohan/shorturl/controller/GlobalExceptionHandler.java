package com.rohan.shorturl.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rohan.shorturl.dto.ResponseDTO;
import com.rohan.shorturl.exception.ShortenURLisNotInDB;
import com.rohan.shorturl.exception.URLException;
import com.rohan.shorturl.exception.URLisPresent;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(URLisPresent.class)
    public ResponseEntity<ResponseDTO> handleURLisPresentException(URLisPresent ex) {
        ResponseDTO response = new ResponseDTO(true, "URL is already present in the system", "");
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(URLException.class)
    public ResponseEntity<ResponseDTO> handleURLException(URLException ex){
    	return new ResponseEntity<ResponseDTO>(
    			new ResponseDTO(true, ex.getMessage(),"")
    			, HttpStatus.BAD_GATEWAY);
    }
    
    @ExceptionHandler(ShortenURLisNotInDB.class)
    public ResponseEntity<ResponseDTO> name(ShortenURLisNotInDB ex) {
	    return new ResponseEntity<ResponseDTO>(new ResponseDTO(true,ex.getMessage(),""),HttpStatus.NOT_FOUND);
	    
	}
}
