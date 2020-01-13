package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author martin bøgh
 */
@Entity
@Table(name = "MENUPLAN")
@NamedQueries({
    @NamedQuery(name = "MenuPlan.findAll", query = "SELECT m FROM MenuPlan m"),
//    @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.personID = :personID"),
    @NamedQuery(name = "MenuPlan.deleteAllRows", query = "DELETE from MenuPlan")
})
public class MenuPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuPlanID;
    
    private int week;

    @OneToMany(
            mappedBy = "menuPlan",
            cascade = CascadeType.PERSIST
    )
    private List<DayPlan> dayPlans = new ArrayList();

    @OneToMany(
            mappedBy = "menuPlan",
            cascade = CascadeType.PERSIST
    )
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(
            mappedBy = "menuPlan",
            cascade = CascadeType.PERSIST
    )
    private List<User> users = new ArrayList<>();

    public MenuPlan() {
    }

    public MenuPlan(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
    
    
    public int getMenuPlanID() {
        return menuPlanID;
    }

    public List<DayPlan> getDayPlans() {
        return dayPlans;
    }

    public void setDayPlans(List<DayPlan> dayPlans) {
        this.dayPlans = dayPlans;
    }

    public void addDayPlan(DayPlan dayPlan) {
        dayPlans.add(dayPlan);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MenuPlan other = (MenuPlan) obj;
        if (this.menuPlanID != other.menuPlanID) {
            return false;
        }
        if (this.week != other.week) {
            return false;
        }
        if (!Objects.equals(this.dayPlans, other.dayPlans)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        if (!Objects.equals(this.users, other.users)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MenuPlan{" + "menuPlanID=" + menuPlanID + ", week=" + week + ", dayPlans=" + dayPlans + ", ingredients=" + ingredients + ", users=" + users + '}';
    }


    
}
