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
@Schema(name = "MenuInfoIn")
public class MenuPlanInDTO implements Serializable {

    private int menuPlanID;
    private int week;
    private List<String> dayPlans;

    public MenuPlanInDTO() {
    }

    public MenuPlanInDTO(List<String> dayPlans) {
        this.dayPlans = dayPlans;
    }
    
    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
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
