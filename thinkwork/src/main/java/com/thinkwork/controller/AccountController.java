package com.thinkwork.controller;

import com.thinkwork.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AccountController extends BaseController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }


// the login processing has been replaced by spring security
//    @PostMapping("/loginDo")
//    public String doLogin(@RequestParam String username
//            , @RequestParam String password, HttpServletRequest request, Model model) {
//        String rem = request.getParameter("remember_me");
//        logger.info("--------------------- get into my login process----"+rem);
//        User user = new User();
//        user.setUserName(username);
//        user.setPassword(password);
//        if (userService.verifyUser(user)) {
//            user = userService.findByName(username);
//            model.addAttribute("user", user);
//            logger.info(" -------------" + user.getImg());
//            return "index";
//        } else {
//            return "login";
//        }
//    }
//

    @GetMapping("/forgotpassword")
    public String forgotPassoword() {
        return "/administration/forgot_password";
    }

    @PostMapping("/passwordMail")
    public String passwordMail(@RequestParam(value = "email") String email) {
        //call send mail function
        return "redirect:forgot_password?success";
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
