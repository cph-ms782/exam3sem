package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author martin bøgh
 */
@Entity
@Table(name = "INGREDIENT")
@NamedQueries({
    @NamedQuery(name = "Ingredient.deleteAllRows", query = "DELETE from Ingredient")
//    @NamedQuery(name = "Ingredient.findByStreet", query = "SELECT a FROM Address a WHERE a.street = :street")
})
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientID;

    private String ingredientName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menuPlanID")
    private MenuPlan menuPlan;

    public Ingredient() {
    }

    public Ingredient(String ingredientName, MenuPlan menuPlan) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.menuPlan = menuPlan;
    }

    public Integer getIngredientID() {
        return ingredientID;
    }

    public void setIngredientID(Integer ingredientID) {
        this.ingredientID = ingredientID;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
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
        final Ingredient other = (Ingredient) obj;
        if (!Objects.equals(this.ingredientName, other.ingredientName)) {
            return false;
        }
        if (!Objects.equals(this.ingredientID, other.ingredientID)) {
            return false;
        }
        if (!Objects.equals(this.menuPlan, other.menuPlan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "ingredientID=" + ingredientID + ", ingredientName=" + ingredientName + ", menuPlan=" + menuPlan + '}';
    }
    
    
    
}
