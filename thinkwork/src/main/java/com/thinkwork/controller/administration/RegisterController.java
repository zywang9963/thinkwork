package com.thinkwork.controller.administration;

import com.thinkwork.Service.UserService;
import com.thinkwork.controller.base.BaseController;
import com.thinkwork.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController  extends BaseController {
    @Autowired
    UserService userService;

    private String registerPage = "/sys/register";

    @GetMapping("/register")
    public String singUp() {
        return registerPage;
    }

    @PostMapping("/registerDo")
    public ModelAndView doRegister(@RequestParam(value = "agreement", required = false) String[] agreement
            , @RequestParam String password_confirmation, User tempUser, RedirectAttributes attr) {

        if (StringUtils.isEmpty(tempUser.getUserName())) {
            ModelAndView model = new ModelAndView("redirect:register?nullUserName");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (StringUtils.isEmpty(tempUser.getEmail())) {
            ModelAndView model = new ModelAndView("redirect:register?nullEmail");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (StringUtils.isEmpty(tempUser.getPassword())) {
            ModelAndView model = new ModelAndView("redirect:register?nullPassword");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (StringUtils.isEmpty(password_confirmation)) {
            ModelAndView model = new ModelAndView("redirect:register?nullConfirmPassword");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (null == agreement) {
            ModelAndView model = new ModelAndView("redirect:register?noAgreement");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (hasUser(tempUser.getUserName())) {
            ModelAndView model = new ModelAndView("redirect:register?userExists");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (hasEmail(tempUser.getEmail())) {
            ModelAndView model = new ModelAndView("redirect:register?emailExists");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        if (!tempUser.getPassword().equals(password_confirmation)) {
            ModelAndView model = new ModelAndView("redirect:register?passwordError");
            attr.addFlashAttribute("tempUser", tempUser);
            return model;
        }

        userService.save(tempUser);
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    public Boolean hasUser(String username) {
        if (userService.findByUserName(username) == null) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean hasEmail(String email) {
        if (userService.findByEmail(email) == null) {
            return false;
        } else {
            return true;
        }
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
