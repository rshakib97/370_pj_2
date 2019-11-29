import java.util.ArrayList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchAdminTable extends TableView<Reservation> {
	private int ROW_SIZE;
	
	public SearchAdminTable() {
		ROW_SIZE = 135;
		
		createTable();
	}
	
	public void createTable() {
		TableColumn<Reservation, String> firstName = new TableColumn<>("First");
		firstName.setMinWidth(ROW_SIZE);
		firstName.setCellValueFactory(new PropertyValueFactory<>("custFN"));
		
		TableColumn<Reservation, String> lastName = new TableColumn<>("Last");
		lastName.setMinWidth(ROW_SIZE);
		lastName.setCellValueFactory(new PropertyValueFactory<>("custLN"));
	
		TableColumn<Reservation, Integer> resNum = new TableColumn<>("Reservation Number");
		resNum.setMinWidth(ROW_SIZE);
		resNum.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
		
		TableColumn<Reservation, Integer> flightNum = new TableColumn<>("Flight Number");
		flightNum.setMinWidth(ROW_SIZE);
		flightNum.setCellValueFactory(new PropertyValueFactory<>("flightID"));
		
		getColumns().addAll(firstName, lastName, resNum, flightNum);
	}
	
	public void getResults(ArrayList<Reservation> reservations) {
		getItems().clear();
		
		for(int i = 0; i < reservations.size(); i++) {
			Reservation r = reservations.get(i);
			getItems().add(r);
		}
	}
}
