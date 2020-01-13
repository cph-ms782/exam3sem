package dto;

import entities.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author martin bøgh
 */
@Schema(name = "MenuInfoOUT")
public class DayPlanDTO implements Serializable {

    private int dayPlanID;
    private String dayPlan;

    public DayPlanDTO() {
    }

    public DayPlanDTO(int dayPlanID, String dayPlan) {
        this.dayPlanID = dayPlanID;
        this.dayPlan = dayPlan;
    }

    public int getDayPlanID() {
        return dayPlanID;
    }

    public void setDayPlanID(int dayPlanID) {
        this.dayPlanID = dayPlanID;
    }

    public String getDayPlan() {
        return dayPlan;
    }

    public void setDayPlan(String dayPlan) {
        this.dayPlan = dayPlan;
    }


    
}
