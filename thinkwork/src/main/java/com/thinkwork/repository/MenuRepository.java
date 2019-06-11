package com.thinkwork.repository;

import com.thinkwork.config.jpa.BaseRepository;
import com.thinkwork.entity.Menu;
import com.thinkwork.entity.MenuGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends BaseRepository<Menu, Integer> {

    @Query(value = "select m.sid,m.menuName,m.displayName,m.description,m.url,m.dateCreated,m.dateModified,m.isActive from Menu m inner join m.roles as r where r.roleName in :roleNameList and m.isActive = :isActive")
    List<Object[]> findActiveMenusByRole(@Param(value = "roleNameList") List<String> roleNameList, @Param(value = "isActive") Boolean isActive);

    @Query(value = "select new com.thinkwork.entity.MenuGroup(mg.sid,mg.name,mg.description,mg.parentId,mg.isRoot) from Menu m inner join m.menuGroup as mg where m.sid = :menu_sid")
    MenuGroup findMenuGroupByMenu(@Param(value = "menu_sid") Integer menu_sid);
}
