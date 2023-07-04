package com.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.model.UserSearch;
import com.socialmediaplatform.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@PostMapping
	public ResponseEntity<List<UserSearch>> searchHandler(@RequestParam("searchTerm") String searchTerm) {
		List<UserSearch> userList=searchService.searchUserByUserNameAndFirstNameAndLastName(searchTerm);
		return ResponseEntity.ok().body(userList);
	}
	
	@PostMapping("/post")
	public ResponseEntity<List<Posts>> searchPostHandler(@RequestParam("searchTerm") String searchTerm) {
		List<Posts> postList=searchService.getAllPostsByLocation(searchTerm);
		return ResponseEntity.ok().body(postList);
	}
}
