import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class BookingButton extends Button {
	private TableView<Flight> table;
	public BookingButton(String text, TableView<Flight> tv) {
		super(text);
		table = tv;
		
		setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Flight f = table.getSelectionModel().getSelectedItem();
				
				System.out.println(f.getDate() );
			}
		});
	}
	
	
}
