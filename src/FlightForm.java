import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightForm extends Application {
	private String airline;
	
	public FlightForm(String a) {
		airline = a;
	}

	@Override
	public void start(Stage stage) {
		VBox form = new VBox();
		form.setSpacing(10);
		
		Label head = new Label("Add a Flight");
		form.getChildren().add(head);
		
		Label origin = new Label("Origin Airport: ");
		Label destination = new Label("Destination Airport: ");
		
		ComboBox<String> airportOrgs = setAirportsList();
		ComboBox<String> airportDests = setAirportsList();
		
		HBox airportOrigin = new HBox(origin, airportOrgs);
		HBox airportDestination = new HBox(destination, airportDests);
		
		form.getChildren().addAll(airportOrigin, airportDestination);
		
		String fieldNames[] = {"Date",
				"Departure Time", 
				"Arrival Time",
				"Maximum Seats",
				"Price" };
		
		DatePicker date = new DatePicker();
		
		TextField depart = new TextField();
		depart.setPromptText("Departure Time");
		TextField arrival = new TextField();
		arrival.setPromptText("Arrival Time");
		TextField maxSeats = new TextField();
		maxSeats.setPromptText("Maximum Seats");
		TextField price = new TextField();
		price.setPromptText("Price");
		
		form.getChildren().addAll(date, depart, arrival, maxSeats, price);
		
		Button submit = new Button("Submit");
		
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int max = Integer.parseInt(maxSeats.getText() );
				
				LocalDate d = date.getValue();
				String deptTime = depart.getText();
				String arrTime = arrival.getText();
				String origin = airportOrgs.getSelectionModel().getSelectedItem();
				String dest = airportDests.getSelectionModel().getSelectedItem();
				
				Double fare = Double.parseDouble(price.getText() );
				
				DatabaseManager.addFlight(max, d, deptTime, arrTime, origin, dest, airline, fare);
			}
		});
		
		form.getChildren().add(submit);
		
		Scene s = new Scene(form, 500, 500);
		stage.setScene(s);
		stage.show();
	}
	
	private ComboBox<String> setAirportsList() {
		ArrayList<String> airports = DatabaseManager.getAllAirports();
		
		ComboBox<String> cb = new ComboBox<String>(FXCollections.observableArrayList(airports));
		cb.getSelectionModel().selectFirst();
		
		return cb;
	}
}
	
