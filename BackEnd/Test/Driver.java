package BackEnd.Test;

import java.util.ArrayList;
import java.io.*;
import BackEnd.*;

public class Driver{

    public static void main(String[] args) {

        //Write boolean ArrayList to .bin

    String boolPath = "bool.bin";

    boolean b1 = false;
    boolean b2 = false;
    boolean b3 = true;
    boolean b4 = false;
    boolean b5 = true;

    ArrayList<Boolean> boolArr = new ArrayList<>();

    boolArr.add(b1);
    boolArr.add(b2);
    boolArr.add(b3);
    boolArr.add(b4);
    boolArr.add(b5);

    try (FileOutputStream fileOutputStream = new FileOutputStream(boolPath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

           objectOutputStream.writeObject(boolArr);
           System.out.println("Object has been serialized and written to " + boolPath);

       } catch (IOException e) {
           e.printStackTrace();
       }



        //Read default ingredients in

        String filePath = "defaultIngredients.bin";
        ArrayList<Ingredient> defaultIngredientsList = new ArrayList<Ingredient>();

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            defaultIngredientsList = (ArrayList<Ingredient>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("UHOH!!!");
            e.printStackTrace();
        }
        System.out.println("test");
        System.out.println(defaultIngredientsList.get(0).getName());

    }
}
