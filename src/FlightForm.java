import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FlightForm extends Application {
	private String airline;
	private Label messageLabel;
	
	private double textFieldSize, textFieldSpacing;
	
	public FlightForm(String a) {
		airline = a;
		textFieldSize = 200;
		textFieldSpacing = 15;
	}

	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		root.setSpacing(10);
		root.setAlignment(Pos.TOP_CENTER);
		
		messageLabel = new Label("");
		
		Label head = new Label("Add a Flight");
		head.setFont(new Font(20));
		root.getChildren().add(head);
		
		Label origin = new Label("Origin Airport: ");
		Label destination = new Label("Destination Airport: ");
		
		ComboBox<String> airportOrgs = setAirportsList();
		ComboBox<String> airportDests = setAirportsList();
		
		
		HBox airportOrigin = new HBox(origin, airportOrgs);
		airportOrigin.setSpacing(5);
		airportOrigin.setAlignment(Pos.CENTER);
		
		HBox airportDestination = new HBox(destination, airportDests);
		airportDestination.setSpacing(5);
		airportDestination.setAlignment(Pos.CENTER);
		
		DatePicker date = new DatePicker();
		
		TextField depart = new TextField();
		depart.setPromptText("Departure Time");
		depart.setMaxWidth(textFieldSize);
		
		TextField arrival = new TextField();
		arrival.setPromptText("Arrival Time");
		arrival.setMaxWidth(textFieldSize);
		
		TextField maxSeats = new TextField();
		maxSeats.setPromptText("Maximum Seats");
		maxSeats.setMaxWidth(textFieldSize);
		
		TextField price = new TextField();
		price.setPromptText("Price");
		price.setMaxWidth(textFieldSize);
		
		VBox forms = new VBox();
		forms.setSpacing(textFieldSpacing);
		forms.setAlignment(Pos.CENTER);
		forms.getChildren().addAll(date, depart, arrival, maxSeats, price);
		
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(5);
		
		Button submit = new Button("Submit");
		
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String origin = airportOrgs.getSelectionModel().getSelectedItem();
				String dest = airportDests.getSelectionModel().getSelectedItem();
				LocalDate d = date.getValue();
				
				if(origin.equals(dest)) { 
					messageLabel.setTextFill(Color.RED);
					messageLabel.setText("Origin and destination can not be the same");
				}
				
				else if(d == null) {
					messageLabel.setTextFill(Color.RED);
					messageLabel.setText("Please enter a date");
				}
				
				else {
					try {
						int max = Integer.parseInt(maxSeats.getText() );
						
						String deptTime = depart.getText();
						String arrTime = arrival.getText();
						
						Double fare = Double.parseDouble(price.getText() );
						
						DatabaseManager.addFlight(max, d, deptTime, arrTime, origin, dest, airline, fare);
						
						messageLabel.setTextFill(Color.GREEN);
						messageLabel.setText("Flight creation successful!");
					}
					
					
					catch(Exception e) {
						messageLabel.setTextFill(Color.RED);
						messageLabel.setText("Flight creation failed");
					}
				}
			}
		});
		
		Button close = new Button("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		
		bottom.getChildren().addAll(submit, close, messageLabel);
		root.getChildren().addAll(airportOrigin, airportDestination, forms, bottom);
		
		Scene s = new Scene(root, 500, 500);
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
	
