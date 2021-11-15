package application;

import java.util.ArrayList;

import pizzas.Pizza;
import pizzas.PizzaMaker;
import pizzas.Size;
import pizzas.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Class that handles user's pizza selections in creating a pizza order in PizzaCustomization GUI.
 * @author Sai Maduri, Heer Patel
 *
 */
public class PizzaCustomizationController {

    private String[] userData;
    private MainMenuController mainMenuController;
    private CurrentOrderController currentOrderController;
    private String pizzaType;

    @FXML
    private Label lblPhoneNumber;
    @FXML
    private CheckBox cbPepperoni;
    @FXML
    private CheckBox cbExtraCheese;
    @FXML
    private CheckBox cbBacon;
    @FXML
    private CheckBox cbJalapenos;
    @FXML
    private CheckBox cbOnions;
    @FXML
    private CheckBox cbChicken;
    @FXML
    private CheckBox cbSausage;
    @FXML
    private CheckBox cbPeppers;
    @FXML
    private CheckBox cbOlives;
    @FXML
    private CheckBox cbMushrooms;
    @FXML
    private CheckBox cbPineapple;
    @FXML
    private ComboBox<String> cbSize;
    @FXML
    private Label subTotal;
    @FXML
    private StackPane spToppings;
    @FXML
    private ImageView pizza;
    @FXML
    private ImageView pepperoni;
    @FXML
    private ImageView extraCheese;
    @FXML
    private ImageView bacon;
    @FXML
    private ImageView jalapenos;
    @FXML
    private ImageView onions;
    @FXML
    private ImageView chicken;
    @FXML
    private ImageView sausage;
    @FXML
    private ImageView peppers;
    @FXML
    private ImageView olives;
    @FXML
    private ImageView mushrooms;
    @FXML
    private ImageView pineapple;

    private int toppingsCount = 0;
    private ArrayList<Topping> toppings = new ArrayList<Topping>();
    private Pizza tmpPricePizza;
    private Pizza tmpNewPizza;

    private double SMALL_SCALE = .6;
    private double MEDIUM_SCALE = .8;
    private double LARGE_SCALE = 1;

    /**
     * Sends user and pizza information to MainMenu GUI.
     * @param mainMenuController 
     */
    public void setMainController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.userData = (String[]) mainMenuController.getUserData();
        lblPhoneNumber.setText(userData[0]);
        this.pizzaType = userData[1];
        setDefaultTopppings();
        displaySubtotal();
    }

    /**
     * Sends user and pizza information to CurrentOrder GUI.
     * @param controller
     */
    public void setCurrentOrderController(CurrentOrderController controller) {
        tmpNewPizza = currentOrderController.getSelectedPizza();
        this.currentOrderController = controller;
        this.userData = (String[]) currentOrderController.getUserData();
        lblPhoneNumber.setText(userData[0]);
        this.pizzaType = userData[1];
        setDefaultTopppings();
        displaySubtotal();
    }

    /**
     * Add pizza order to cart in MainMenu GUI.
     * @param event button click
     */
    @FXML
    public void addToOrder(ActionEvent event) {
        mainMenuController.addPizzaToOrder(tmpPricePizza);
        if (currentOrderController != null) {
            mainMenuController.getOrder().removePizza(tmpNewPizza);
            currentOrderController.fillListView();
        } else {
            mainMenuController.addOrderToCart();
        }
        Stage stage = (Stage) lblPhoneNumber.getScene().getWindow();
        stage.close();
    }

    /**
     * Update pizza total and image dependent on pizza topping(s) selection.
     * @param event button click
     */
    @FXML
    public void updateToppings(ActionEvent event) {
        CheckBox topping = (CheckBox) event.getSource();
        if (!topping.isSelected()) {
            if (toppingsCount > 0) {
                toppingsCount -= 1;
                toppings.remove(getTopping(topping.getText().toUpperCase()));
                setImage(getTopping(topping.getText().toUpperCase()), false);
            }
        } else {
            if (toppingsCount >= Pizza.MAX_TOPPINGS_COUNT) {
                topping.setSelected(false);
            } else {
                toppings.add(getTopping(topping.getText().toUpperCase()));
                setImage(getTopping(topping.getText().toUpperCase()), true);
                toppingsCount += 1;
            }
        }
        displaySubtotal();
    }

    /**
     * Update pizza total and image dependent on pizza size selection.
     * @param event button click
     */
    @FXML
    public void updateSize(ActionEvent event) {
        displaySubtotal();
        setImageSize();
    }

    /**
     * Change pizza image scale dependent on pizza size selection.
     */
    public void setImageSize() {
        double imageScale = 0;
        switch (cbSize.getSelectionModel().getSelectedItem()) {
            case "Small":
                imageScale = SMALL_SCALE;
                break;
            case "Medium":
                imageScale = MEDIUM_SCALE;
                break;
            case "Large":
                imageScale = LARGE_SCALE;
                break;
        }
        spToppings.setScaleX(imageScale);
        spToppings.setScaleY(imageScale);
    }

    /**
     * Close PizzaMaker GUI and update MainMenu GUI with the newly created pizza order data.
     */
    @FXML
    public void exit() {
        mainMenuController.updateMainMenu();
    }

    /**
     * Change pizza image according to user selected pizza toppings.
     * @param topping selected by user.
     * @param setTo set visibility to true 
     */
    public void setImage(Topping topping, boolean setTo) {
        switch (topping) {
            case PEPPERONI:
                pepperoni.setVisible(setTo);
                break;
            case EXTRACHEESE:
                extraCheese.setVisible(setTo);
                break;
            case BACON:
                bacon.setVisible(setTo);
                break;
            case JALAPENOS:
                jalapenos.setVisible(setTo);
                break;
            case ONIONS:
                onions.setVisible(setTo);
                break;
            case CHICKEN:
                chicken.setVisible(setTo);
                break;
            case SAUSAGE:
                sausage.setVisible(setTo);
                break;
            case PEPPERS:
                peppers.setVisible(setTo);
                break;
            case OLIVES:
                olives.setVisible(setTo);
                break;
            case MUSHROOMS:
                mushrooms.setVisible(setTo);
                break;
            case PINEAPPLE:
                pineapple.setVisible(setTo);
                break;
        }
    }

    /**
     * Set default toppings dependent on user selected pizza.
     */
    public void setDefaultTopppings() {
        ObservableList<String> sizes = FXCollections.observableArrayList("Small", "Medium", "Large");
        cbSize.setItems(sizes);
        cbSize.setValue("Small");
        setImageSize();
        if (pizzaType.equals(Pizza.DELUXE)) {
            setImage(Topping.CHICKEN, true);
            setImage(Topping.SAUSAGE, true);
            setImage(Topping.PEPPERS, true);
            setImage(Topping.ONIONS, true);
            setImage(Topping.BACON, true);
            cbChicken.setSelected(true);
            cbSausage.setSelected(true);
            cbPeppers.setSelected(true);
            cbOnions.setSelected(true);
            cbBacon.setSelected(true);
            loadToppings();
            toppingsCount = Pizza.DELUXE_TOPPINGS_COUNT;
        } else if (pizzaType.equals(Pizza.HAWAIIAN)) {
            setImage(Topping.CHICKEN, true);
            setImage(Topping.PINEAPPLE, true);
            cbChicken.setSelected(true);
            cbPineapple.setSelected(true);
            loadToppings();
            toppingsCount = Pizza.HAWAIIAN_TOPPINGS_COUNT;
        } else {
            setImage(Topping.PEPPERONI, true);
            cbPepperoni.setSelected(true);
            loadToppings();
            toppingsCount = Pizza.PEPPERONI_TOPPINGS_COUNT;
        }
    }

    /**
     * Display the subtotal of pizza order in allocated location.
     */
    public void displaySubtotal() {
        tmpPricePizza = PizzaMaker.createPizza(pizzaType, getSize(cbSize.getSelectionModel().getSelectedItem()), toppings);
        String price = String.format("%,.2f", tmpPricePizza.price());
        subTotal.setText("$" + price);
    }

    /**
     * Load the selected toppings of the pizza. 
     */
    public void loadToppings() {
        int loadToppingsStartIndex = 3;
        for (int i = loadToppingsStartIndex; i < userData.length; i++)
            toppings.add(getTopping(userData[i].toUpperCase()));
    }

    /**
     * Get pizza orders topping(s).
     * @param topping of pizza.
     * @return topping(s) of pizza.
     */
    public static Topping getTopping(String topping) {
        return Topping.valueOf(topping.toUpperCase());
    }

    /**
     * Get pizza orders size.
     * @param size of pizza.
     * @return size of pizza.
     */
    public static Size getSize(String size) {
        return Size.valueOf(size.toUpperCase());
    }

}