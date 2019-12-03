import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FlightAdminWindow extends Application {
	ListView<String> lv;
	private FlightTable ft;
	private TextField fareField;
	private Label messageLabel;
	
	// Reference to the value that was clicked so the table can "refresh"
	String tableValue;

	@Override
	public void start(Stage stage) {
		lv = new ListView<String>();
		lv.setPrefSize(120, 150);
		messageLabel = new Label("");
		
		ArrayList<Airline> airlines = DatabaseManager.getAllAirlines();
		
		for(int i = 0; i < airlines.size(); i++) { lv.getItems().add(airlines.get(i).getName() ); }
		
		HBox airlineList = new HBox(lv);
		airlineList.setSpacing(10);
		
		HBox selection = new HBox(airlineList);
		selection.setSpacing(10);
		
		ft = new FlightTable();
		
		Button addFlight = setAddFlightButton();
		Button cancelFlight = setCancelFlightButton();
		Button modifyFare = setModifyFareButton();
		
		fareField = new TextField();
		fareField.setPromptText("New Fare");
		fareField.setMaxWidth(100);
		fareField.setFont(new Font(12) );
		
		HBox hBox = new HBox(addFlight, cancelFlight, modifyFare, fareField, messageLabel);
		hBox.setSpacing(5);
		
		VBox root = new VBox(selection, ft, hBox);
		root.setSpacing(5);
		
		root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
		
		lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				tableValue = newValue;
				ArrayList<Flight> flights = DatabaseManager.getAllFlightsFromAirline(newValue);
				ft.getResults(flights);
			}
		});
		
		Scene s = new Scene(root, 1250, 500);
		stage.setScene(s);
		stage.show();
	}
	
	public Button setAddFlightButton() {
		Button b = new Button("Add Flight");
		
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String airline = lv.getSelectionModel().getSelectedItem();
				if(airline != null) {	
					FlightForm ff = new FlightForm(airline);
					Stage s = new Stage();
					ff.start(s);
				}  
				
				else {
					messageLabel.setTextFill(Color.RED);
					messageLabel.setText("Please select an airline to create a new flight");
				}
			}
		});
		
		return b;
	}
	
	public Button setModifyFareButton() {
		Button b = new Button("Modify Fare");
		
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Flight f = ft.getSelectionModel().getSelectedItem();
				if(f != null) { 
					if(!fareField.getText().isEmpty() ) {
						messageLabel.setTextFill(Color.GREEN);
						messageLabel.setText("Fare changed successfully!");
						
						try {
							double fare = Double.parseDouble(fareField.getText() );
							DatabaseManager.modifyFare(f, fare);
							ArrayList<Flight> flights = DatabaseManager.getAllFlightsFromAirline(tableValue);
							ft.getResults(flights);
						}
						
						catch(Exception e) {
							messageLabel.setTextFill(Color.RED);
							messageLabel.setText("Invalid Value");
						}
					}
				}
			}
		});
		
		return b;
	}
	
	public Button setCancelFlightButton() {
		Button b = new Button("Cancel Flight");
		
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				messageLabel.setTextFill(Color.GREEN);
				messageLabel.setText("Flight successfully cancelled!");
				Flight f = ft.getSelectionModel().getSelectedItem();
				if(f != null) { DatabaseManager.cancelFlight(f.getFlightID() ); }
				ArrayList<Flight> flights = DatabaseManager.getAllFlightsFromAirline(tableValue);
				ft.getResults(flights);
			}
		});
		
		return b;
	}
}
