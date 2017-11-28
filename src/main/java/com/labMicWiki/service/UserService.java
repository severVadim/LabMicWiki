package com.labMicWiki.service;



import com.labMicWiki.domain.User;

public interface UserService{
		
	boolean checkAccess(User user);	
	
}