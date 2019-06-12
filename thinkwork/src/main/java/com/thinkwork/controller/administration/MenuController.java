package com.thinkwork.controller.administration;

import com.thinkwork.Service.MenuService;
import com.thinkwork.common.SysConstants;
import com.thinkwork.controller.base.BaseController;
import com.thinkwork.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
@RequestMapping(path = "/sys/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    private String listPage = "/sys/menuList";
    private String detailPage = "/sys/menuDetail";

    @GetMapping(value = {"/list",""})
    @Override
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0")  Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size, HttpServletRequest request) {
        HashMap querySet = new HashMap();
        String query_menuName = "";
        String query_status = "";
        if (!StringUtils.isEmpty(request.getParameter("query_menuName"))){
            query_menuName = request.getParameter("query_menuName");
        }
        if (!StringUtils.isEmpty(request.getParameter("query_status"))){
            query_status = request.getParameter("query_status");
        }
        querySet.put("query_menuName",query_menuName);
        querySet.put("query_status",query_status);

        Page<Menu> results = menuService.findAllCriteria(page, size, querySet);
        model.addAttribute("results",results);
        model.addAttribute("query_menuName",query_menuName);
        model.addAttribute("query_status",query_status);
        return listPage;
    }

    @GetMapping("/detail")
    @Override
    public String detail(Model model, String requestType, HttpServletRequest request) {

        if (setParametersByRequestType(model, menuService, requestType, request))
            return detailPage;

        model.addAttribute("requestTypeError","1");
        return listPage;
    }

    @PostMapping("/create")
    public String create(Model model, Menu tempMenu, HttpServletRequest request) {
        model.addAttribute("requestType", SysConstants.REQUEST_TYPE_CREATE);
        model.addAttribute("tempData", tempMenu);
        Menu menu = new Menu();
        menu = tempMenu;
        menuService.save(menu);
        return detailPage;
    }

    @PostMapping("/update")
    public String update(Model model, Menu menu, HttpServletRequest request) {
        menuService.update(menu);
        return detailPage;
    }

    @PostMapping("/delete")
    public String delete(Model model, String sid) {
        menuService.delete(sid);
        return listPage;
    }
}
