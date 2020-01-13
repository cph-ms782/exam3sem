package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author martin bøgh
 */
@Entity
@Table(name = "DAYPLAN")
@NamedQueries({
    @NamedQuery(name = "DayPlan.deleteAllRows", query = "DELETE from DayPlan") //    @NamedQuery(name = "DayPlan.findByStreet", query = "SELECT a FROM DayPlan a WHERE a.street = :street")
})
public class DayPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dayPlanID;

    private String recipe;

    private int dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuPlanID")
    private MenuPlan menuPlan;

    public DayPlan() {
    }

    public DayPlan(String recipe, int dayOfWeek, MenuPlan menuPlan) {
        this.recipe = recipe;
        this.dayOfWeek = dayOfWeek;
        this.menuPlan = menuPlan;
    }

    public Integer getDayPlanID() {
        return dayPlanID;
    }

    public void setDayPlanID(Integer dayPlanID) {
        this.dayPlanID = dayPlanID;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public MenuPlan getMenuPlan() {
        return menuPlan;
    }

    public void setMenuPlan(MenuPlan menuPlan) {
        this.menuPlan = menuPlan;
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
        final DayPlan other = (DayPlan) obj;
        if (this.dayOfWeek != other.dayOfWeek) {
            return false;
        }
        if (!Objects.equals(this.recipe, other.recipe)) {
            return false;
        }
        if (!Objects.equals(this.dayPlanID, other.dayPlanID)) {
            return false;
        }
        if (!Objects.equals(this.menuPlan, other.menuPlan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DayPlan{" + "dayPlanID=" + dayPlanID + ", recipe=" + recipe + ", dayOfWeek=" + dayOfWeek + ", menuPlan=" + menuPlan + '}';
    }

    
}
