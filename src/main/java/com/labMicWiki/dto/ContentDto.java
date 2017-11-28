package com.labMicWiki.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContentDto {
	
	private Long   id;
	private String articleName;
	private Long catalogId;
	private String catalogName;
	private String content;
	private String datetime;
	
	public String getContent() {
		return content;
	}
	public void setDatetime(Date datetime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.US);
		this.datetime = dateFormat.format(datetime);;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDatetime() {
		return datetime;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
				+ ((articleName == null) ? 0 : articleName.hashCode());
		result = prime * result
				+ ((catalogId == null) ? 0 : catalogId.hashCode());
		result = prime * result
				+ ((catalogName == null) ? 0 : catalogName.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ContentDto other = (ContentDto) obj;
		if (articleName == null) {
			if (other.articleName != null)
				return false;
		} else if (!articleName.equals(other.articleName))
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
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ContentDto [id=" + id + ", articleName=" + articleName
				+ ", catalogId=" + catalogId + ", catalogName=" + catalogName
				+ ", content=" + content + ", datetime=" + datetime + "]";
	}

}