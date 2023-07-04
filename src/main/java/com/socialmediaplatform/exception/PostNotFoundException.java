package com.socialmediaplatform.exception;

public class PostNotFoundException extends Exception{
	public PostNotFoundException(Long postId) {
        super("Post not found with ID: " + postId);
    }
}
