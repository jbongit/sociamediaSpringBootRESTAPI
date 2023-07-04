package com.socialmediaplatform.service;
import com.socialmediaplatform.model.FollowDetails;

public interface ManageFollowerService {
    FollowDetails followUser(FollowDetails user);
    void unfollowUser(String userName, String unfollowUserName);
}