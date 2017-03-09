package hotel.vo;

import javax.validation.constraints.Size;

/**
 * Created by Mr.Zero on 2017/3/9.
 */
public class PasswordForm {
    @Size(min = 8, message = "密码至少8位")
    private String oldPassword;
    @Size(min = 8, message = "密码至少8位")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
