import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AirlineWebsite extends Application {
	private Airline airline;
	private BookStatus bookStatus;
	
	public AirlineWebsite(Airline a) {
		airline = a;
		bookStatus = BookStatus.AIR;
	}
	
	@Override
	public void start(Stage stage)  {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.TOP_CENTER);
		
		Label welcome = new Label("Welcome to " + airline.getName() );
		
		FlightTable ft = new FlightTable();
		
		ArrayList<Flight> flights = DatabaseManager.getFlightsFromAirline(airline.getName() );
		ft.getResults(flights);
		
		BookingButton bookButton = new BookingButton("Book", ft, bookStatus);
		
		vBox.getChildren().addAll(welcome, ft, bookButton);
		
		Scene s = new Scene(vBox, 1050, 400);
		stage.setScene(s);
		stage.setResizable(false);
		stage.show();
	}
	
}
