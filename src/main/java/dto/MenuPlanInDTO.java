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
    private List<DayPlan> dayPlans = new ArrayList();
    private List<Ingredient> ingredients = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public MenuPlanInDTO() {
    }

    public void addDayPlan(DayPlan dayPlan) {
        dayPlans.add(dayPlan);
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public int getMenuPlanID() {
        return menuPlanID;
    }

    public void setMenuPlanID(int menuPlanID) {
        this.menuPlanID = menuPlanID;
    }

    public List<DayPlan> getDayPlans() {
        return dayPlans;
    }

    public void setDayPlans(List<DayPlan> dayPlans) {
        this.dayPlans = dayPlans;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
