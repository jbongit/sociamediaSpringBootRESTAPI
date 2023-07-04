package com.socialmediaplatform.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER_TEST")
public class User {

	@Id
	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "FIRSTNAME")
	private String firstName;

	@Column(name = "LASTNAME")
	private String lastName;

	@Column(name = "BIO")
	private String bio;

	@Column(name = "PASSWORD")
	private String password;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userName")
	@Builder.Default
	private List<Posts> posts = new ArrayList<>();

	@ElementCollection
	@Builder.Default
	private Set<String> followers = new HashSet<>();

	@ElementCollection
	@Builder.Default
	private Set<String> following = new HashSet<>();

	public void follow(User userToFollow) {
		followers.add(userToFollow.getUserName());
	}

	public void unfollow(User userToUnfollow) {
		followers.remove(userToUnfollow.getUserName());
	}

	public boolean isFollowing(User user) {
		return following.contains(user.getUserName());
	}

	public void addFollowing(User userToFollow) {
		following.add(userToFollow.getUserName());
	}

	public void addUnfollowing(User userToUnfollow) {
		following.remove(userToUnfollow.getUserName());
	}

}
