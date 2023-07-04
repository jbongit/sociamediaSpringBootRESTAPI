package com.socialmediaplatform.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialmediaplatform.model.Posts;
import com.socialmediaplatform.model.User;
import com.socialmediaplatform.model.UserSearch;
import com.socialmediaplatform.repository.PostsRepository;
import com.socialmediaplatform.repository.UserRepository;

@Service
public class SearchService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostsRepository postsRepository;

    public List<UserSearch> searchUserByUserNameAndFirstNameAndLastName(String searchTerm) {
    	if(searchTerm.isEmpty()) {
    		return null;
    	}
        List<User> userListByFirstName = userRepository.findAllByFirstNameContaining(searchTerm);
        List<User> userListByLastName = userRepository.findAllByLastNameContaining(searchTerm);
        List<User> userListByUserName = userRepository.findAllByUserNameContaining(searchTerm);

        Set<User> uniqueUsers = new HashSet<>();
        uniqueUsers.addAll(userListByFirstName);
        uniqueUsers.addAll(userListByLastName);
        uniqueUsers.addAll(userListByUserName);
        
        List<UserSearch> userSearchList = uniqueUsers.stream()
            .map(user -> {
            	List<Posts> userPosts = postsRepository.findByUserName(user.getUserName());
                UserSearch object = UserSearch.builder()
                    .userName(user.getUserName())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .bio(user.getBio())
                    .posts(userPosts)
                    .build();
                return object;
            })
            .collect(Collectors.toList());

        return userSearchList;
    }

    public List<Posts> getAllPostsByLocation(String location){
    	return postsRepository.findAllByLocationContaining(location);
    }
    
}
