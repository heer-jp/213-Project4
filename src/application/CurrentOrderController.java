package application;

import java.util.ArrayList;

import pizzas.Order;
import pizzas.Pizza;
import pizzas.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private Pizza selectedPizza;

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
     * @param mainMenuController
     */
    public void setMainController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.order = mainMenuController.getOrder();
        phoneNumber = order.getPhoneNumber();
        lblPhoneNumber.setText(phoneNumber);
        fillListView();
    }

    /**
     * Get selected pizza order.
     * @return
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
     * Close GUI and update the MainMenu GUI.
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
     * Get user information and pizza orders.
     * @return
     */
    public String[] getUserData() {
        int numToppings = selectedPizza.getToppings().size();
        String[] data = new String[numToppings + 3];
        int storeToppingsStartIndex = 3;
        ArrayList<Topping> topping = selectedPizza.getToppings();
        data[0] = order.getPhoneNumber();
        data[1] = selectedPizza.getType();
        data[2] = selectedPizza.getSize().name();
        for (int i = storeToppingsStartIndex; i < data.length; i++)
            data[i] = topping.get(i - storeToppingsStartIndex).name();
        return data;
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
            // lets add an alert here
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
            // lets add an alert here as well
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
        }
    }
}