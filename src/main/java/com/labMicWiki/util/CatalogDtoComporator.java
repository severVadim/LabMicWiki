package com.labMicWiki.util;

import java.util.Comparator;

import com.labMicWiki.dto.CatalogDto;

public class CatalogDtoComporator implements Comparator<CatalogDto> {

	public int compare(CatalogDto o1, CatalogDto o2) {
		return o1.getCatalogName().compareTo(o2.getCatalogName());
	}

}