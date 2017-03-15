package hotel.vo;

import javax.validation.constraints.Size;

/**
 * Created by Mr.Zero on 2017/3/15.
 */
public class RegisterForm {
    //vip或者(manager)管理员
    private String userType;

    @Size(min = 1, message = "用户名不能为空")
    private String username;

    @Size(min = 1, message = "密码不能为空")
    private String password;

    private String name;

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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
