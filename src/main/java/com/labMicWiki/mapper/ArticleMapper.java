package com.labMicWiki.mapper;


import org.springframework.stereotype.Component;
import com.labMicWiki.domain.Article;
import com.labMicWiki.dto.ArticleDto;



@Component("articleMapper")
public class ArticleMapper{
	
	public ArticleDto toArticleDto (Article article){
		ArticleDto articleDto = new ArticleDto();
		articleDto.setArticleId(article.getArticleId());
		articleDto.setArticleName(article.getArticleName());		
		return articleDto;
}
}