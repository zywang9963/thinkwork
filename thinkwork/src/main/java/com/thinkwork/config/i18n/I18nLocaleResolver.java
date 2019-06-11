package com.thinkwork.config.i18n;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import java.util.Locale;

/**
 * 自定义国际化语言解析器
 *
 */
public class I18nLocaleResolver implements LocaleResolver{

    private static final String I18N_LANGUAGE = "lang";
    private static final String I18N_LANGUAGE_SESSION = "lang_session";
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        String lang = req.getParameter(I18N_LANGUAGE);
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(lang)) {
            String[] language = lang.split("_");
            locale = new Locale(language[0], language[1]);
            //将国际化语言保存到session
            HttpSession session = req.getSession();
            session.setAttribute(I18N_LANGUAGE_SESSION, locale);
        }else {
            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
            HttpSession session = req.getSession();
            Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
            if(localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest req, HttpServletResponse res, Locale locale) {
    }
}
