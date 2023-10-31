package BackEnd.Ingredients;

public class Ingredient {
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
}