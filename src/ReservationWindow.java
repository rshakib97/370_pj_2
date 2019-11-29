import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReservationWindow extends Application {
	private String userName;
	
	public ReservationWindow(String un) {
		userName = un;
	}
	
	public void start(Stage stage) {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.TOP_CENTER);
		
		Label welcome = new Label("Reservations");
		
		FlightTable ft = new FlightTable();
		
		CancelButton cancelButton = new CancelButton("Cancel", ft);
		
		ArrayList<Flight> reservations = DatabaseManager.getReservationsOf(userName);
		
		ft.getResults(reservations);
		
		vBox.getChildren().addAll(welcome, ft, cancelButton);
		
		Scene s = new Scene(vBox, 925, 400);
		stage.setScene(s);
		stage.setResizable(false);
		stage.show();
	}

}
