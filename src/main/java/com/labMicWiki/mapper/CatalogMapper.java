package com.labMicWiki.mapper;


import java.util.Iterator;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.Catalog;
import com.labMicWiki.dto.ArticleDto;
import com.labMicWiki.dto.CatalogDto;
import com.labMicWiki.util.ArticleDtoComporator;




@Component("catalogMapper")
public class CatalogMapper{

    @Autowired
    private ArticleMapper articleMapper;
    
	public CatalogDto toCatalogsDto (Catalog catalog){
		CatalogDto catalogsDto = new CatalogDto();
		ArticleDtoComporator comporatorForArticle = new ArticleDtoComporator();
		catalogsDto.setCatalogName(catalog.getCatalogName());
		catalogsDto.setCatalogId(catalog.getCatalogId());
		
		TreeSet <ArticleDto> articles = new TreeSet<ArticleDto>(comporatorForArticle);


		for (Iterator<Article> iter = catalog.getArticlesForCatalog().iterator();iter.hasNext();) {
			articles.add(articleMapper.toArticleDto(iter.next()));
		}

		catalogsDto.setArticles(articles);
		return catalogsDto;
}
}