package com.thinkwork.repository;

import com.thinkwork.config.jpa.BaseRepository;
import com.thinkwork.entity.UserGroup;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository extends BaseRepository<UserGroup, Long> {

}
