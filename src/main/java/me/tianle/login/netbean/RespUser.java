package me.tianle.login.netbean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RespUser {
    private String name;
    private String password;
    private String phonenum;
    private String email;

    public RespUser() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore // 用来过滤密码，使得返回不会包含密码信息
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
