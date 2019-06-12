package com.thinkwork.controller.administration;

import com.thinkwork.Service.RoleService;
import com.thinkwork.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    private String listPage = "/sys/roleList";

    @GetMapping(value = {"/list",""})
    @Override
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0")  Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size, HttpServletRequest request) {
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("defaultTab", "role");
        return listPage;
    }

    @GetMapping("/detail")
    @Override
    public String detail(Model model, String requestType, HttpServletRequest request) {
        return null;
    }

    @Override
    public String delete(Model model, String sid) {
        return null;
    }
}
