package com.labMicWiki.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.labMicWiki.service.ImageService;

@Controller
public class ImageController{
	   
	@Autowired
	ImageService imageService;
	       
    @RequestMapping(value = "/lifs", method = RequestMethod.GET)
    public void showImage(@RequestParam String i, HttpServletResponse response) throws IOException{
        
        OutputStream out = response.getOutputStream();
        out.write(imageService.uploadImage(i));
        out.flush();
        out.close();      
}
    @RequestMapping(value="/uploadImage", method=RequestMethod.POST )
    public @ResponseBody String uploadFile(@RequestParam("image") MultipartFile file){
    	
    	return imageService.downloadImage(file);

    }   
}
