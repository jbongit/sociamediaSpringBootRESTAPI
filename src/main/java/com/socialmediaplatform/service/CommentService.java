package com.socialmediaplatform.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaplatform.exception.CommentNotFoundException;
import com.socialmediaplatform.exception.PostNotFoundException;
import com.socialmediaplatform.exception.UserAccessDeniedException;
import com.socialmediaplatform.model.Comment;
import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.repository.CommentsRepository;
import com.socialmediaplatform.repository.PostsRepository;

@Service
public class CommentService {

   @Autowired
   private CommentsRepository commentsRepository;
   
   @Autowired
   private PostsRepository postsRepository;

    public Comment addCommentToPost(Long postId, Comment comment) throws PostNotFoundException {
        comment.setPostId(postId);
        comment.setCommentDateTime(LocalDateTime.now());
        return commentsRepository.save(comment);
    }

    public Comment updateComment(String userName, Long postId, Long commentId, Comment updatedComment) throws PostNotFoundException, CommentNotFoundException, UserAccessDeniedException {  	
        Comment comment = getCommentById(commentId);
        Posts post=postsRepository.findById(postId).orElse(new Posts());
        System.out.println(post.getUserName());
        if (!userName.equals(comment.getCommenteduserName()) && !userName.equals(post.getUserName())) {
            throw new UserAccessDeniedException(userName);
        }
        comment.getPostId();
        comment.setCommentText(updatedComment.getCommentText());
        return commentsRepository.save(comment);
    }


    public void deleteComment(String userName,Long postId, Long commentId) throws PostNotFoundException, CommentNotFoundException, UserAccessDeniedException {
        Comment comment = getCommentById(commentId);
        Posts post=postsRepository.findById(postId).orElse(new Posts());
        
        if (!userName.equals(comment.getCommenteduserName()) && !userName.equals(post.getUserName())) {
            throw new UserAccessDeniedException(userName);
        }
        commentsRepository.delete(comment);
    }
    
    private Comment getCommentById(Long commentId) throws CommentNotFoundException {
        return commentsRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));
    }
    
}
