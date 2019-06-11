package com.thinkwork.Service;

import com.thinkwork.Service.base.BaseService;
import com.thinkwork.entity.BaseEntity;
import com.thinkwork.repository.UserRepository;
import com.thinkwork.entity.Menu;
import com.thinkwork.entity.MenuGroup;
import com.thinkwork.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class UserService extends BaseService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MenuGroupService menuGroupService;

    @Autowired
    MenuService menuService;

    public Page<User> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "sid");
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllCriteria(Integer page, Integer size, HashMap querySet) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "sid");
        Page<User> userPage = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != querySet.get("userName") && !"".equals(querySet.get("userName"))) {
                    list.add(criteriaBuilder.like(root.get("userName").as(String.class), "%" + querySet.get("userName") + "%"));
                }
                if (null != querySet.get("email") && !"".equals(querySet.get("email"))) {
                    list.add(criteriaBuilder.like(root.get("email").as(String.class), "%" + querySet.get("email") + "%"));
                }
                if (null != querySet.get("status") && !"".equals(querySet.get("status"))) {
                    if ("1".equals(querySet.get("status"))) {
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


    public List<String> queryUserOwnedRoles(String userName) {
        return userRepository.queryUserOwnedRoles(userName);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer sid) {
        return userRepository.findById(sid).get();
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(initUser(user));
    }

    @Transactional
    @Modifying
    public void update(User user) {
        userRepository.update(initUser(user));
    }

    @Transactional
    @Modifying
    public void delete(User user){
        userRepository.delete(user);
    }

    private User initUser(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword().trim()));
        if (null == user.getGender())
            user.setGender(3);

        if (null == user.getImg())
            if (user.getGender() == 1)
                user.setImg("/assets/images/male.jpg");
            else if (user.getGender() == 1)
                user.setImg("/assets/images/female.jpg");
            else
                user.setImg("/assets/images/others.jpg");

        return user;
    }

    public Boolean verifyUser(User user) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        if (encoder.matches(user.getPassword(), userRepository.findById(user.getSid()).get().getPassword()))
            return true;
        else
            return false;
    }

    public Collection<MenuGroup> getAssembliedMenuGroups(User user) {
        Collection<MenuGroup> userMenuGroups = new ArrayList<>();
        Collection<MenuGroup> allRootMenuGroups = menuGroupService.getAllRootMenuGroups();
        List<Menu> authorizedMenus = menuService.getAuthorizedMenus(user);

        if (null == authorizedMenus)
            return null;

        for (MenuGroup mg : allRootMenuGroups) {
            Boolean hasAuthorizedMenus = false;
            mg.getMenuList().clear();
            for (Iterator it = authorizedMenus.iterator(); it.hasNext(); ) {
                Menu menu = (Menu) it.next();
                menu.setMenuGroup(menuService.getMenuGroupByMenuSid(menu.getSid()));
                if (menu.getMenuGroup().getSid().equals(mg.getSid())) {
                    mg.getMenuList().add(menu);
                    hasAuthorizedMenus = true;
                }
            }
            if (hasAuthorizedMenus) {
                userMenuGroups.add(mg);
            }
        }

        return userMenuGroups;
    }
}
