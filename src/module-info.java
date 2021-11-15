module Project4 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
