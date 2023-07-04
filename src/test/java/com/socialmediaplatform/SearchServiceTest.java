package com.socialmediaplatform;
/*
import com.hus23.assignment.socialmediaplatform.model.Posts;
import com.hus23.assignment.socialmediaplatform.model.User;
import com.hus23.assignment.socialmediaplatform.model.UserSearch;
import com.hus23.assignment.socialmediaplatform.repository.PostsRepository;
import com.hus23.assignment.socialmediaplatform.repository.UserRepository;
import com.hus23.assignment.socialmediaplatform.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SearchServiceTests {

	@Autowired
	private SearchService searchService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostsRepository postsRepository;

	@Test
	void searchUserByUserName() {
	    User user = userRepository.save(new User("johndoe", "John", "Doe", "This is my bio", null, null));
	    postsRepository.save(new Posts(null, user.getUserName(), "This is the content of my first post",LocalDateTime.now(),"Global", null));

	    List<UserSearch> userSearches = searchService.searchUserByUserNameAndFirstNameAndLastName("johndoe");
	    assertEquals(1, userSearches.size());
	    UserSearch userSearch = userSearches.get(0);
	    assertEquals("johndoe", userSearch.getUserName());
	    assertEquals("John", userSearch.getFirstName());
	    assertEquals("Doe", userSearch.getLastName());
	    assertNotNull(userSearch.getPosts());
	    assertEquals(1, userSearch.getPosts().size());
	}


    @Test
    void getAllPostsByLocation() {
        List<Posts> posts = searchService.getAllPostsByLocation("San Francisco");
        assertEquals(0, posts.size());
    }
}*/
