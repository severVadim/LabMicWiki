package com.labMicWiki.dao;


import java.util.List;

import com.labMicWiki.domain.ContentOld;


public interface ContentOldDao {

	List <ContentOld> getOldContent (Long id);
	
	void createOldContent (ContentOld contentOld);
	
	void deleteContentOld (ContentOld contentOld);

}
