package com.labMicWiki.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "catalog")
public class Catalog  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "catalog_id")
	private Long catalogId;

	@Column(name = "catalog_name")
	private String catalogName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "catalog",  cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Article> articles = new HashSet<Article>();
	
    public Set<Article> getArticlesForCatalog (){
		return this.articles;
    }
    
	public Catalog(Long catalogId, String catalogName) {
		this.catalogId = catalogId;
		this.catalogName = catalogName;

	}
	public Catalog() {}
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Catalog other = (Catalog) obj;
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
			
}