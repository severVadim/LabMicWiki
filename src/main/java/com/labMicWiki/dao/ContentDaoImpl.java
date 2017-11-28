package com.labMicWiki.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.labMicWiki.domain.Article;
import com.labMicWiki.domain.Content;

@Repository("contentDao")
@Transactional
public class ContentDaoImpl implements ContentDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Content getContentById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Content)session.createQuery("from Content where article_Id =:articleId").setParameter("articleId", id).uniqueResult();
	}

	public List<Content> search(String searchCriteria) {
		Session session = sessionFactory.getCurrentSession();	
		return session.createQuery("from Content where upper(content) like upper(:searchCriteria)").setParameter("searchCriteria", "%" + searchCriteria + "%").list();
		}

	public void createNewContent(Content content) {
		Session session = sessionFactory.getCurrentSession();
		session.save(content);
	}
		
	public void changeContent(Content content) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery ("update Content set content =:content, datetime =:datetime where article_Id =:articleId");
		query.setParameter("datetime", content.getDatetime());
		query.setParameter("articleId", content.getArticle().getArticleId());
		query.setParameter("content", content.getContent());
		query.executeUpdate();
		
	}

	public void deleteContent(Content content) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(content);
	}
	
}