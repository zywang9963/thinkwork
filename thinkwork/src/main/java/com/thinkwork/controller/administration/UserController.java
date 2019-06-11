package com.thinkwork.controller.administration;

import com.thinkwork.Service.UserService;
import com.thinkwork.controller.base.BaseController;
import com.thinkwork.common.SysConstants;
import com.thinkwork.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(path = "/thinkwork/sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    private String listPage = "/sys/userList";
    private String detailPage = "/sys/userDetail";

    @GetMapping(value = {"/list", ""})
    @Override
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size, HttpServletRequest request) {
        HashMap querySet = new HashMap();
        String query_userName = "";
        String query_email = "";
        String query_status = "";
        if (!StringUtils.isEmpty(request.getParameter("query_userName"))) {
            query_userName = request.getParameter("query_userName");
        }
        if (!StringUtils.isEmpty(request.getParameter("query_email"))) {
            query_email = request.getParameter("query_email");
        }
        if (!StringUtils.isEmpty(request.getParameter("query_status"))) {
            query_status = request.getParameter("query_status");
        }
        querySet.put("userName", query_userName);
        querySet.put("email", query_email);
        querySet.put("status", query_status);
        Page<User> results = userService.findAllCriteria(page, size, querySet);
        model.addAttribute("results", results);
        model.addAttribute("query_userName", query_userName);
        model.addAttribute("query_email", query_email);
        model.addAttribute("query_status", query_status);
        return listPage;
    }

    @GetMapping("/detail")
    @Override
    public String detail(Model model, String requestType, HttpServletRequest request) {

        if (setParametersByRequestType(model, userService, requestType, request))
            return detailPage;

        model.addAttribute("requestTypeError", "1");
        return listPage;
    }

    @PostMapping("/create")
    public String create(Model model, User tempUser, HttpServletRequest request) {

        model.addAttribute("requestType", SysConstants.REQUEST_TYPE_CREATE);
        model.addAttribute("tempData", tempUser);
        String password_confirmation = request.getParameter("password_confirmation");
        if (!validateForm(model, tempUser, password_confirmation))
            return detailPage;

        User newUser = new User();
        newUser = tempUser;
        userService.save(newUser);
        model.addAttribute("createSuccessfully", "1");
        return detailPage;
    }

    @PostMapping("/update")
    public String update(Model model, User tempUser, HttpServletRequest request) {
        model.addAttribute("requestType", SysConstants.REQUEST_TYPE_UPDATE);
        model.addAttribute("tempData", tempUser);
        userService.update(tempUser);
        model.addAttribute("updateSuccessfully", "1");
        return detailPage;
    }

    @PostMapping("/delete")
    @Override
    public String delete(Model model, @RequestParam String sid) {
//        User user = userService.findById(Integer.valueOf(sid));
//        userService.delete(user);
//        model.addAttribute("deleteSuccessfully", "1");
        return "redirect:list";
    }

    private boolean hasUser(String username) {
        if (userService.findByUserName(username) == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean hasEmail(String email) {
        if (userService.findByEmail(email) == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateForm(Model model, User tempUser, String password_confirmation) {
        Boolean result = true;

        if (StringUtils.isEmpty(tempUser.getUserName())) {
            model.addAttribute("nullUserName", "1");
            result = false;
        }

        if (StringUtils.isEmpty(tempUser.getPassword())) {
            model.addAttribute("nullPassword", "1");
            result = false;
        }

        if (hasUser(tempUser.getUserName())) {
            model.addAttribute("userExists", "1");
            result = false;
        }

        if (hasEmail(tempUser.getEmail())) {
            model.addAttribute("emailExists", "1");
            result = false;
        }

        if (StringUtils.isEmpty(password_confirmation)) {
            model.addAttribute("nullConfirmPassword", "1");
            result = false;
        }

        if (!tempUser.getPassword().equals(password_confirmation)) {
            model.addAttribute("passwordError", "1");
            result = false;
        }
        return result;
    }
}
