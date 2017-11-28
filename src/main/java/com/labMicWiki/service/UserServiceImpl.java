package com.labMicWiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labMicWiki.dao.UserDao;
import com.labMicWiki.domain.User;


@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
   
	public boolean checkAccess(User user) {
		return user.equals(userDao.getAdmin());
	}
	
}