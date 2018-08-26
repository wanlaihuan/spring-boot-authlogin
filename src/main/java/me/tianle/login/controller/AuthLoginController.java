package me.tianle.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class AuthLoginController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询用户信息
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public List<Map<String, Object>> userinfo(String username) {
        StringBuilder sb = new StringBuilder("select * from t_user where username = \"");
        sb.append(username);
        sb.append("\"");
        String sql = sb.toString();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }
}
