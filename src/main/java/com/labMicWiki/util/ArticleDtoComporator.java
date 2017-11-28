package com.labMicWiki.util;

import java.util.Comparator;

import com.labMicWiki.dto.ArticleDto;



public class ArticleDtoComporator implements Comparator<ArticleDto> {

	public int compare(ArticleDto o1, ArticleDto o2) {
		return o1.getArticleName().compareTo(o2.getArticleName());
	}	
}