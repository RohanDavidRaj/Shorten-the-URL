package com.rohan.shorturl.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rohan.shorturl.entity.URLEntity;

public interface URLRepo extends JpaRepository<URLEntity, Integer> {

	Optional<URLEntity> findByOriginalUrl(String originalUrl);
	Optional<URLEntity> findByShortenedUrl(String shortenedUrl);
}
