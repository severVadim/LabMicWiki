package com.labMicWiki.dao;

import com.labMicWiki.domain.Article;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("articleDao")
@Transactional
public class ArticleDaoImpl implements ArticleDao {
	   
	@Autowired
	private SessionFactory sessionFactory;

	public Article getArticleById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Article)session.createQuery("from Article where article_Id  =:articleId").setParameter("articleId", id).uniqueResult();
	}		

	public void changeArticleName (Article article){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery ("update Article set article_Name =:articleName where article_Id =:articleId");
		query.setParameter("articleId", article.getArticleId());
		query.setParameter("articleName", article.getArticleName());
		query.executeUpdate();
	}

	public void changeCatalog(Article article) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery ("update Article set catalog_id =:catalogId where article_Id =:articleId");
		query.setParameter("catalogId", article.getCatalog().getCatalogId());
		query.setParameter("articleId", article.getArticleId());
		query.executeUpdate();		
	}

	public void addArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		session.save(article);	
	}


	public void deleteArticle(Article article) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(article);
		
	}
}
