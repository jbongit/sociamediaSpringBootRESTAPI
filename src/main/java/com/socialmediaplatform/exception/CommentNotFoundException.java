package com.socialmediaplatform.exception;

public class CommentNotFoundException extends Exception {
	public CommentNotFoundException(Long commentId) {
        super("Comment not found with ID: " + commentId);
    }
}
