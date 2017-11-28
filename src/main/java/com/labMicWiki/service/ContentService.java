package com.labMicWiki.service;

import java.text.ParseException;
import java.util.ArrayList;

import com.labMicWiki.dto.ContentDto;

public interface ContentService{
	
	ContentDto getContentById(Long id);
		
	ArrayList<ContentDto> search (String searchCriteria);
	
    void createNewContent (ContentDto contentDto);
	
	void changeContent (ContentDto contentDto) throws ParseException;
	
	void newContent (ContentDto contentDto) throws ParseException;
}