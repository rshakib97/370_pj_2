import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirlineWebsite extends Application {
	private Airline airline;
	
	public AirlineWebsite(Airline a) {
		airline = a;
	}
	
	@Override
	public void start(Stage stage)  {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.TOP_CENTER);
		
		Label welcome = new Label("Welcome to " + airline.getName() );
		
		FlightTable ft = new FlightTable();
		
		ArrayList<Flight> flights = DatabaseManager.getFlightsFromAirline(airline.getName() );
		ft.getResults(flights);
		
		vBox.getChildren().addAll(welcome, ft);
		
		
		
		Scene s = new Scene(vBox, 925, 400);
		stage.setScene(s);
		stage.setResizable(false);
		stage.show();
	}
	
}
