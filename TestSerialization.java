import BackEnd.Ingredients.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TestSerialization {
    private static String userHas(Ingredient in) {
        if (in.isAvailable()) {
            return "Which the user has.";
        } else return "Which the user does not have.";
    }

    public static void main(String[] args) {
        String importPath;
        boolean imported;
        File f = new File("./savedIngredients.bin");
        if(f.exists() && !f.isDirectory()) { 
            System.out.println("Saved Ingredients Array found, using it"); 
            importPath = "./savedIngredients.bin";
            imported = true;
        } else {
            System.out.println("Saved Ingredients Array not found, using default"); 
            importPath = "./BackEnd/Ingredients/defaultIngredients.bin";
            imported = false;
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

        /* 
         * Actual Logic Goes here. time to do some funky stuff for testing purposes.
        */
        if (!imported) {

            Ingredient thyme = new Ingredient("Thyme");
            ingredientsList.add(thyme);

            Ingredient carrots = ingredientsList.get(0);
            Ingredient potatoes = ingredientsList.get(2);
            Ingredient corn = ingredientsList.get(3);

            thyme.setAvailable(true);
            carrots.setAvailable(true);
            potatoes.setAvailable(true);
            corn.setAvailable(true);

            try(ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("./savedIngredients.bin"))) {
                objOut.writeObject(ingredientsList);
            }
            catch (IOException ioe) {
                System.out.println("IO Exception Thrown: " + ioe.getMessage());
            }
        } else f.delete();
    }
}
