package application;

import java.util.ArrayList;

import pizzas.Order;
import pizzas.Pizza;
import pizzas.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class that handles output for all pizza order(s) for a specific phone number.
 * Allows completion and deletion of pizza order(s) in the CurrentOrder GUI.
 * @author Sai Maduri, Heer Patel
 *
 */
public class CurrentOrderController {

    private MainMenuController mainMenuController;
    private Order order;
    private String phoneNumber;

    @FXML
    private ListView<String> lvOrder;
    @FXML
    private Label lblPhoneNumber;
    @FXML
    private Label lblSubtotal;
    @FXML
    private Label lblTotal;
    @FXML
    private Button btnCancelOrder;

    /**
     * Sends user and pizza information to MainMenu Gui.
     * @param mainMenuController controller to be set to pass data
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.order = mainMenuController.getOrder();
        phoneNumber = order.getPhoneNumber();
        lblPhoneNumber.setText(phoneNumber);
        fillListView();
    }

    /**
     * Get selected pizza order.
     * @return currently selected pizza
     */
    public Pizza getSelectedPizza() {
        return order.getOrder().get(lvOrder.getSelectionModel().getSelectedIndex());
    }

    /**
     * Close GUI.
     */
    private void closeScene() {
        ((Stage) lblPhoneNumber.getScene().getWindow()).close();
    }

    /**
     * Update the MainMenu GUI.
     */
    @FXML
    public void exit() {
        mainMenuController.updateMainMenu();
    }

    /**
     * Show order information in list view. 
     */
    public void fillListView() {
        double total = 0;
        ArrayList<Pizza> Pizzas = order.getOrder();
        ObservableList<String> lvElem = FXCollections.observableArrayList();
        for (Pizza pizza : Pizzas) {
            total += pizza.price();
            lvElem.add(pizza.toString());
        }
        lblSubtotal.setText(String.format(" $%,.2f ", total));
        lblTotal.setText(String.format(" $%,.2f", order.getPrice()));
        lvOrder.setItems(lvElem);
    }
    
    /**
     * Cancel all orders for a specific user.
     * @param event button click
     */
    @FXML
    private void cancelOrder(ActionEvent event) {
        try {
            mainMenuController.clearOrder();
            closeScene();
        } catch (NullPointerException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("An error occured while trying to cancel the order. Please try again.");
            alert.show();
        }
    }

    /**
     * Place order(s) sending orders to StoreOrder GUI.
     * @param event button click.
     */
    @FXML
    private void placeOrder(ActionEvent event) {
        try {
            mainMenuController.addToStoreOrders(order);
            closeScene();
        } catch (NullPointerException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("An error occured while trying place the order. Please try again.");
            alert.show();
        }
    }

    /**
     * Remove selected pizza from current orders list.
     * @param event button click. 
     */
    @FXML
    private void removePizza(ActionEvent event) {
        if (lvOrder.getSelectionModel().getSelectedItem() != null) {
            ArrayList<Pizza> pizzas = order.getOrder();
            order.removePizza(pizzas.get(lvOrder.getSelectionModel().getSelectedIndex()));
            mainMenuController.removeOrderFromCart();
            if (order.getOrder().size() > 0) {
                fillListView();
            } else {
                mainMenuController.clearOrder();
                closeScene();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Please select a pizza to remove.");
            alert.show();
        }
    }
}