package com.socialmediaplatform.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialmediaplatform.model.FollowDetails;
import com.socialmediaplatform.model.User;


public interface ManageFollowerRepository extends JpaRepository<FollowDetails, Long> {

    @Modifying
    @Query(value = "delete from FOLLOW_DATA b where b.follower=:follower AND b.following=:following", nativeQuery = true)
    void unfollowUser(@Param("follower") String follower, @Param("following") String following);

    List<FollowDetails> findByFollower(User follower);
    List<FollowDetails> findByFollowing(User following);
    FollowDetails findByFollowerAndFollowing(User follower, User following);
}
