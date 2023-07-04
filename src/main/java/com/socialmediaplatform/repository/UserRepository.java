package com.socialmediaplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmediaplatform.model.User;


public interface UserRepository extends JpaRepository<User, String> {

	public List<User> findAllByFirstNameContaining(String searchTerm);

	public List<User> findAllByLastNameContaining(String lastName);

	public List<User> findAllByUserNameContaining(String searchTerm);

}
