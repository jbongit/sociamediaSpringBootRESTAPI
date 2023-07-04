package com.socialmediaplatform.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearch {
    private String userName;
    private String firstName;
    private String lastName;
    private String bio;
    private List<Posts> posts;
}
