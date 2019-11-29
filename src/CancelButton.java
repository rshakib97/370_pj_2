import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class CancelButton extends Button {
	TableView<Flight> table;
	public CancelButton(String text, TableView<Flight> tv) {
		super(text);
		table= tv;
		
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Flight f = table.getSelectionModel().getSelectedItem();
				if(DatabaseManager.cancelReservation(f.getFlightID() ) ) {
					
					table.getItems().remove(f);
				}
			}
		});
	}
}
