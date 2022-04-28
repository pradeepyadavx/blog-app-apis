package com.pradeep.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> posts;
	private int pageNumber;
	private int pagesize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;

}