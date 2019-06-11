package com.thinkwork.Service;

import com.thinkwork.Service.base.BaseService;
import com.thinkwork.common.SysConstants;
import com.thinkwork.entity.*;
import com.thinkwork.repository.MenuRepository;
import com.thinkwork.utils.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuService extends BaseService {
    @Autowired
    MenuRepository menuRepository;

    public void save(Menu menu){
        menuRepository.store(menu);
    }

    @Transactional
    @Modifying
    public void delete(String sid){
        Menu menu = menuRepository.findById(Integer.valueOf(sid)).get();
        menu.setActive(false);
        menuRepository.store(menu);
    };

    @Transactional
    @Modifying
    public void update(Menu tempMenu){
        Menu menu = menuRepository.findById(Integer.valueOf(tempMenu.getSid())).get();
        menu = tempMenu;
        menuRepository.store(menu);
    };

    @Override
    public Page<Menu> findAllCriteria(Integer page, Integer size, HashMap querySet) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "sid");
        Page<Menu> userPage = menuRepository.findAll(new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != querySet.get("query_menuName") && !"".equals(querySet.get("query_menuName"))) {
                    list.add(criteriaBuilder.like(root.get("menuName").as(String.class), "%" + querySet.get("query_menuName") + "%"));
                }
                if (null != querySet.get("query_status") && !"".equals(querySet.get("query_status"))) {
                    if ("1".equals(querySet.get("query_status"))) {
                        list.add(criteriaBuilder.isTrue(root.get("isActive")));
                    } else {
                        list.add(criteriaBuilder.isFalse(root.get("isActive")));
                    }
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return userPage;
    }
    @Override
    public Menu findById(Integer sid){
        return menuRepository.findById(sid).get();
    }

    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

    public List<Menu> getAuthorizedMenus(User user) {
        List<String> roleNameList = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roleNameList.add(role.getRoleName());
        }
        if (null != roleNameList && roleNameList.size() > 0) {
            List<Object[]> objects = menuRepository.findActiveMenusByRole(roleNameList, SysConstants.MENU_IS_ACTIVE);
            List<Menu> menuList = EntityUtils.castEntity(objects, Menu.class);
            return menuList;
        }
        return null;
    }

    public MenuGroup getMenuGroupByMenuSid(Integer menu_sid) {
        MenuGroup mg = menuRepository.findMenuGroupByMenu(menu_sid);
        return mg;
    }
}
