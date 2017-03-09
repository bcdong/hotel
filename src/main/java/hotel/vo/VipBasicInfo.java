package hotel.vo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

/**
 * Created by Mr.Zero on 2017/3/8.
 */
public class VipBasicInfo {
    private String id;

    @Size(min = 1, message = "姓名不能为空")
    private String name;

    @Digits(integer = 31,fraction = 0, message = "银行卡号必须为31位以内的数字")
    private String bankId;

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

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }
}
