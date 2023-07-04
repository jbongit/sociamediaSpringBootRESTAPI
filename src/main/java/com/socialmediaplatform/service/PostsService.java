package com.socialmediaplatform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaplatform.exception.PostNotFoundException;
import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.repository.PostsRepository;

@Service
public class PostsService {
	@Autowired
    private PostsRepository postRepository;

    public List<Posts> getAllPosts() {
        return postRepository.findAll();
    }

    public Posts getPostById(Long postId) throws PostNotFoundException {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
    }

    public Posts createPost(Posts post) {
        post.setPostDateTime(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Posts updatePost(Long postId, Posts updatedPost) throws PostNotFoundException {
        Posts existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        existingPost.setDescription(updatedPost.getDescription());
        existingPost.setLocation(updatedPost.getLocation());
        return postRepository.save(existingPost);
    }

    public void deletePost(Long postId) throws PostNotFoundException {
        Posts post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId));
        postRepository.delete(post);
    }
}
