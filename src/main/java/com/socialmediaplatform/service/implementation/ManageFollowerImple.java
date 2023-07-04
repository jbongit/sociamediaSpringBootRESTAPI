package com.socialmediaplatform.service.implementation;

import org.springframework.stereotype.Service;

import com.socialmediaplatform.model.FollowDetails;
import com.socialmediaplatform.repository.ManageFollowerRepository;
import com.socialmediaplatform.service.ManageFollowerService;
import com.socialmediaplatform.service.UserService;

@Service 
public class ManageFollowerImple implements ManageFollowerService {

    private ManageFollowerRepository manageFollowerRepository;

    private UserService userService;

    public ManageFollowerImple(ManageFollowerRepository manageFollowerRepository) {
        super();
        this.manageFollowerRepository = manageFollowerRepository;
    }

    @Override
    public FollowDetails followUser(FollowDetails followDetails) {
        return manageFollowerRepository.save(followDetails);
    }

    @Override
    public void unfollowUser(String userName, String followUserName) {

         manageFollowerRepository.unfollowUser(userName, followUserName);
    }
}
