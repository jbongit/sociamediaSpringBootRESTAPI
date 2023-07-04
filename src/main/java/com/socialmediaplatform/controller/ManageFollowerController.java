package com.socialmediaplatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmediaplatform.model.FollowDetails;
import com.socialmediaplatform.service.ManageFollowerService;

@RestController
@RequestMapping("/follow")
public class ManageFollowerController {

    @Autowired
    private ManageFollowerService manageFollowerService;

//    public ManageFollowerController(ManageFollowerService manageFollowerService) {
//        super();
//        this.manageFollowerService = manageFollowerService;
//    }

    @PostMapping
    public ResponseEntity<FollowDetails> followUser(@RequestBody FollowDetails followDetails){
        return new ResponseEntity<FollowDetails>(manageFollowerService.followUser(followDetails), HttpStatus.CREATED);
    }
    @DeleteMapping("{userName}")
    public ResponseEntity<String> unfollowUser(@PathVariable("userName") String userName,@RequestBody String followUserName){

        manageFollowerService.unfollowUser(userName,followUserName);

        return new ResponseEntity<String>("User successfully unfollowed!.", HttpStatus.OK);
    }
}