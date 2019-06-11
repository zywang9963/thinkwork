package com.thinkwork.controller.base;

import com.thinkwork.Service.base.BaseService;
import com.thinkwork.common.SysConstants;
import com.thinkwork.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Controller
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public abstract String list(Model model, Integer page, Integer size, HttpServletRequest request);

    public abstract String detail(Model model, String requestType, HttpServletRequest request);

    public abstract String delete(Model model, String sid);

    public Boolean setParametersByRequestType(Model model, BaseService baseService, String requestType, HttpServletRequest request){
        if (SysConstants.REQUEST_TYPE_DISPLAY.equalsIgnoreCase(requestType)){
            model.addAttribute("tempData", baseService.findById(Integer.valueOf(request.getParameter("sid"))));
            model.addAttribute("requestType", SysConstants.REQUEST_TYPE_DISPLAY);
            return true;
        }
        if (SysConstants.REQUEST_TYPE_CREATE.equalsIgnoreCase(requestType)){
            model.addAttribute("requestType", SysConstants.REQUEST_TYPE_CREATE);
            return true;
        }
        if (SysConstants.REQUEST_TYPE_UPDATE.equalsIgnoreCase(requestType)){
            model.addAttribute("tempData", baseService.findById(Integer.valueOf(request.getParameter("sid"))));
            model.addAttribute("requestType", SysConstants.REQUEST_TYPE_UPDATE);
            return true;
        }

        return false;
    }
}
