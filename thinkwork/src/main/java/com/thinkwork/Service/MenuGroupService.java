package com.thinkwork.Service;

import com.thinkwork.Service.base.BaseService;
import com.thinkwork.entity.BaseEntity;
import com.thinkwork.repository.MenuGroupRepository;
import com.thinkwork.entity.MenuGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service
public class MenuGroupService extends BaseService {
    @Autowired
    MenuGroupRepository menuGroupRepository;

    public Collection<MenuGroup> getAllRootMenuGroups() {
        return menuGroupRepository.findMenuGroupByIsRoot(true);
    }

    public Collection<MenuGroup> getAllMenuGroups() {
        return menuGroupRepository.findMenuGroupByIsRoot(false);
    }

    @Override
    public BaseEntity findById(Integer sid) {
        return null;
    }

    @Override
    public <T> T findAll() {
        return null;
    }

    @Override
    public <T> T findAllCriteria(Integer page, Integer size, HashMap querySet) {
        return null;
    }
}
