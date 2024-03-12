package com.rohan.shorturl.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import com.rohan.shorturl.dto.ResponseDTO;
import com.rohan.shorturl.dto.ResponseDTO;
import com.rohan.shorturl.dto.URLRegisterDto;

public interface ShortURLService {

	public URLRegisterDto register(URLRegisterDto dto);
	
	 public static String shortenURL(String longURL) {
	        try {
	            // Generate SHA-256 hash
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hash = digest.digest(longURL.getBytes());

	            // Encode hash using Base64
	            String encodedHash = Base64.getUrlEncoder().encodeToString(hash);
	            

	            // Use first 8 characters of the encoded hash as short URL
	            return encodedHash.substring(0, 8);
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	public URLRegisterDto getOriginalURL(URLRegisterDto dto);

}
