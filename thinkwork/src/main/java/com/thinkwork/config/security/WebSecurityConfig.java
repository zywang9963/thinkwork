package com.thinkwork.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.Locale;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider provider;//自定义验证
    @Autowired
    private MyUserDetailsService userDetailsService;//自定义用户服务

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("myDataSource")
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/thinkwork/register*",
                        "/thinkwork/err*",
                        "/thinkwork/forgotpassword",
                        "/thinkwork/passwordmail",
                        "/thinkwork/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .requiresChannel()
                .mvcMatchers("/thinkwork/administration/**", "/thinkwork/login*", "/thinkwork/register*").requiresSecure()
                .and()
                .formLogin()
                .loginPage("/thinkwork/login")
                .loginProcessingUrl("/thinkwork/loginDo")
                .failureUrl("/thinkwork/login?error")
                .defaultSuccessUrl("/thinkwork/index").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout().logoutUrl("/thinkwork/login?logout").clearAuthentication(true).permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()
                .tokenValiditySeconds(1209600)
                .tokenRepository(tokenRepository());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //ignore the static resources, css/js/img
        web.ignoring().antMatchers("/assets/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //将验证过程交给自定义验证工具
        auth.authenticationProvider(provider);
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl j=new JdbcTokenRepositoryImpl();
        j.setDataSource(dataSource);
        return j;
    }
}
