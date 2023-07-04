package com.socialmediaplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmediaplatform.model.Comment;


public interface CommentsRepository extends JpaRepository<Comment, Long> {
    
}
