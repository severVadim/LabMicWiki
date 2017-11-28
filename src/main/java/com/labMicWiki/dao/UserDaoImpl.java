package com.labMicWiki.dao;
import java.io.File;

import com.labMicWiki.domain.User;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

private static final String PATH_TO_USER_XML = "D:\\Wiki\\Resources\\DB\\user.xml";

   public User getAdmin() {
	   User admin = null;
      try {    	 
    	 ClassLoader classLoader = getClass().getClassLoader();
    	  
         DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = f.newDocumentBuilder();
         Document doc = builder.parse(new File(PATH_TO_USER_XML));
         admin = new User (doc.getElementsByTagName("login").item(0).getTextContent(), doc.getElementsByTagName("password").item(0).getTextContent());         
      } catch (Exception ex) {ex.printStackTrace();}
      return admin;
   }
}