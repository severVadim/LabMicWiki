package com.labMicWiki.service;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labMicWiki.dao.ArticleDao;
import com.labMicWiki.dao.CatalogDao;
import com.labMicWiki.dao.ContentDao;
import com.labMicWiki.dao.ContentOldDao;
import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.Catalog;
import com.labMicWiki.domain.Content;
import com.labMicWiki.domain.ContentOld;

@Service("globalService")
public class GlobalServiceImpl implements GlobalService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private CatalogDao catalogDao;
	
	@Autowired
	private ContentDao contentDao;
	
	@Autowired
	private ContentOldDao contentOldDao;
	

	public void toSave(ArrayList<String> toSave) {
		Catalog catalog;
		Article article;
		Content content;
		ContentOld contentOld;
		if (toSave.get(0).equals("AddNewCatalog")){
			catalog = new Catalog();
			catalog.setCatalogName(toSave.get(1));
			catalogDao.createCatalog(catalog);
		}else if (toSave.get(0).equals("DeleteCatalog")){
			catalog = new Catalog();
			catalog.setCatalogId(Long.parseLong(toSave.get(1)));
			catalogDao.deleteCatalog(catalog);
		}else if (toSave.get(0).equals("RenameCatalog")){
			catalog = new Catalog();
			catalog.setCatalogId(Long.parseLong(toSave.get(1)));
			catalog.setCatalogName(toSave.get(2));
			catalogDao.changeCatalogName(catalog);
		}else if (toSave.get(0).equals("DeleteArticle")){
			article = new Article();
			content = new Content();
			contentOld = new ContentOld();			
			article.setArticleId(Long.parseLong(toSave.get(2)));
			content.setArticle(article);
			contentOld.setArticle(article);
			contentDao.deleteContent(content);
			contentOldDao.deleteContentOld(contentOld);
			articleDao.deleteArticle(article);
		}else if (toSave.get(0).equals("RenameArticle")){
			article = new Article();
			article.setArticleId(Long.parseLong(toSave.get(2)));
			article.setArticleName(toSave.get(3));
			articleDao.changeArticleName(article);
		}else if (toSave.get(0).equals("MoveArticle")){
			article = new Article();
			catalog = new Catalog();
			article.setArticleId(Long.parseLong(toSave.get(2)));
			catalog.setCatalogId(Long.parseLong(toSave.get(3)));
			article.setCatalog(catalog);
			articleDao.changeCatalog(article);	
		}		
}
}