package me.tianle.login.controller;

import me.tianle.login.netbean.ReqUser;
import me.tianle.login.resp.RespCode;
import me.tianle.login.resp.RespEntity;
import me.tianle.login.resp.EntityJsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/system/oauth")
public class AuthRegController {

    @Autowired
    private JdbcTemplate jdbcTemplate; // 数据库查询类

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity reg(@RequestBody @Valid ReqUser reqUser) {

        String name = reqUser.getUser_name();
        String password = reqUser.getPassword();

        // TODO: 先查询是否存在
        StringBuilder sb = new StringBuilder("select * from userBaseInfo where user_name = \"");
        sb.append(name);
        sb.append("\"");
        String sql = sb.toString();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                String queryName = rs.getString("user_name");
                if (name.equals(queryName)) {
                    EntityJsonValue.with()
                            .put("reg_status", "1"); // -1: 其他异常 0：注册成功；1：用户名被占用;
                }
            }
        });
        if (EntityJsonValue.with().hasValues()) {
            return new RespEntity(RespCode.SUCCESS, EntityJsonValue.with().toJsonArrayValue());
        }

        Date date = new Date();     //获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String regTime = simpleDateFormat.format(date);   //格式化后的时间
        jdbcTemplate.update("INSERT INTO userBaseInfo VALUES(?, ?, ?, ?, ?,?,?,?,?,?,?,?)",
                new Object[]{null, name, password, reqUser.getPhone_num(), reqUser.getEmail(), "", "", "", "", "", regTime, ""}); // 自增 ID ，所以第一个传 null
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                String queryName = rs.getString("user_name");
                if (!StringUtils.isEmpty(queryName)) {
                    EntityJsonValue.with().put("reg_status", "0"); // -1: 其他异常 0：注册成功；1：用户名被占用;
                } else {
                    // 其他异常
                    EntityJsonValue.with().put("reg_status", "-1"); // -1: 其他异常 0：注册成功；1：用户名被占用;
                }
            }
        });

        return new RespEntity(RespCode.SUCCESS, EntityJsonValue.with().toJsonArrayValue());
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity login(@RequestBody @Valid ReqUser reqUser) {
        String name = reqUser.getUser_name();
        String password = reqUser.getPassword();

        jdbcTemplate.query("SELECT * FROM userBaseInfo WHERE user_name = ? and password = ?",
                new Object[]{name, password},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        String queryName = rs.getString("user_name");
                        String queryPassword = rs.getString("password");
                        if (name.equals(queryName) && password.equals(queryPassword)) {
                            EntityJsonValue.with()
                                    .put("login_status", "0"); // -1：其他错误；0: 登录成功；1：用户名或密码错误
                        }
                    }
                });

        if (!EntityJsonValue.with().hasValues()) {
            EntityJsonValue.with().put("login_status", "1"); // -1：其他错误；0: 登录成功；1：用户名或密码错误
        }

        return new RespEntity(RespCode.SUCCESS, EntityJsonValue.with().toJsonArrayValue());
    }
}
