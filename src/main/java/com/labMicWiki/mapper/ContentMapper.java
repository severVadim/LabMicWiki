package com.labMicWiki.mapper;


import org.springframework.stereotype.Component;

import com.labMicWiki.domain.Content;
import com.labMicWiki.dto.ContentDto;
import static com.labMicWiki.util.ContentEncode.contentEncode;


@Component("contentMapper")
public class ContentMapper{
		
	public ContentDto toContentDto (Content content){
		ContentDto contnetDto = new ContentDto();
		contnetDto.setId(content.getArticle().getArticleId());
		contnetDto.setArticleName(content.getArticle().getArticleName());
		contnetDto.setContent(contentEncode(content.getContent()));
		contnetDto.setDatetime(content.getDatetime());
		contnetDto.setCatalogId(content.getArticle().getCatalog().getCatalogId());
		contnetDto.setCatalogName(content.getArticle().getCatalog().getCatalogName());
		return contnetDto;
	}	

}