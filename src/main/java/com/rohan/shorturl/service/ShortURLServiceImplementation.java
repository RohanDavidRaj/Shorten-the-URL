package com.rohan.shorturl.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rohan.shorturl.dao.URLRepo;
import com.rohan.shorturl.dto.URLRegisterDto;
import com.rohan.shorturl.entity.URLEntity;
import com.rohan.shorturl.exception.ShortenURLisNotInDB;
import com.rohan.shorturl.exception.URLException;
import com.rohan.shorturl.exception.URLisPresent;

@Service
public class ShortURLServiceImplementation implements ShortURLService {
	
	@Autowired
	URLRepo dao;

	//ModelMapper modelMapper = new ModelMapper();

	@Override
	public URLRegisterDto register(URLRegisterDto dto) {
	    try {
	        if (dao.findByOriginalUrl(dto.getUrl()).isPresent()) {
	            throw new URLisPresent();
	        } else {
	            URLEntity build = URLEntity.builder()
	                    .originalUrl(dto.getUrl())
	                    .shortenedUrl(ShortURLService.shortenURL(dto.getUrl()))
	                    .build();
	            
	            dao.save(build);

	            //Encoding here the URL
	            String encodedUrl = Base64.getUrlEncoder().encodeToString(ShortURLService.shortenURL(dto.getUrl()).getBytes());

	            return new URLRegisterDto(dto.getUrl(), encodedUrl); // Return success response
	        }
	    } catch (URLException e) {
	        // Return error response for URL already present
	       throw new URLException("Something went wrong");
	    }
	}


	@Override
	@Cacheable(value = "originalUrls", key = "#dto.shortenURL")
	public URLRegisterDto getOriginalURL(URLRegisterDto dto) {
	    try {
	    	 byte[] decodedBytes = Base64.getUrlDecoder().decode(dto.getShortenURL());
	         String decodedUrl = new String(decodedBytes);
	         
	        URLEntity urlEntity = dao.findByShortenedUrl(decodedUrl)
	                                   .orElseThrow(() ->
	                                       new ShortenURLisNotInDB("Original URL not found for shortened URL: " + dto.getShortenURL()));
             System.err.println("Hit DB");
             System.err.println(urlEntity.getShortenedUrl()+" encoded-> "+dto.getShortenURL());
	        return new URLRegisterDto(urlEntity.getOriginalUrl(), dto.getShortenURL());
	    } catch (ShortenURLisNotInDB e) {
	    	  e.printStackTrace(); // You can log the exception or handle it in any other appropriate way
	           throw new ShortenURLisNotInDB("Original URL not found for shortened URL: " + dto.getShortenURL()); 
	          }
	}



}
