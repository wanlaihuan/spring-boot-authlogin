package me.tianle.login.server;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class AuthLoginService {
    private JdbcTemplate jdbcTemplate; // 数据库查询类

    public AuthLoginService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    public List<Map<String, Object>> getUserinfo(String username) {
        StringBuilder sb = new StringBuilder("select * from t_user where username = \"");
        sb.append(username);
        sb.append("\"");
        String sql = sb.toString();
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        return list;
    }
}
