package application;

import java.io.File;
import java.util.ArrayList;

import pizzas.Order;
import pizzas.StoreOrders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Class that handles output for all pizza order(s) in the StoreOrders GUI.
 * Allows all pizza order(s) to be cancelled or exported into a local file.
 * @author Sai Maduri, Heer Patel
 *
 */
public class StoreOrdersController {

    private MainMenuController mainMenuController;
    private StoreOrders storeOrders;

    @FXML
    private ListView<String> lvOrders;

    /**
     * Sends user and pizza information to MainMenu Gui.
     * @param mainMenuController
     */
    public void setMainMenuController(MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.storeOrders = mainMenuController.getStoreOrders();
        updateLVOrders();
    }

    /**
     * Close StoreOrders GUI and update MainMenu GUI.
     */
    @FXML
    public void exit() {
        mainMenuController.updateMainMenu();
    }

    /**
     * Update StoreOrders GUI list view.
     */
    private void updateLVOrders() {
        ObservableList<String> lvElem = FXCollections.observableArrayList();
        if ((storeOrders.getOrders()).size() != 0) {
            ArrayList<Order> Orders = storeOrders.getOrders();
            for (Order order : Orders) {
                lvElem.add(order.toString());
            }
        }
        lvOrders.setItems(lvElem);
    }

    /**
     * Cancel all pizza orders.
     * @param event button click
     */
    @FXML
    private void cancelOrder(ActionEvent event) {
        if (lvOrders.getSelectionModel().getSelectedItem() != null) {
            storeOrders.removeOrder(storeOrders.getOrder(lvOrders.getSelectionModel().getSelectedIndex()));
            updateLVOrders();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Please select an order to be cancelled.");
            alert.show();
        }
    }

    /**
     * Export the store orders to a file chosen by user.
     * Appropriate Alert cases shown based on export success
     */
    @FXML
    private void export() {
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                    new ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File file = chooser.showSaveDialog(stage);
            Alert alert = new Alert(AlertType.INFORMATION);
            if (storeOrders.export(file)) {
                alert.setContentText("Exported successfully.");
            } else {
                alert.setContentText("An error occured when exporting the file.");
            }
            alert.show();
        } catch (Exception e) {

        }
    }
}