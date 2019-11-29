import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class BookingButton extends Button {
	private TableView<Flight> table;
	private BookStatus status;
	public BookingButton(String text, TableView<Flight> tv, BookStatus s) {
		super(text);
		table = tv;
		status = s;
		
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Flight f = table.getSelectionModel().getSelectedItem();
				
				if(f != null) { DatabaseManager.bookReservation(GlobalData.getLoggedInAccount(), f.getFlightID(), status); }
			}
		});
	}
}
