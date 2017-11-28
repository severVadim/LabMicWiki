package com.labMicWiki.service;

import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labMicWiki.dao.ArticleDao;
import com.labMicWiki.dao.CatalogDao;
import com.labMicWiki.dao.ContentDao;
import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.Catalog;
import com.labMicWiki.domain.Content;
import com.labMicWiki.dto.ContentDto;
import com.labMicWiki.mapper.ContentMapper;



@Service("contentService")
public class ContentServiceImpl implements ContentService{

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CatalogDao catalogDao;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private ContentDao contentDao;
	
    @Autowired
    private ContentMapper contentMapper;
            
    @Autowired
    private ContentOldService contentOldService;
    
	public ContentDto getContentById(Long id) {
		return contentMapper.toContentDto(contentDao.getContentById(id));
	}

	public ArrayList<ContentDto> search(String searchCriteria) {
		ArrayList <ContentDto> contentsDto = new ArrayList <ContentDto>();
		for (Content content : contentDao.search(searchCriteria)){
			contentsDto.add(contentMapper.toContentDto(content));
		}
		return contentsDto;
	}

	public void createNewContent(ContentDto contentDto) {
		Content content = new Content ();
		Article article = new Article ();
		Catalog catalog = new Catalog ();
		catalog.setCatalogName(contentDto.getCatalogName());
		if (contentDto.getCatalogId() != 0) catalog.setCatalogId(contentDto.getCatalogId());
		article.setArticleName(contentDto.getArticleName());
		article.setCatalog(catalog);
		content.setArticle(article);
		content.setContent(contentDto.getContent());
		content.setDatetime();
		articleDao.addArticle (article);
		contentDao.createNewContent(content);
		
	}

	public void changeContent(ContentDto contentDto) throws ParseException {
		Content content = new Content ();
		Content contentOld = contentDao.getContentById(contentDto.getId());
		
		Article article = new Article ();
		Catalog catalog = new Catalog();
		
		article.setArticleId(contentDto.getId());
		article.setArticleName(contentDto.getArticleName());
		content.setContent(contentDto.getContent());
		content.setArticle(article);
		content.setDatetime();
		
		
		catalog.setCatalogName(contentDto.getCatalogName());
		

		if (!contentOld.getArticle().getArticleName().equals(article.getArticleName()))  articleDao.changeArticleName(article);
		if (!contentOld.getArticle().getCatalog().getCatalogName().equals(catalog.getCatalogName())){
			
			if (contentDto.getCatalogId() == 0)catalog.setCatalogId(catalogService.createNewCatalog(contentDto.getCatalogName()));
			else catalog.setCatalogId(contentDto.getCatalogId());			
			article.setCatalog(catalog);
			articleDao.changeCatalog(article);
		}
		if (!contentOld.getContent().equals(contentDto.getContent())){
		contentDto.setContent(contentOld.getContent());
		contentOldService.createOldContent(contentDto);
		contentDao.changeContent(content);				
		}
	}

	public void newContent(ContentDto contentDto) throws ParseException {
		contentDto.setContent(contentDto.getContent());
		if (contentDto.getId() == 0) createNewContent(contentDto);
		else changeContent(contentDto);
		
	}

}