package com.socialmediaplatform.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long postId;
    
    private String userName;
    
    private String description;
    
    private LocalDateTime postDateTime;
    
    private String location;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private List<Comment> comments;
}
