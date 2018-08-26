package me.tianle.login.controller;

import me.tianle.login.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class LoginRegController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    String result = "";

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String reg(@ModelAttribute User user) {
        String name = user.getName();
        String password = user.getPassword();

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return "账号密码不能为空！";
        }
        result = "";
        // TODO: 先查询是否存在
        StringBuilder sb = new StringBuilder("select * from t_user where username = \"");
        sb.append(name);
        sb.append("\"");
        String sql = sb.toString();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                String queryName = rs.getString("username");
                if (name.equals(queryName)) {
                    result = "对不起，该账号已被占用！";
                }
            }
        });
        if (!StringUtils.isEmpty(result)) {
            return result;
        }
        Date date = new Date();     //获取一个Date对象
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //创建一个格式化日期对象
        String regTime = simpleDateFormat.format(date);   //格式化后的时间
        jdbcTemplate.update("INSERT INTO t_user VALUES(?, ?, ?, ?, ?, ?)",
                new Object[]{null, name, password, user.getPhonenum(), user.getEmail(), regTime}); // 自增 ID ，所以第一个传 null
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            public void processRow(ResultSet rs) throws SQLException {
                String queryName = rs.getString("username");
                if (!StringUtils.isEmpty(queryName)) {
                    result = "注册成功！";
                }
            }
        });

        if (!StringUtils.isEmpty(result)) {
            return result;
        }

        result = "注册失败！";
        return result;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        String name = user.getName();
        String password = user.getPassword();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return "账号密码不能为空！";
        }
        result = "";
        jdbcTemplate.query("SELECT * FROM t_user WHERE username = ? and password = ?",
                new Object[]{name, password},
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        String queryName = rs.getString("username");
                        String queryPassword = rs.getString("password");
                        if (name.equals(queryName) && password.equals(queryPassword)) {
                            result = "登录成功！" + queryName + ":" + queryPassword;
                        }
                    }
                });
        if (StringUtils.isEmpty(result)) {
            result = "账号或密码错误！";
        }

        return result;
    }
}
