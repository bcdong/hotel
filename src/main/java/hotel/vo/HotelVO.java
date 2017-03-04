package hotel.vo;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/3.
 */
public class HotelVO {
    private String id;
    private String name;
    private String state;
    private List<PlanVO> plans;

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
}
