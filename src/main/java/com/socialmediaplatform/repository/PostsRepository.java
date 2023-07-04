package com.socialmediaplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmediaplatform.model.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

	List<Posts> findAllByLocationContaining(String location);

	List<Posts> findByUserName(String userName);
    
}
