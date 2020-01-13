package dto;

import entities.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin bøgh
 */
@Schema(name = "MenuInfoOUT")
public class MenuPlanOutDTO implements Serializable {

    private int menuPlanID;
    private List<String> dayPlans;

    public MenuPlanOutDTO() {
    }

    public MenuPlanOutDTO(int menuPlanID, List<String> dayPlans) {
        this.menuPlanID = menuPlanID;
        this.dayPlans = dayPlans;
    }
    
    public void addDayPlan(String dayPlan) {
        dayPlans.add(dayPlan);
    }

    public int getMenuPlanID() {
        return menuPlanID;
    }

    public void setMenuPlanID(int menuPlanID) {
        this.menuPlanID = menuPlanID;
    }

    public List<String> getDayPlans() {
        return dayPlans;
    }

    public void setDayPlans(List<String> dayPlans) {
        this.dayPlans = dayPlans;
    }
    
}
