package com.thinkwork.Service;

import com.thinkwork.Service.base.BaseService;
import com.thinkwork.entity.BaseEntity;
import com.thinkwork.entity.Role;
import com.thinkwork.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RoleService extends BaseService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
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
