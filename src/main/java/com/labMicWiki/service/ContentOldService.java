package com.labMicWiki.service;


import java.text.ParseException;
import java.util.TreeSet;

import com.labMicWiki.dto.ContentDto;
import com.labMicWiki.dto.ContentOldDto;


public interface ContentOldService{
	
	TreeSet <ContentOldDto> getOldContent (Long id);
	
	void createOldContent (ContentDto contentDto) throws ParseException;
}
