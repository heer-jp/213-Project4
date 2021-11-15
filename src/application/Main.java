package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * A launcher class to initialize and create the MainMenu GUI.
 * @author Sai Maduri, Heer Patel
 *
 */
public class Main extends Application {
    
    /**
     *  Initializes the Main Menu scene by loading the MainMenuView.fxml file.
     */
	@Override
	public void start(Stage primaryStage) {
		try {
		    BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene mainMenuScene = new Scene(root,750,800);
			mainMenuScene.getStylesheets().add(getClass().getResource("mainMenu.css").toExternalForm());
			primaryStage.setTitle("Main Menu");
			primaryStage.setScene(mainMenuScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Calls the JavaFX Application's launch() method, which starts up the project.
     * @param args user input.
     */
	public static void main(String[] args) {
		launch(args);
	}
}
