package Backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The `Runtime` class represents the runtime environment for the recipe management system.
 * It encapsulates the lists of ingredients and recipes, providing methods to retrieve individual ingredients and recipes.
 * The class handles the initialization of the backend by loading saved lists during its construction.
 *
 * @author Max MacNeill
 * @version 1.0.0
 */
public class Runtime {

  private ArrayList<Ingredient> ingredientList;
  private ArrayList<Recipe> recipeList;

  /**
   * Constructs a new `Runtime` object, initializing the backend by loading saved lists of ingredients and recipes.
   * If an exception occurs during initialization, the details are printed, and a stack trace is provided.
   */
  public Runtime() {
    try {
      ingredientList = Ingredient.loadSavedList();
      recipeList = Recipe.loadSavedList();
    } catch (Exception e) {
      System.out.println(e.getClass());
      System.out.println(
        "The backend failed to initialize due to the following error: \n\n" +
        e.getMessage() +
        "\n\nA stack trace will follow."
      );
      e.printStackTrace();
    }
  }

  /**
   * Get the number of recipes
   *
   * @return the number of recipes
   */
  public int ingredientCount() {
    return ingredientList.size();
  }

  /**
   * Get the number of ingredients
   *
   * @return the number of recipes
   */
  public int recipeCount() {
    return recipeList.size();
  }

  /**
   * Retrieves an individual ingredient from the ingredient list by index.
   *
   * @param i The index of the ingredient to retrieve.
   * @return A copy of the `Ingredient` object at the specified index.
   */
  public Ingredient getIngredients(int i) {
    return new Ingredient(ingredientList.get(i));
  }

  /**
   * Retrieves an individual recipe from the recipe list by index.
   *
   * @param i The index of the recipe to retrieve.
   * @return A copy of the `Recipe` object at the specified index.
   */
  public Recipe getRecipes(int i) {
    return new Recipe(recipeList.get(i));
  }

  /**
   * Saves the current runtime data to disc.
   *
   * @throws IOException if something breaks while writing the data.
   */
  private void saveRuntime() throws IOException {
    File f = new File(System.getProperty("user.dir") + "\\.RecipeBrowser");
    String[] entries = f.list();
    for (String s : entries) {
      File currentFile = new File(f.getPath(), s);
      currentFile.delete();
    }

    Ingredient.writeCurrentList(ingredientList);
    Recipe.writeCurrentList(recipeList);
  }

  /**
   * Adds a new ingredient to the runtime.
   *
   * @param in The ingredient to be added
   * @return true if the ingredient was sucessfully added, false if an ingredient with the same name already existed.
   * @throws IOException if the data fails to save to the drive.
   */
  public boolean addIngredient(String in) throws IOException {
    boolean success = true;

    for (int i = 0; i < ingredientList.size(); i++) {
      if (ingredientList.get(i).equals(in)) {
        success = false;
      }
    }

    if (success) {
      ingredientList.add(new Ingredient(in, true));
      saveRuntime();
    }

    return success;
  }

  /**
   * Adds a new recipe to the runtime.
   *
   * @param nameIn the name of the recipe.
   * @param instructionsIn the instructions to prepare the recipe.
   * @param prepTimeIn how long it takes to prepare the recipe, including ingredient prep and cooking.
   * @param servingCountIn how many people can be served by one patch of this recipe.
   * @return true if the ingredient was sucessfully added, false if an ingredient with the same name already existed.
   * @throws IOException if the data fails to save to the drive.
   */
  public boolean addRecipe(
    String nameIn,
    String instructionsIn,
    String prepTimeIn,
    int servingCountIn
  ) throws IOException {
    boolean success = true;
    for (int i = 0; i < recipeList.size(); i++) {
      if (recipeList.get(i).getName() == nameIn) {
        success = false;
      }
    }

    if (success) {
      recipeList.add(
        new Recipe(nameIn, instructionsIn, prepTimeIn, servingCountIn, false)
      );
      saveRuntime();
    }

    return success;
  }
}
