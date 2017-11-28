package com.labMicWiki.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contentOld")
public class ContentOld implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "article_id")
	private Article article;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "datetime")
	private Date datetime;
	
	public ContentOld(Long id, Article article, String content, Date datetime) {
		this.id = id;
		this.article = article;
		this.content = content;
		this.datetime = datetime;
	}

	public ContentOld() {
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDatetime() {
	    return datetime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatetime(String datetime) throws ParseException{
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.US);
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:SSS");
		Date date1 = formatter1.parse(datetime);
		String sDate2 = formatter2.format(date1);
		Date dDate2 = formatter2.parse(sDate2);
		this.datetime = dDate2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((article == null) ? 0 : article.hashCode());
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result
				+ ((content == null) ? 0 : content.hashCode());
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
		ContentOld other = (ContentOld) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}		
}