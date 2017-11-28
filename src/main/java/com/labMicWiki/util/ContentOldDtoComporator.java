package com.labMicWiki.util;

import java.util.Comparator;


import com.labMicWiki.dto.ContentOldDto;

public class ContentOldDtoComporator implements Comparator<ContentOldDto> {


	public int compare(ContentOldDto o1, ContentOldDto o2) {
		return o1.getDatetime().compareTo(o2.getDatetime());
	}

}