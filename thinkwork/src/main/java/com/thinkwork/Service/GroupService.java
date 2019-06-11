package com.thinkwork.Service;

import com.thinkwork.Service.base.BaseService;
import com.thinkwork.entity.BaseEntity;
import com.thinkwork.entity.UserGroup;
import com.thinkwork.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GroupService extends BaseService {
    @Autowired
    GroupRepository groupRepository;

    public List<UserGroup> findAll(){
        return groupRepository.findAll();
    }

    @Override
    public BaseEntity findById(Integer sid) {
        return null;
    }

    @Override
    public <T> T findAllCriteria(Integer page, Integer size, HashMap querySet) {
        return null;
    }
}
