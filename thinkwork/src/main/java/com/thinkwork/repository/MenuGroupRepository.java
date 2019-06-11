package com.thinkwork.repository;

import com.thinkwork.config.jpa.BaseRepository;
import com.thinkwork.entity.MenuGroup;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MenuGroupRepository extends BaseRepository<MenuGroup, Integer> {

    Collection<MenuGroup> findMenuGroupByIsRoot(Boolean isRoot);

}
