package com.rohan.shorturl.exception;

public class ShortenURLisNotInDB extends RuntimeException{

	public ShortenURLisNotInDB(String str){
		super(str);
	}
	
}
