package dto;

import java.io.Serializable;

/**
 *
 * @author martin bøgh
 */
public class RecipeDTO implements Serializable {

    private String id;
    private String description;
    private String ingredients;
    private String cookingTime;
    private String preparations;

    public RecipeDTO() {
    }

    public RecipeDTO(String id, String description, String ingredients, String cookingTime, String preparations) {
        this.id = id;
        this.description = description;
        this.ingredients = ingredients;
        this.cookingTime = cookingTime;
        this.preparations = preparations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getPreparations() {
        return preparations;
    }

    public void setPreparations(String preparations) {
        this.preparations = preparations;
    }

}
