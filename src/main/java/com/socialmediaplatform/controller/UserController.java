package com.socialmediaplatform.controller;

import java.util.List;

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

import com.socialmediaplatform.model.FollowerFollowing;
import com.socialmediaplatform.model.User;
import com.socialmediaplatform.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    // build create employee REST API
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user  ){
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    // build get all users REST API
    @GetMapping("/allusers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // build get user by id REST API
    // http://localhost:8080/users/kri
    @GetMapping("{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){
        return new ResponseEntity<User>(userService.getUserByUserName(userName), HttpStatus.OK);
    }

    // build update user REST API
    // http://localhost:8080/users/kri
    @PutMapping("{userName}")
    public ResponseEntity<User> updateUser(@PathVariable("userName") String userName
            ,@RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(user, userName), HttpStatus.OK);
    }

    // build delete user by using userName the primaryKey
    // http://localhost:8080/users/kri
    @DeleteMapping("{userName}")
    public ResponseEntity<String> deleteUser(@PathVariable("userName") String userName){


        userService.deleteUser(userName);

        return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
    }


    //api to implement follow functionality
    //http://localhost:8080/users/abcd/follow/abcde
    @PostMapping("{followerId}/follow/{followingId}")
    public ResponseEntity<String> followUser(@PathVariable("followerId") String followerId,@PathVariable("followingId") String followingId){
        userService.followUser(followerId,followingId);
        return new ResponseEntity<String>(followerId + " started following "+followingId, HttpStatus.OK);

    }

    //api to implement unfollow functionality
    //http://localhost:8080/users/abcd/unfollow/abcde
    @PostMapping("{followerId}/unfollow/{followingId}")
    public ResponseEntity<String> unfollowUser(@PathVariable("followerId") String followerId,@PathVariable("followingId") String followingId){
        userService.unfollowUser(followerId,followingId);

        return new ResponseEntity<String>(followerId + " is now unfollowing "+followingId, HttpStatus.OK);

    }

    //api to get all the followers and following of a particular user
    //http://localhost:8080/users/abc/followerAndFollowing
    @GetMapping("{userId}/followerAndFollowing")
    public FollowerFollowing getFollowerAndFollowing(@PathVariable("userId") String userId){
       return userService.getFollowerAndFollowing(userId);

    }
}