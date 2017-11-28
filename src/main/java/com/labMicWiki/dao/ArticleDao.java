package com.labMicWiki.dao;

import com.labMicWiki.domain.Article;

public interface ArticleDao {
	
	Article getArticleById(Long id);
	
	void changeArticleName (Article article);
	
	void changeCatalog (Article article);
	
	void addArticle (Article article);
	
	void deleteArticle (Article article);
}
