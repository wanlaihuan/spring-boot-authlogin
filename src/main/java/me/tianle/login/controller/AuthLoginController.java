package me.tianle.login.controller;

import me.tianle.login.resp.RespCode;
import me.tianle.login.resp.RespEntity;
import me.tianle.login.server.AuthLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthLoginController {
    @Autowired
    private JdbcTemplate jdbcTemplate; // 数据库查询类

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/userinfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public RespEntity userinfo(String username) {
        AuthLoginService authLoginService = new AuthLoginService(jdbcTemplate);
        return new RespEntity(RespCode.SUCCESS, authLoginService.getUserinfo(username));
    }
}
