package com.buleqr.controller;

import com.buleqr.dto.request.ChangeToEmailBody;
import com.buleqr.dto.request.CustomerRegisterBody;
import com.buleqr.dto.request.RetrievePasswordBody;
import com.buleqr.dto.request.UpdatePwdReq;
import com.buleqr.pojo.AjaxResult;
import com.buleqr.pojo.PageDomain;
import com.buleqr.pojo.User;
import com.buleqr.security.LoginBody;
import com.buleqr.security.service.JwtAuthService;
import com.buleqr.service.UserService;
import com.buleqr.service.VerificationCodeManager;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author buleqr
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtAuthService jwtAuthService;
    private final VerificationCodeManager verificationCodeManager;

    public UserController(UserService userService, JwtAuthService jwtAuthService, VerificationCodeManager verificationCodeManager) {
        this.userService = userService;
        this.jwtAuthService = jwtAuthService;
        this.verificationCodeManager = verificationCodeManager;
    }


    /**
     * c端注册
     * @param registerBody 参数
     * @return
     */
    @PostMapping("/register")
    public AjaxResult register(@Valid @RequestBody CustomerRegisterBody registerBody) {
        AjaxResult ajaxResult = AjaxResult.success("注册成功",userService.register(registerBody));
        return ajaxResult;
    }

    /**
     * 发送邮箱验证码
     * @param email
     * @return
     */
    @PostMapping("/sendEmailCode")
    public AjaxResult sendEmailCode(@RequestParam String email) {
        AjaxResult ajaxResult = AjaxResult.success(verificationCodeManager.sendEmailCode(email));
        return ajaxResult;
    }

    /**
     * 后台注册
     * @param user 参数
     * @return
     */
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("/adminRegister")
    public AjaxResult adminRegister(@RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success("注册成功",userService.adminRegister(user));
        return ajaxResult;
    }

    /**
     * 加载C端用户
     * @param pageDomain 参数
     * @return 结果
     */
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('admin')")
    public AjaxResult ListUser(@RequestBody PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success(userService.ListUser(pageDomain));
        return ajaxResult;
    }

    /**
     * 加载好友列表
     * @param pageDomain 参数
     * @return 结果
     */
    @PostMapping("/haoyoulist")
    public AjaxResult haoyouListUser(@RequestBody PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success(userService.HaoyouListUser(pageDomain));
        return ajaxResult;
    }

    /**
     * 加载后台用户
     * @param pageDomain 参数
     * @return 结果
     */
    @PostMapping("/adminList")
    @PreAuthorize("hasAnyAuthority('admin')")
    public AjaxResult AdminListUser(@RequestBody PageDomain pageDomain) {
        AjaxResult ajaxResult = AjaxResult.success(userService.AdminListUser(pageDomain));
        return ajaxResult;
    }

    /**
     * 登陆
     * @param loginBody 参数
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@Valid  @RequestBody LoginBody loginBody) {
        String token = jwtAuthService.login(loginBody.getUsername(), loginBody.getPassword());
        AjaxResult ajaxResult = AjaxResult.success("登录成功",token);
        return ajaxResult;
    }

    /**
     * 修改用户信息，密码，手机号，头像
     * @param user 用户
     * @return 结果
     */
    @PutMapping
    public AjaxResult updateUser(@Valid @RequestBody User user) {
        AjaxResult ajaxResult = AjaxResult.success(userService.updateUser(user));
        return ajaxResult;
    }

    /**
     * 修改用户信息，密码，手机号，头像
     * @param user 用户
     * @return 结果
     */
    @PutMapping("/admin")
    @PreAuthorize("hasAnyAuthority('admin')")
    public AjaxResult adminUpdateUser(@Valid @RequestBody User user) {
        System.out.println(user);
        AjaxResult ajaxResult = AjaxResult.success(userService.adminUpdateUser(user));
        return ajaxResult;
    }

    /**
     * 获取当前用户登陆的信息
     * @return 结果
     */
    @PostMapping("/info")
    public AjaxResult getUserInfo() {
        AjaxResult ajaxResult = AjaxResult.success(userService.getUserInfo());
        return ajaxResult;
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin')")
    public AjaxResult deletedUser(@PathVariable Long id) {
        AjaxResult ajaxResult = AjaxResult.success(userService.deletedUser(id));
        return ajaxResult;
    }

    /**
     * 修改密码
     * @param updatePwdReq 参数
     * @return 结果
     */
    @PutMapping("/updatePwdReq")
    public AjaxResult updatePwdReq(@Valid  @RequestBody UpdatePwdReq updatePwdReq) {
        AjaxResult ajaxResult = AjaxResult.success("修改密码成功",userService.updatePwdReq(updatePwdReq));
        return ajaxResult;
    }

    /**
     * 换绑邮箱
     * @param changeToEmailBody 参数
     * @return 结果
     */
    @PostMapping("/changeToEmail")
    public AjaxResult changeToEmail(@Valid @RequestBody ChangeToEmailBody changeToEmailBody) {
        AjaxResult ajaxResult = AjaxResult.success("换绑邮箱成功",userService.changeToEmail(changeToEmailBody));
        return ajaxResult;
    }

    /**
     * 修该密码找回
     * @param body 参数
     * @return 结果
     */
    @PostMapping("/retrievePassword")
    public AjaxResult retrievePassword(@Valid @RequestBody RetrievePasswordBody body) {
        AjaxResult ajaxResult = AjaxResult.success("找回密码成功",userService.retrievePassword(body));
        return ajaxResult;
    }
}
