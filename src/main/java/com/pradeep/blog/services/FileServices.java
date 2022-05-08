package com.pradeep.blog.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileServices {
	
	void saveFile(MultipartFile multipartFile);
	void getFile(String path ,int postId);

}
