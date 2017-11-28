package com.labMicWiki.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.labMicWiki.domain.Catalog;

@Repository("catalogDao")
@Transactional
public class CatalogDaoImpl implements CatalogDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void changeCatalogName(Catalog catalog) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery ("update Catalog set catalog_Name =:catalogName where catalog_Id =:catalogId");
		query.setParameter("catalogName", catalog.getCatalogName());
		query.setParameter("catalogId", catalog.getCatalogId());
		query.executeUpdate();		
	}

	public Catalog getCatalogById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Catalog)session.createQuery("from Catalog where catalog_Id  =:catalogId").setParameter("catalogId", id).uniqueResult();	
	}

	public Catalog getCatalogByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		return (Catalog)session.createQuery("from Catalog where catalog_Name  =:catalogName").setParameter("catalogName", name).uniqueResult();	
	}

	public List<Catalog> getAllCatalogs() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Catalog").list();
		}

	public void createCatalog(Catalog catalog) {
		Session session = sessionFactory.getCurrentSession();
		session.save(catalog);
	}

	public void deleteCatalog(Catalog catalog) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery ("delete Catalog where catalog_Id =:catalogId").setParameter ("catalogId", catalog.getCatalogId());
		query.executeUpdate();	
	}

}