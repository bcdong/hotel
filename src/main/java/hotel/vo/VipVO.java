package hotel.vo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
public class VipVO implements Serializable {

    private String id;

    @NotNull(message = "姓名不能为空")
    private String name;
    private double balance;
    private int level;
    private int experience;
    private int score;
    private String state;

    @Digits(integer = 31,fraction = 0, message = "银行卡号必须为31位以内的数字")
    private String bankId;

    @NotNull(message = "用户名不能为空")
    private String username;

    @Size(min = 8, max = 255, message = "密码位数必须在{min}到{max}之间")
    private String password;

    public VipVO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
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
