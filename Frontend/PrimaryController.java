package Frontend;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Backend.Ingredient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/*
 * This is a controller class for the primary screen.
 * 
 * @author Miguel Daigle Gould
 */

public class PrimaryController implements Initializable{
	@FXML
	private ListView<Ingredient> ingredientsListView;
	
	@FXML
	private ListView<Ingredient> selectedListView;
	
	private ArrayList<Ingredient> ingredientArray = new ArrayList<Ingredient>();
	private Ingredient currentIngredient;

	/*
	 * Method runs upon instantiation. Loads the ListView with data and provides functionality
	 * for selecting ingredients.
	 */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ingredientArray = Ingredient.loadSavedList();
		}
		catch(IOException e) {
			
		}
		ingredientsListView.getItems().addAll(ingredientArray);
		
		//Adds listener to Available Ingredients list, then adds selected ingredients to Selected list
		
		ingredientsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ingredient>() {

			@Override
			public void changed(ObservableValue<? extends Ingredient> arg0, Ingredient arg1, Ingredient arg2) {
				currentIngredient = ingredientsListView.getSelectionModel().getSelectedItem();
				
				if(! selectedListView.getItems().contains(currentIngredient)) {
					selectedListView.getItems().add(currentIngredient);	
				}
			}
			
		});
	}

	
	@FXML
    void addIngredientAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addIngredient.fxml"));
            Parent root1 = loader.load();
         
            Stage stage1 = new Stage();
            Scene scene1 = new Scene(root1);
            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            System.out.println("Cannot load new window: " + e.getMessage());
        }
    }

}
