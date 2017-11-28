package com.labMicWiki.util;

public final class ContentEncode {
	private ContentEncode (){}
	public static String contentEncode (String contentToEncode){		
		return contentToEncode.replaceAll("\\s*[\\r\\n]+\\s*", "").trim().replaceAll("'", "\"");		
	}
}