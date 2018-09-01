package me.tianle.login.server;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserInfoService {
    private JdbcTemplate jdbcTemplate; // 数据库查询类

    public UserInfoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     */
    public List<Map<String, Object>> getUserinfo(String username) {
        StringBuilder sb = new StringBuilder("select * from userBaseInfo where user_name = \"");
        sb.append(username);
        sb.append("\"");
        String sql = sb.toString();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        Map<String, Object> map = list.get(0);
        map.remove("password"); // 过滤掉密码、用户注册时间的数据返回
        map.remove("reg_time");
        map.remove("login_time");
        return list;
    }
}
