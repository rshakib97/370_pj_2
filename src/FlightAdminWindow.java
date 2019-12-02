import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightAdminWindow extends Application {
	ListView<String> lv;
	private FlightTable ft;

	@Override
	public void start(Stage stage) {
		lv = new ListView<String>();
		lv.setPrefSize(120, 150);
		
		ArrayList<Airline> airlines = DatabaseManager.getAllAirlines();
		
		for(int i = 0; i < airlines.size(); i++) { lv.getItems().add(airlines.get(i).getName() ); }
		
		HBox airlineList = new HBox(lv);
		airlineList.setSpacing(10);
		
		HBox selection = new HBox(airlineList);
		selection.setSpacing(10);
		
		ft = new FlightTable();
		
		Button addFlight = setAddFlightButton();
		Button cancelFlight = setCancelFlightButton();
		
		HBox buttons = new HBox(addFlight, cancelFlight);
		buttons.setSpacing(5);
		
		VBox root = new VBox(selection, ft, buttons);
		
		root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
		
		lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ArrayList<Flight> flights = DatabaseManager.getFlightsFromAirline(newValue);
				ft.getResults(flights);
			}
		});
		
		Scene s = new Scene(root, 1050, 500);
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
			}
		});
		
		return b;
	}
	
	public Button setCancelFlightButton() {
		Button b = new Button("Cancel Flight");
		
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Flight f = ft.getSelectionModel().getSelectedItem();
				if(f != null) { DatabaseManager.cancelFlight(f.getFlightID() ); }
			}
		});
		
		return b;
	}
}
