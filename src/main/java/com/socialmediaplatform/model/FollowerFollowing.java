package com.socialmediaplatform.model;

import java.util.ArrayList;
import java.util.List;

public class FollowerFollowing {

    List<String> follower = new ArrayList<>();
    List<String> following = new ArrayList<>();

    public List<String> getFollower() {
        return follower;
    }

    public void setFollower(List<String> follower) {
        this.follower = follower;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }
}