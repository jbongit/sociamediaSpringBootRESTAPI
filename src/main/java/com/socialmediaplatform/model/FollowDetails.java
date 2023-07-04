package com.socialmediaplatform.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "FOLLOW_DATA")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowDetails {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;
    
    public FollowDetails(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}