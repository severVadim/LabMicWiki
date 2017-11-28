package com.labMicWiki.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContentOldDto {
	
	private String datetime;
	private String content;
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.US);
		this.datetime = dateFormat.format(datetime);;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
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
		ContentOldDto other = (ContentOldDto) obj;
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
		return true;
	}
	
	
}