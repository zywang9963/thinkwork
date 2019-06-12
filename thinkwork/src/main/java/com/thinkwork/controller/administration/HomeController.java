package com.thinkwork.controller.administration;

import com.thinkwork.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/sys")
public class HomeController extends BaseController {

    @GetMapping(value = {"/home"})
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "0")  Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size, HttpServletRequest request) {
        return "/sys/home";
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
