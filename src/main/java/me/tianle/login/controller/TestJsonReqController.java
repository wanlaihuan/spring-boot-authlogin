package me.tianle.login.controller;

import me.tianle.login.netbean.ReqUser;
import me.tianle.login.netbean.RespUser;
import me.tianle.login.resp.RespCode;
import me.tianle.login.resp.RespEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 用来测试调试用的类
 */
@Controller
@RequestMapping("/test")
public class TestJsonReqController {

    /**
     * 调试 json 入参格式的请求
     * @param reqUser
     * @return
     */
    @RequestMapping(value = "/json", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody                // TODO: 将以 json 格式返回数据
    public RespEntity login(@RequestBody @Valid ReqUser reqUser) {    // TODO: RequestBody : 将以 json 格式传入数据

        RespUser respUser = new RespUser();
        if (reqUser != null) {
            respUser.setName(reqUser.getUser_name());
            respUser.setPassword(reqUser.getPassword());
        }

        return new RespEntity(RespCode.SUCCESS, respUser);    // 返回的响应实体具体看下节
    }
}
