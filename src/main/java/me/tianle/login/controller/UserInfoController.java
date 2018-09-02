package me.tianle.login.controller;

import me.tianle.login.netbean.ReqUser;
import me.tianle.login.resp.RespCode;
import me.tianle.login.resp.RespEntity;
import me.tianle.login.server.AuthLoginService;
import me.tianle.login.server.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController {
    @Autowired
    private JdbcTemplate jdbcTemplate; // 数据库查询类

    /**
     * 查询用户信息
     *
     * @return
     */
    @RequestMapping(value = "/baseInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public RespEntity baseInfo(@RequestBody @Valid ReqUser reqUser) {
        if(StringUtils.isEmpty(reqUser.getUser_name())) {
            return new RespEntity(RespCode.ERROR,null);
        }
        UserInfoService userInfoService = new UserInfoService(jdbcTemplate);
        return new RespEntity(RespCode.SUCCESS, userInfoService.getUserinfo(reqUser.getUser_name()));
    }
}
