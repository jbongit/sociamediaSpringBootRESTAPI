package com.socialmediaplatform.exception;

public class UserNotFoundException extends Exception{
	public UserNotFoundException(String username) {
        super("User not found with ID: " + username);
    }
}
