package BackEnd.Ingredients;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private boolean available;

    public Ingredient(String nameIn) {
        name = nameIn;
        available = false;
    }

    public void setAvailable(boolean in) {
        available = in;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getName() {
        return name;
    }
}