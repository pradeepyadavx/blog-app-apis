package com.pradeep.blog.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.pradeep.blog.services.FileServices;

public class FileUploadImpl implements FileServices {
	
	
	@Value("$spring.folderpath")
	String  folderPath;

	@Override
	public void saveFile(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
		String imageName=multipartFile.getOriginalFilename();
		
		String path=folderPath+"\\"+imageName;
		
		
		

	}

	@Override
	public void getFile(String path, int postId) {
		// TODO Auto-generated method stub

	}

}
