package com.thinkwork.repository;

import com.thinkwork.config.jpa.BaseRepository;
import com.thinkwork.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    @Query(value = "select r.roleName from User u inner join u.roles as r where u.userName = :userName")
    List<String> queryUserOwnedRoles(@Param(value = "userName") String userName);

    User findByUserName(String userName);

    User findByEmail(String email);
}
