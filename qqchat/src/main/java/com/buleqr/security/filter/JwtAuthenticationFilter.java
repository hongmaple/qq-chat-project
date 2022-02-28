package com.buleqr.security.filter;

import com.buleqr.utils.security.JwtTokenUtil;
import com.buleqr.config.RSAKeyProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RSAKeyProperties rsaKeyProp;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(jwtTokenUtil.getHeader());
        if (StringUtils.isEmpty(token)) {
            Map<String, String[]> params = httpServletRequest.getParameterMap();

            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String key = param.getKey(); // 参数名
                String value = StringUtils.join(param.getValue()); // 参数值
                String headerKey = jwtTokenUtil.getHeader();
                if (key.equals(headerKey)) {
                    token = value;
                    break;
                }
            }
        }
        if(!StringUtils.isEmpty(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token, rsaKeyProp.getPublicKey());

            // 如果可以正确的从JWT中提取用户信息，并且该用户未被授权
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jwtTokenUtil.validateToken(token, userDetails, rsaKeyProp.getPublicKey())) {
                    // 给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // 交给spring security管理,在之后的过滤器中不会再被拦截进行二次授权了
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
