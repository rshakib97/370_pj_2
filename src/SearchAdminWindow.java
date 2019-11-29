import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchAdminWindow extends Application {

	public SearchAdminWindow() {
		
	}

	@Override
	public void start(Stage stage) {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.TOP_CENTER);
		
		SearchAdminTable sat = new SearchAdminTable();
		
		ArrayList<Reservation> reservations = DatabaseManager.getReservationsFromSearchEngine();
		
		sat.getResults(reservations);
		
		vBox.getChildren().addAll(sat);
	
		Scene s = new Scene(vBox, 700, 400);
		stage.setScene(s);
		stage.setResizable(false);
		stage.show();
	}
}
