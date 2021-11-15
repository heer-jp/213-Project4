package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pizzas.Order;
import pizzas.Pizza;
import pizzas.StoreOrders;

/**
 * Class that handles input, output, and button events that open corresponding GUI.
 * @author Sai Maduri, Heer Patel
 *
 */
public class MainMenuController {

    @FXML
    private ToggleGroup tgPizzaType;
    @FXML
    private RadioButton rdbDeluxe;
    @FXML
    private RadioButton rdbHawaiian;
    @FXML
    private RadioButton rdbPepperoni;
    @FXML
    private Button btnNewOrder;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextArea outputArea;
    @FXML
    private Button btnCart;
    @FXML
    private Button btnStoreOrders;
    @FXML
    private ListView<String> lvOrders;
    @FXML
    private Button btnComplete;

    private StoreOrders storeOrders = new StoreOrders();
    private Order order;
    private int cartSize = 0;

    /**
     * Enable ability to place new pizza order to be placed.
     */
    public void enableNewOrder() {
        btnNewOrder.setDisable(false);
        btnCart.setDisable(false);
    }

    /**
     * Disable ability to place new pizza order to be placed.
     */
    public void disableNewOrder() {
        btnNewOrder.setDisable(true);
        btnCart.setDisable(true);
    }

    /**
     * Enable all pizza type buttons to be selected for a new pizza order to be placed.
     */
    public void enableNewPizza() {
        rdbDeluxe.setDisable(true);
        rdbHawaiian.setDisable(true);
        rdbPepperoni.setDisable(true);
        rdbPepperoni.setSelected(true);
        rdbPepperoni.setSelected(false);
        btnStoreOrders.setDisable(true);
        btnComplete.setDisable(true);
        ;
    }

    /**
     * Disable all pizza type buttons to be selected for a new pizza order to be placed.
     */
    public void disableNewPizza() {
        rdbDeluxe.setDisable(false);
        rdbHawaiian.setDisable(false);
        rdbPepperoni.setDisable(false);
        rdbPepperoni.setSelected(true);
        rdbPepperoni.setSelected(false);
        btnStoreOrders.setDisable(false);
        btnComplete.setDisable(false);
        ;
    }

    /**
     * Open PizzaMaker GUI for new pizza order to be made.
     * Send current user phone number and pizza type selected to PizzaMaker GUI.
     * @param event button click
     */
    @FXML
    private void newOrder(ActionEvent event) {
        if (validateOrder()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaCustomizationView.fxml"));
                BorderPane pizzaCustomizer = (BorderPane) loader.load();
                PizzaCustomizationController pizzaController = loader.getController();
                pizzaController.setMainController(this);
                Scene pizzaScene = new Scene(pizzaCustomizer, 600, 400);
                pizzaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Stage primaryStage = new Stage();
                primaryStage.setOnHidden(e -> pizzaController.exit());
                primaryStage.setTitle("Customize Your Pizza");
                enableNewPizza();
                enableNewOrder();
                outputArea.setText("");
                if (order == null) {
                    order = new Order(txtPhoneNumber.getText());
                    cartSize = 0;
                    txtPhoneNumber.setDisable(true);
                }
                primaryStage.setScene(pizzaScene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open CurrentOrder GUI to view pizza order(s) made by the current user.
     * @param event button click
     */
    @FXML
    private void viewCart(ActionEvent event) {
        if (cartSize > 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderView.fxml"));
                BorderPane currentOrder = (BorderPane) loader.load();
                CurrentOrderController cartController = loader.getController();
                cartController.setMainController(this);
                Scene cart = new Scene(currentOrder, 600, 400);
                cart.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Stage primaryStage = new Stage();
                primaryStage.setOnHidden(e -> cartController.exit());
                primaryStage.setTitle("Order");
                enableNewPizza();
                enableNewOrder();
                primaryStage.setScene(cart);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            outputArea.setText("The cart is currently empty.");
        }
    }

    /**
     * Complete user pizza order - updating the StoreOrder GUI list view.
     * @param event button click
     */
    @FXML
    private void completeOrder(ActionEvent event) {
        if (lvOrders.getSelectionModel().getSelectedItem() != null) {
            storeOrders.removeOrder(storeOrders.getOrder(lvOrders.getSelectionModel().getSelectedIndex()));
            updateLVOrders();
        } else {
            outputArea.setText("Please select an order.");
        }
    }

    /**
     * Open StoreOrders GUI to view all pizza order(s) made.
     * @param event button click
     */
    @FXML
    private void viewStoreOrders(ActionEvent event) {
        if (storeOrders.getOrders().size() > 0) {
            try {
                txtPhoneNumber.setDisable(true);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
                BorderPane storeOrders = (BorderPane) loader.load();
                StoreOrdersController storeOrdersController = loader.getController();
                storeOrdersController.setMainMenuController(this);
                Scene StoreOrder = new Scene(storeOrders, 600, 400);
                StoreOrder.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                Stage primaryStage = new Stage();
                primaryStage.setOnHidden(e -> storeOrdersController.exit());
                primaryStage.setTitle("All Store Orders");
                enableNewOrder();
                enableNewPizza();
                primaryStage.setScene(StoreOrder);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            outputArea.setText("There are currently no orders for the store.");
        }
    }

    /**
     * Checks if user entered phone number is a valid number.
     * Appropriate text commands are prompted.
     * @return true if number is valid, false if number is invalid.
     */
    private boolean validateOrder() {
        if (txtPhoneNumber.getText().trim().length() == 0) {
            outputArea.setText("Please enter a phone number.");
            return false;
        } else if (txtPhoneNumber.getText().trim().matches("[0-9]+")) {
            if (txtPhoneNumber.getText().trim().length() != 10) {
                outputArea.setText("Phone numbers must be 10 digits.");
                return false;
            }
        } else {
            outputArea.setText("Please use only digits for your phone number.");
            return false;
        }
        return true;
    }

    /**
     * Get user specified data.
     * Includes user phone number and pizza type selection.
     * @return
     */
    public String[] getUserData() {
        String phoneNumber = txtPhoneNumber.getText();
        if (rdbDeluxe.isSelected()) {
            return new String[] { phoneNumber, "DELUXE", "SMALL", "CHICKEN", "SAUSAGE", "PEPPERS", "ONIONS",
                    "OLIVES" };
        } else if (rdbHawaiian.isSelected()) {
            return new String[] { phoneNumber, "HAWAIIAN", "SMALL", "CHICKEN", "PINEAPPLE" };
        } else {
            return new String[] { phoneNumber, "PEPPERONI", "SMALL", "PEPPERONI" };
        }
    }

    /**
     * Get user pizza order(s).
     * @return pizza order(s)
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Get all created pizza order(s).
     * @return all pizza orders.
     */
    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    /**
     * Add pizza order(s) to store orders list.
     * @param order of pizza.
     */
    public void addToStoreOrders(Order order) {
        storeOrders.addOrder(order);
        clearOrder();
    }

    /**
     * Clear all orders in cart. 
     * Allow for new pizza orders to be made.
     */
    public void clearOrder() {
        btnCart.setText("Cart");
        txtPhoneNumber.setText("");
        order = null;
        cartSize = 0;
        disableNewPizza();
        txtPhoneNumber.setDisable(false);
        updateLVOrders();
    }

    /**
     * Enlarge cart size by 1 when pizza order is added. 
     */
    public void addOrderToCart() {
        cartSize++;
    }

    /**
     * Reduce cart size by 1 when pizza order is removed. 
     */
    public void removeOrderFromCart() {
        cartSize--;
    }

    /**
     * Update MainMenu GUI with updated pizza information.
     */
    private void updateLVOrders() {
        ObservableList<String> lvElem = FXCollections.observableArrayList();
        if ((storeOrders.getOrders()).size() != 0) {
            ArrayList<Order> Orders = storeOrders.getOrders();
            for (Order order : Orders) {
                lvElem.add(order.getPhoneNumber());
            }
        }
        lvOrders.setItems(lvElem);
    }

    /**
     * Update MainMenu GUI with updated pizza information.
     */
    public void updateMainMenu() {
        outputArea.setText("");
        updateLVOrders();
        disableNewPizza();
        if (cartSize == 0) {
            txtPhoneNumber.setDisable(false);
            btnCart.setDisable(true);
        } else {
            txtPhoneNumber.setDisable(true);
            btnCart.setText("Cart (" + cartSize + ")");
            btnCart.setDisable(false);
        }
    }

    /**
     * Add pizza to order list.
     * @param pizza
     */
    public void addPizzaToOrder(Pizza pizza) {
        order.addPizza(pizza);
    }

}