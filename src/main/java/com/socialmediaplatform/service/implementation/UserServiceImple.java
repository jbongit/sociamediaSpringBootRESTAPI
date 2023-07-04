package com.socialmediaplatform.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.socialmediaplatform.exception.ResourceAlreadyPresentException;
import com.socialmediaplatform.exception.ResourceNotFoundException;
import com.socialmediaplatform.model.FollowDetails;
import com.socialmediaplatform.model.FollowerFollowing;
import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.model.User;
import com.socialmediaplatform.repository.ManageFollowerRepository;
import com.socialmediaplatform.repository.PostsRepository;
import com.socialmediaplatform.repository.UserRepository;
import com.socialmediaplatform.service.UserService;


@Service
public class UserServiceImple implements UserService {

    private UserRepository userRepository;
    private PostsRepository postsRepository;

    private ManageFollowerRepository manageFollowerRepository;

    public UserServiceImple(UserRepository userRepository, ManageFollowerRepository manageFollowerRepository) {
        super();
        this.userRepository = userRepository;
        this.manageFollowerRepository = manageFollowerRepository;
    }

    @Override
    public User saveUser(User user) {
        Optional<User> existingUser =  userRepository.findById(user.getUserName());
        if(existingUser.isPresent()) {
            throw new ResourceAlreadyPresentException("User", "userName", user.getUserName());
       }

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserName(String userName) {
        return (User) userRepository.findById(userName).orElseThrow(() ->
                new ResourceNotFoundException("User", "userName", userName));

    }

    @Override
    public User updateUser(User user, String userName) {

        // we need to check whether user with given userName is present in DB or not
        User existingUser = (User) userRepository.findById(userName).orElseThrow(() ->
                new ResourceNotFoundException("User", "userName", userName));
        if(!user.getFirstName().isEmpty()) {
            existingUser.setFirstName(user.getFirstName());
        }
        if(!user.getLastName().isEmpty()) {
            existingUser.setLastName(user.getLastName());
        }
        if(!user.getBio().isEmpty()) {
            existingUser.setBio(user.getBio());
        }
        if(!user.getPassword().isEmpty()){
            existingUser.setPassword(user.getPassword());
        }

        // save existing user to DataBase
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(String userName) {

        // check whether a user with given userName is present in DB or not
        userRepository.findById(userName).orElseThrow(() ->
                new ResourceNotFoundException("User", "userName", userName));
        userRepository.deleteById(userName);
    }

   @Override
    public Posts createPost(String userName) {
       // TODO Auto-generated method stub
       return null;
    }

//    public void followUser(String followerId, String followingId){
//        User follower = userRepository.findById(followerId).orElseThrow(() -> new IllegalArgumentException("Follower does not exist"));
//        User following = userRepository.findById(followingId).orElseThrow(() -> new IllegalArgumentException("Following does not exist"));
//
//        if(follower.equals(following)){
//            throw new IllegalStateException("Cannot follow self");
//        }
//        FollowDetails ifExistsAlready = manageFollowerRepository.findByFollowerAndFollowing(follower,following);
//        if(ifExistsAlready != null){
//            throw new IllegalStateException(followerId+" already following "+followingId);
//        }
//        follower.followUser(following);
//        userRepository.save(follower);
//    }

   public void followUser(String followerId, String followingId) {
	    User follower = userRepository.findById(followerId)
	            .orElseThrow(() -> new IllegalArgumentException("Follower does not exist"));
	    User following = userRepository.findById(followingId)
	            .orElseThrow(() -> new IllegalArgumentException("Following does not exist"));

	    if (follower.equals(following)) {
	        throw new IllegalStateException("Cannot follow self");
	    }

	    FollowDetails existingFollow = manageFollowerRepository.findByFollowerAndFollowing(follower, following);
	    if (existingFollow != null) {
	        throw new IllegalStateException(followerId + " already following " + followingId);
	    }

	    following.follow(follower);
	    follower.addFollowing(following);
	    userRepository.save(follower);
	    userRepository.save(following);
	}

   
   public void unfollowUser(String followerId, String followingId) {
	    User follower = userRepository.findById(followerId)
	            .orElseThrow(() -> new IllegalArgumentException("Follower does not exist"));
	    User following = userRepository.findById(followingId)
	            .orElseThrow(() -> new IllegalArgumentException("Following does not exist"));

	    if (!follower.isFollowing(following)) {
	        throw new IllegalStateException(followerId + " is not following " + followingId);
	    }

	    following.unfollow(follower);
	    follower.addUnfollowing(following);
	    userRepository.save(follower);
	    userRepository.save(following);
	}

    @Override
    public FollowerFollowing getFollowerAndFollowing(String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Following does not exist"));

        List<FollowDetails> followers = manageFollowerRepository.findByFollower(user);
        List<FollowDetails> followings = manageFollowerRepository.findByFollowing(user);


        FollowerFollowing ff = new FollowerFollowing();
        List<String> followerList = new ArrayList<>();
        followerList.addAll(followings.stream().map(follow -> follow.getFollower().getUserName()).collect(Collectors.toList()));
        ff.setFollower(followerList);

        List<String> followingList = new ArrayList<>();
        followingList.addAll(followers.stream().map(follow -> follow.getFollowing().getUserName()).collect(Collectors.toList()));
        ff.setFollowing(followingList);

        return ff;
    }
}