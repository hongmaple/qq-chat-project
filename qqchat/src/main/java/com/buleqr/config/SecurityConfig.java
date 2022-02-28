package com.buleqr.config;

import com.buleqr.security.filter.JwtAuthenticationFilter;
import com.buleqr.security.handle.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 认证失败处理类
     */
    @Autowired
    private AuthenticationEntryPointImpl unauthorizedHandler;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 基于token分布式认证，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 配置权限
                .authorizeRequests()
                // 登录Login 验证码CaptchaImage 允许匿名访问 但是如果携带了token会报错403
                .antMatchers(
                        "/user/login",
                        "/user/register",
                        "/common/upload",
                        "/user/retrievePassword"
                        ).anonymous()
                // 静态资源放行
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/profile/**",
                        "/common/download/**"
                ).permitAll()
                .antMatchers(
                        HttpMethod.POST,
                        "/user/sendEmailCode"
                ).permitAll()
                // 除了上面所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                // 允许跨域访问 等同于 config类中的corsConfigurationSource
                .cors()
                .and()
                // CRSF禁用，因为不使用session，禁用跨站csrf攻击防御，否则无法登陆成功
                .csrf().disable();

        // 退出功能
        http.logout().logoutUrl("/logout");
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //忽略websocket请求
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/ws/**");
    }
}
