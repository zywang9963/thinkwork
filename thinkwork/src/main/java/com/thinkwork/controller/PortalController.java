package com.thinkwork.controller;

import com.thinkwork.Service.MenuGroupService;
import com.thinkwork.Service.MenuService;
import com.thinkwork.Service.UserService;
import com.thinkwork.common.SysConstants;
import com.thinkwork.controller.base.BaseController;
import com.thinkwork.entity.MenuGroup;
import com.thinkwork.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PortalController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/thinkwork", "/thinkwork/index"})
    public String portalIndex(Model model, HttpSession session) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
        if (null == session.getAttribute(SysConstants.SESSION_USER_MENU_KEY+user.getUserName())) {
            logger.info("No user session found, will create user session.");
            session.setAttribute(SysConstants.SESSION_USER_MENU_KEY+user.getUserName(),userService.getAssembliedMenuGroups(user));
        }else{
            logger.info("Found user session, will load user's session data.");
        }
        List<MenuGroup> mgList = (List<MenuGroup>)session.getAttribute(SysConstants.SESSION_USER_MENU_KEY+user.getUserName());
        model.addAttribute("menuGroup", mgList);
        model.addAttribute("user",user);
        return "/index";
    }

    @Override
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0")  Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size, HttpServletRequest request) {
        return null;
    }

    @Override
    public String detail(Model model, String requestType, HttpServletRequest request) {
        return null;
    }

    @Override
    public String delete(Model model, String sid) {
        return null;
    }
}
