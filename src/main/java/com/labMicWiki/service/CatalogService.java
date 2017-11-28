package com.labMicWiki.service;

import java.util.TreeSet;

import com.labMicWiki.domain.Catalog;
import com.labMicWiki.dto.CatalogDto;


public interface CatalogService{
	
	Long createNewCatalog (String name);
	
	TreeSet<CatalogDto> getAllCatalogs();
		
}