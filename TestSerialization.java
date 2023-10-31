import BackEnd.Ingredients.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class TestSerialization {
    private static String userHas(Ingredient in) {
        if (in.isAvailable()) {
            return "Which the user has.";
        } else return "Which the user does not have.";
    }

    public static void main(String[] args) {
        String importPath;
        File f = new File("./savedIngredients.bin");
        if(f.exists() && !f.isDirectory()) { 
            importPath = "./savedIngredients.bin";
        } else {
            System.out.println("Saved Ingredients Array not found, using default"); 
            importPath = "./BackEnd/Ingredients/defaultIngredients.bin";
        }

        ArrayList<Ingredient> ingredientsList = new ArrayList<Ingredient>(); //This will be discarded when it is set by the below code, but I needed it to get rid of some compiler uninitialized variable warnings.

        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(importPath))) {
            Object obj = objIn.readObject();
            if (obj instanceof ArrayList<?>) {
                ingredientsList = (ArrayList<Ingredient>)obj;
            } else throw new ClassCastException("defaultIngredients is not an ArrayList of Ingredients");
        }
        catch (Exception e) {
            System.out.println("Exception thrown reading serialized object: " + e.getMessage());
            System.out.println("Execution cannot continue without a valid default ingredients list. Exiting.");
            System.exit(-1);
        }

        for (int i = 0; i < ingredientsList.size(); i++) {
            System.out.println("Loaded Ingredient: " + ingredientsList.get(i).getName() + ", " + userHas(ingredientsList.get(i)));
        }
    }
}
