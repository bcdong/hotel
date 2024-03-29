package hotel.vo;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/3.
 */
public class HotelVO {
    private String id;

    @Size(min = 1, message = "酒店名称不能为空")
    private String name;
    private String address;
    private String state;
    private List<PlanVO> plans;
    private String managerName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<PlanVO> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanVO> plans) {
        this.plans = plans;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
