package BackEnd.Ingredients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateDefaultList {
    public static void main(String[] args) {
        ArrayList<Ingredient> defaultIngredients = new ArrayList<Ingredient>();

        Ingredient one = new Ingredient("carrots");
        Ingredient two = new Ingredient("beans");
        Ingredient three = new Ingredient("potatoes");
        Ingredient four = new Ingredient("corn");
        Ingredient five = new Ingredient("bacon");

        defaultIngredients.add(one);
        defaultIngredients.add(two);
        defaultIngredients.add(three);
        defaultIngredients.add(four);
        defaultIngredients.add(five);

        try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("./BackEnd/Ingredients/defaultIngredients.bin"))) {
            objOut.writeObject(defaultIngredients);
        }
        catch (IOException ioe) {
            System.out.println("IO Exception Thrown: " + ioe.getMessage());
        }
    }
}
