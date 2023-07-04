package com.socialmediaplatform.exception;

public class UserAccessDeniedException extends Exception{
	public UserAccessDeniedException(String userName) {
		super("User :: Access Denied :: "+userName);
	}
}
