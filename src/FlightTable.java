import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FlightTable extends TableView<Flight> {
	private int ROW_SIZE;
	
	public FlightTable() {
		ROW_SIZE = 135;
		
		createTable();
	}
	
	private void createTable() {
		// PROPERTY NAMES MUST BE THE SAME AS THE VARIABLE NAMES IN THE FLIGHT
		// GETTERS MUST ALSO BE PROPERLY FORMATTED!
		
		//Airline Name column 
		TableColumn<Flight, String> nameColumn = new TableColumn<>("Airline");
		nameColumn.setMinWidth(ROW_SIZE);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("airline"));
			
		// price Column
		TableColumn<Flight, Double> pricecol = new TableColumn<>("Price");
		pricecol.setMinWidth(ROW_SIZE);
		pricecol.setCellValueFactory(new PropertyValueFactory<>("fare"));
		
		TableColumn<Flight, String> dateCol = new TableColumn<>("Date");
		dateCol.setMinWidth(ROW_SIZE);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		// Origin Column
		TableColumn<Flight, String> orgCol = new TableColumn<>("Origin");
		orgCol.setMinWidth(ROW_SIZE);
		orgCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
		
		// Destination Column
		TableColumn<Flight, String> deptCol = new TableColumn<>("Destination");
		deptCol.setMinWidth(ROW_SIZE);
		deptCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
		
		// Departure time column
		TableColumn<Flight, String> Departure_time = new TableColumn<>("Departure Time");
		Departure_time.setMinWidth(ROW_SIZE);
		Departure_time.setCellValueFactory(new PropertyValueFactory<>("deptTime"));
		
		// Arrival Time column
		TableColumn<Flight, String> Arrival_Time = new TableColumn<>("Arrival Time");
		Arrival_Time.setMinWidth(ROW_SIZE);
		Arrival_Time.setCellValueFactory(new PropertyValueFactory<>("arrTime"));
		
		// status column
		TableColumn<Flight, String> status = new TableColumn<>("Status");
		status.setMinWidth(ROW_SIZE);
		status.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
		
		getColumns().addAll(nameColumn, pricecol, dateCol, orgCol, deptCol, Departure_time, Arrival_Time, status);
	}
	
	public void getResults(ArrayList<Flight> flights) {
		getItems().clear();
		
		for(int i = 0; i < flights.size(); i++) {
			Flight f = flights.get(i);
			getItems().add(f);
		}
	}
}
