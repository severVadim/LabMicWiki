package com.labMicWiki.service;

import java.text.ParseException;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labMicWiki.dao.ContentOldDao;
import com.labMicWiki.domain.ContentOld;
import com.labMicWiki.dto.ContentDto;
import com.labMicWiki.dto.ContentOldDto;
import com.labMicWiki.mapper.ContentOldMapper;
import com.labMicWiki.util.ContentOldDtoComporator;

@Service("contentOldService")
public class ContentOldServiceImpl implements ContentOldService{

	@Autowired
	private ContentOldDao contentOldDao;
	   
    @Autowired
    private ContentOldMapper contentOldMapper;
    

	public TreeSet<ContentOldDto> getOldContent(Long id) {
		ContentOldDtoComporator contentOldDtoComporator = new ContentOldDtoComporator();
		TreeSet<ContentOldDto> contentsOldDto = new TreeSet<ContentOldDto> (contentOldDtoComporator);
		for (ContentOld contentOld : contentOldDao.getOldContent(id)){
			contentsOldDto.add(contentOldMapper.toContentOldDto(contentOld));		
		}		
		return contentsOldDto;
	}


	public void createOldContent(ContentDto contentDto) throws ParseException {
		contentOldDao.createOldContent(contentOldMapper.toContentOld(contentDto));			
	}
	
}