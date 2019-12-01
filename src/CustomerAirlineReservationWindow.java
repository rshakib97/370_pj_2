import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerAirlineReservationWindow extends Application {

	@Override
	public void start(Stage stage) {
		ListView<String> lv = new ListView<String>();
		lv.setPrefSize(120, 120);
		
		ArrayList<Airline> airlines = DatabaseManager.getAllAirlines();
		
		for(int i = 0; i < airlines.size(); i++) { lv.getItems().add(airlines.get(i).getName() ); }
		
		HBox airlineList = new HBox(lv);
		airlineList.setSpacing(10);
		
		HBox selection = new HBox(airlineList);
		selection.setSpacing(10);
		
		ReservationTable rt = new ReservationTable();
		
		VBox root = new VBox(selection, rt);
		
		root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
		
		lv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ArrayList<Reservation> reservations = DatabaseManager.getReservationsFromAirline(newValue);
				rt.getResults(reservations);
			}
		});
		
		Scene s = new Scene(root, 560, 500);
		stage.setScene(s);
		stage.show();
	}
}
