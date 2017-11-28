package com.labMicWiki.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.labMicWiki.domain.User;
import com.labMicWiki.dto.ContentDto;
import com.labMicWiki.service.CatalogService;
import com.labMicWiki.service.ContentOldService;
import com.labMicWiki.service.ContentService;
import com.labMicWiki.service.GlobalService;


@Controller
public class MainController{
   private static final String DEFAULT_USERNAME = "Anonymous";
   private static final String USERNAME_COOKIE_NAME = "nickName";

   @Autowired
   private ContentService contentService;
   
   @Autowired
   private ContentOldService contentOldService;
   
   @Autowired
   private CatalogService catalogService;
   
   @Autowired
   private GlobalService globalService;

   @RequestMapping(value = "/")
   public @ResponseBody ModelAndView labMicWiki(HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException {	   
      return preparationHomePage (request);
   }   
   
   @RequestMapping(value = "/{id}")
   public @ResponseBody ModelAndView labMicWikiId(@PathVariable String id, HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
	   ModelAndView mav = preparationHomePage (request);
	   ContentDto contentDto = null;
	   try {
		   contentDto = contentService.getContentById(Long.parseLong(id));
	   }catch (NumberFormatException e){
		   mav = new ModelAndView();
		   mav.setViewName("redirect:/");
	   }
	   if (contentDto != null) mav.addObject("content", contentDto);
	   else {
		   mav = new ModelAndView();
		   mav.setViewName("redirect:/");
	   }	   
	   return mav;
   }

   
   @RequestMapping(value = "/search")
   public @ResponseBody String search(@RequestParam String searchPattern) throws JsonGenerationException, JsonMappingException, IOException {
      return new ObjectMapper().writeValueAsString(contentService.search(java.net.URLDecoder.decode(searchPattern, "UTF-8")));
   }
     
   
   @RequestMapping(value = "/save", method = RequestMethod.POST)
   public @ResponseBody String save(@RequestBody String contentDtoToSave) throws ParseException, JsonSyntaxException, UnsupportedEncodingException {
	   ContentDto contentDto = new Gson().fromJson(java.net.URLDecoder.decode(contentDtoToSave, "UTF-8"), ContentDto.class);
	   contentService.newContent ((ContentDto)contentDto); 
	    return "/";	  
   }
   public ModelAndView preparationHomePage (HttpServletRequest request) throws JsonGenerationException, JsonMappingException, IOException{
	      Cookie[] cookies = request.getCookies();
	      User user = new User (DEFAULT_USERNAME, null);
	      if (cookies != null) {
	         for (Cookie cookie : cookies) {
	            if (USERNAME_COOKIE_NAME.equals(cookie.getName())) {
	               user.setNickName(cookie.getValue());
	               break;
	            }
	         }
	      }
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("home");
	      mav.addObject("user", user);   
	      mav.addObject("catalogs", getJsonCatalogs ());
	  return mav;
   }
   
   @RequestMapping(value = "/history")
   public @ResponseBody String history(@RequestParam String id)throws JsonGenerationException, JsonMappingException, IOException{	   
	   return new ObjectMapper().writeValueAsString(contentOldService.getOldContent(Long.parseLong(id)));
   }
   @RequestMapping(value = "/GlobalEdit", method = RequestMethod.GET)
   public ModelAndView logIn(HttpServletRequest request){
	  ModelAndView model = new ModelAndView();
	  model.setViewName("globalEdit");
	  return model;
   }

   @RequestMapping(value = "/getFreshCatalogs")
   public @ResponseBody String getFreshCatalogs()throws JsonGenerationException, JsonMappingException, IOException{	   
	   return getJsonCatalogs ();
   }
   public String getJsonCatalogs () throws JsonGenerationException, JsonMappingException, IOException{
	   return new ObjectMapper().writeValueAsString(catalogService.getAllCatalogs());
   }
   @RequestMapping(value = "/saveGlobal", method = RequestMethod.POST)
   public @ResponseBody String saveGlobal(@RequestBody String toSave) throws JsonSyntaxException, UnsupportedEncodingException{

	    ArrayList<String> yourList = new Gson().fromJson(java.net.URLDecoder.decode(toSave, "UTF-8"), new TypeToken<ArrayList<String>>() {}.getType());
	    globalService.toSave(yourList);	    
	    return "/GlobalEdit";	  
   }
}