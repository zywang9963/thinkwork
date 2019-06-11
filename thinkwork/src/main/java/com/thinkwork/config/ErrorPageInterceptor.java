package com.thinkwork.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private List<Integer> errorCodeList = Arrays.asList(400, 404, 403,405, 500, 503);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        if (errorCodeList.contains(response.getStatus())) {
            logger.info("------ErrorPageInterceptor"+response.getStatus());
            response.sendRedirect("/thinkwork/error?errorCode=" + response.getStatus());
            return false;
        }

        return super.preHandle(request, response, handler);
    }
}
