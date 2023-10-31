import java.util.ArrayList;
import java.io.*;

public class Driver{

    public static void main(String[] args) {

        //Read in bool.bin, a serialized list of booleans equal to ingredient list length
        String boolPath = "bool.bin";
        ArrayList<Boolean> boolArr = new ArrayList<Boolean>();

        try (FileInputStream fileInputStream = new FileInputStream(boolPath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            boolArr = (ArrayList<Boolean>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Read default ingredients in
        String filePath = "defaultIngredients.bin";
        ArrayList<Ingredient> defaultIngredientsList = new ArrayList<Ingredient>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            defaultIngredientsList = (ArrayList<Ingredient>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //verifies both lists are same length
        if( !(defaultIngredientsList.size() == boolArr.size()) ){
          System.out.println("Invalid size arrays - quit");
          System.exit(0);
        }

        //iterates through list
        System.out.println("INGREDIENT LIST BEFORE");
        String name;
        boolean bool;
        for(int i = 0; i < boolArr.size(); i++){
          name = defaultIngredientsList.get(i).getName();
          bool = defaultIngredientsList.get(i).isAvailable();
          System.out.println(name+ " is available: " + bool);
        }

        //modifies available attribute in ingredients list
        for(int i = 0; i < defaultIngredientsList.size(); i++){
          defaultIngredientsList.get(i).setAvailable(boolArr.get(i));
        }

        //iterates through list after modifying
        System.out.println("\nINGREDIENT LIST AFTER");
        for(int i = 0; i < boolArr.size(); i++){
          name = defaultIngredientsList.get(i).getName();
          bool = defaultIngredientsList.get(i).isAvailable();
          System.out.println(name+ " is available: " + bool);
        }

        //We can simply write the modified ingredient ArrayList to the .bin,
        //However, since this spike is only for demonstration/research,
        //I will skip this step.
    }
}
