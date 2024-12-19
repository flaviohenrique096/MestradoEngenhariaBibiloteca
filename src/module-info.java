module TrabalhoFinalMestradoEngenhariaSoftware {
	
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	
	exports modelo; 
    opens modelo to javafx.base;
	
	opens controller to javafx.graphics, javafx.fxml;
}
