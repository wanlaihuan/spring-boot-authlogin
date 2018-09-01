package me.tianle.login.netbean;

import javax.validation.constraints.NotBlank;

public class ReqUser {
    private String user_name;
    private String password;
    private String phone_num;
    private String email;

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_name() {
        return user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotBlank(message = "密码不能为空")
    public String getPassword() {
        return password;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
    public String getPhone_num() {
        return phone_num;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
