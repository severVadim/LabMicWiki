package com.labMicWiki.dao;

import java.util.List;

import com.labMicWiki.domain.Catalog;

public interface CatalogDao {
	
	List <Catalog> getAllCatalogs();
	
	void changeCatalogName (Catalog catalog);
	
	Catalog getCatalogById(Long id);
	
	Catalog getCatalogByName(String name);
	
	void createCatalog (Catalog catalog);
	
	void deleteCatalog (Catalog catalog);	
}
