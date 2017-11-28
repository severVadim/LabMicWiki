package com.labMicWiki.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("imageService")
public class ImageServiceImpl implements ImageService{

private static final String PATH_TO_IMAGE_REPOSITORY = "D:\\Wiki\\Resources\\Images\\";

	public byte[] uploadImage(String imageName) {
		File file = new File(PATH_TO_IMAGE_REPOSITORY + imageName);
		byte[] image = new byte[1024];
		try {
			image = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public String downloadImage(MultipartFile file) {
    	String fileName = null;
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        try {	
        		StringBuilder str = new StringBuilder();
          	  	str.append(ft.format(dNow));
          	  	str.append(".");
          	  	str.append(file.getOriginalFilename().split("\\.")[1]);
                fileName = str.toString();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = 
                        new BufferedOutputStream(new FileOutputStream(new File(PATH_TO_IMAGE_REPOSITORY + fileName)));
                buffStream.write(bytes);
                buffStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }  	
	    return fileName;
	}
	
}