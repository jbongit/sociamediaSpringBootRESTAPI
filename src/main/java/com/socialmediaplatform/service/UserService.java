package com.socialmediaplatform.service;

import java.util.List;

import com.socialmediaplatform.model.FollowerFollowing;
import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.model.User;

public interface UserService {
	User saveUser(User user);
    List<User> getAllUsers();
    User getUserByUserName(String userName);
    User updateUser(User user, String userName);
    void deleteUser(String userName);

    Posts createPost(String userName);


    void followUser(String followerId, String followingId);
    void unfollowUser(String followerId, String followingId);

    FollowerFollowing getFollowerAndFollowing(String userId);
}