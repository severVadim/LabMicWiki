package com.labMicWiki.service;


import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labMicWiki.dao.CatalogDao;
import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.Catalog;
import com.labMicWiki.dto.ArticleDto;
import com.labMicWiki.dto.CatalogDto;
import com.labMicWiki.mapper.CatalogMapper;
import com.labMicWiki.util.CatalogDtoComporator;


@Service("catalogService")
public class CatalogServiceImpl implements CatalogService{

	@Autowired
	private CatalogDao catalogDao;
	
    @Autowired
    private CatalogMapper catalogMapper;
	
	public Long createNewCatalog(String name) {
		Catalog cat = new Catalog();
		cat.setCatalogName(name);
		catalogDao.createCatalog (cat);
		return catalogDao.getCatalogByName(name).getCatalogId();
	}
	
    
	public TreeSet<CatalogDto> getAllCatalogs() {
		CatalogDtoComporator catalogComporator = new CatalogDtoComporator ();
		TreeSet <CatalogDto> catalogsDto = new TreeSet<CatalogDto>(catalogComporator);
		
		for (Catalog catalog : catalogDao.getAllCatalogs()){
			catalogsDto.add(catalogMapper.toCatalogsDto(catalog));
		}
		for (CatalogDto cat : catalogsDto){
			System.out.println (cat.getCatalogName());
			System.out.println ("----------");
			for (ArticleDto ar: cat.getArticles()){
				System.out.println (ar.getArticleName());
			}
			System.out.println ("-------------------------------------------------");
		}
		return catalogsDto;
	}


}
