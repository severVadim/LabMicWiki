package com.labMicWiki.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService{
	
	byte [] uploadImage (String imageName);
	
	String downloadImage (MultipartFile file);
	
}