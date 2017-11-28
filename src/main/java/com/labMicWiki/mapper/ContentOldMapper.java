package com.labMicWiki.mapper;


import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.ContentOld;
import com.labMicWiki.dto.ContentDto;
import com.labMicWiki.dto.ContentOldDto;
import static com.labMicWiki.util.ContentEncode.contentEncode;

@Component("contentOldMapper")
public class ContentOldMapper{
	
	public ContentOldDto toContentOldDto (ContentOld contentOld){
		ContentOldDto contentOldDto = new ContentOldDto();
		contentOldDto.setDatetime(contentOld.getDatetime());
		contentOldDto.setContent(contentEncode(contentOld.getContent()));	
		return contentOldDto;
	}
	
	public ContentOld toContentOld (ContentDto contentDto) throws ParseException{
		ContentOld contentOld = new ContentOld();
		Article article = new Article ();
		article.setArticleId(contentDto.getId());
		contentOld.setArticle(article);
		contentOld.setContent(contentDto.getContent());
		contentOld.setDatetime(contentDto.getDatetime());
		return contentOld;
		
	}
}