package hotel.vo;

import javax.validation.constraints.Size;

/**
 * Created by Mr.Zero on 2017/3/7.
 */
public class ManagerForm {
    @Size(min = 1, message = "姓名不能为空")
    String name;
    @Size(min = 1, message = "用户名不能为空")
    String username;
    @Size(min = 8, message = "密码至少8位")
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
