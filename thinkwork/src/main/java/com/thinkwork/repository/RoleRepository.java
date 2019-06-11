package com.thinkwork.repository;

import com.thinkwork.config.jpa.BaseRepository;
import com.thinkwork.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Integer> {

}
