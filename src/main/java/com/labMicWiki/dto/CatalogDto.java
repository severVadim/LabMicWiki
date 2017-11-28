package com.labMicWiki.dto;

import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.labMicWiki.domain.Article;

public class CatalogDto{
	
	private Long catalogId;
	
	private String catalogName;
	
	private TreeSet<ArticleDto> articles;
	
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public TreeSet<ArticleDto> getArticles() {
		return articles;
	}
	public void setArticles(TreeSet<ArticleDto> articles) {
		this.articles = articles;
	}
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articles == null) ? 0 : articles.hashCode());
		result = prime * result
				+ ((catalogId == null) ? 0 : catalogId.hashCode());
		result = prime * result
				+ ((catalogName == null) ? 0 : catalogName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CatalogDto other = (CatalogDto) obj;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		if (catalogId == null) {
			if (other.catalogId != null)
				return false;
		} else if (!catalogId.equals(other.catalogId))
			return false;
		if (catalogName == null) {
			if (other.catalogName != null)
				return false;
		} else if (!catalogName.equals(other.catalogName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CatalogDto [catalogId=" + catalogId + ", catalogName="
				+ catalogName + ", articles=" + articles + "]";
	}

}