package com.labMicWiki.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.labMicWiki.domain.ContentOld;



@Repository("contentOldDao")
@Transactional
public class ContentOldDaoImpl implements ContentOldDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ContentOld> getOldContent(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from ContentOld where article_Id =:articleId").setParameter("articleId", id).list();		
	}

	public void createOldContent(ContentOld contentOld) {
		Session session = sessionFactory.getCurrentSession();
		session.save(contentOld);
	}

	public void deleteContentOld(ContentOld contentOld) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery ("delete ContentOld where article_Id =:articleId").setParameter ("articleId", contentOld.getArticle().getArticleId());
		query.executeUpdate();	
	}
}