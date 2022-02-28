package com.buleqr.security;

import com.buleqr.pojo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class JwtUser implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 直接使用User创建JwtUser的构造器
     * @param user
     */
    public JwtUser(User user) {
        System.out.println(user);
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id='" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
