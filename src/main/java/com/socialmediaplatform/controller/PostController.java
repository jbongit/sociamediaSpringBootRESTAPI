package com.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaplatform.exception.CommentNotFoundException;
import com.socialmediaplatform.exception.PostNotFoundException;
import com.socialmediaplatform.exception.UserAccessDeniedException;
import com.socialmediaplatform.model.Comment;
import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.service.CommentService;
import com.socialmediaplatform.service.PostsService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
    private PostsService postService;
	
	@Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Posts>> getPost(){
    	List<Posts> list=postService.getAllPosts();
    	return ResponseEntity.ok().body(list);
    }
    
    @PostMapping("/{userName}")
    public ResponseEntity<Posts> createPost(@RequestBody Posts post,@PathVariable("userName") String userName) {
        post.setUserName(userName);
    	Posts createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
    
    @GetMapping("/{postId}")
    public ResponseEntity<Posts> getPostById(@PathVariable("postId") Long postId) throws PostNotFoundException{
    	Posts post=postService.getPostById(postId);
    	return ResponseEntity.ok().body(post);
    }
    
    @PutMapping("/{postId}")
    public ResponseEntity<Posts> updatePost(@PathVariable Long postId, @RequestBody Posts post) throws PostNotFoundException {
        Posts updatedPost = postService.updatePost(postId, post);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) throws PostNotFoundException {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment) throws PostNotFoundException {
        Comment createdComment = commentService.addCommentToPost(postId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PutMapping("/{userName}/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable String userName,@PathVariable Long postId, @PathVariable Long commentId, @RequestBody Comment comment) throws PostNotFoundException, CommentNotFoundException, UserAccessDeniedException {
        Comment updatedComment = commentService.updateComment(userName,postId, commentId, comment);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{userName}/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String userName, @PathVariable Long postId, @PathVariable Long commentId) throws PostNotFoundException, CommentNotFoundException, UserAccessDeniedException {
        commentService.deleteComment(userName,postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
