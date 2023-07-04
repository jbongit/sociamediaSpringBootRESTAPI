package com.socialmediaplatform.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String commentText;
    
    private LocalDateTime commentDateTime;
    
    //User Name Of Person Who Commented
    private String commenteduserName;
    
    private Long postId;
}
