package com.labMicWiki.dao;

import java.util.List;

import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.Content;

public interface ContentDao {
	
	Content getContentById(Long id);
		
	List<Content> search (String searchCriteria);
	
    void createNewContent (Content content);
		
	void changeContent (Content content);
	
	void deleteContent(Content content);
		
}