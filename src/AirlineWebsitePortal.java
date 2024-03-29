import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirlineWebsitePortal extends Application {

	@Override
	public void start(Stage stage) {
		// TODO make this window look better
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setId("webPortal");
		
		Label welcome = new Label("Welcome to the Airline Portal.\nClick any of the links below");
		welcome.setId("portal_welcome");
		root.getChildren().add(welcome);
		
		VBox airlineBox = new VBox();
		airlineBox.setAlignment(Pos.CENTER);
		airlineBox.setId("airline_box");
		
		ArrayList<Airline> airlines = DatabaseManager.getAllAirlines();
		for(int i = 0; i < airlines.size(); i++) {
			Airline a = airlines.get(i);
			String name = a.getName();
			
			Hyperlink hl = new Hyperlink("www." + name + ".com");
			hl.getStyleClass().add("airline_link");
			
			hl.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					AirlineWebsite aw = new AirlineWebsite(a);
					Stage s = new Stage();
					aw.start(s);
				}
			});
			
			airlineBox.getChildren().add(hl);
		}
		
		root.getChildren().add(airlineBox);
		
		Scene scene = new Scene(root, 500, 500);
		scene.getStylesheets().add("styles.css");
		stage.setScene(scene);
		stage.show();
	}
}
