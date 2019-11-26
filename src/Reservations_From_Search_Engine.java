import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Reservations_From_Search_Engine {
	private TableView<Flight> table;
	private VBox VBox;
	
	private final int ROW_SIZE;

	public Reservations_From_Search_Engine() {
		VBox = new VBox();
		VBox.setPrefHeight(350);
		
		ROW_SIZE = 135;
		
		start();
	}
	
	public VBox getVBox() { return VBox; }

	public void start() {
		
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
		TableColumn<Flight, String> status = new TableColumn<>("Available");
		status.setMinWidth(ROW_SIZE);
		status.setCellValueFactory(new PropertyValueFactory<>("current_status"));
		
		// button
		Button addButton = new Button("Book");
		addButton.setOnAction(e->ConfirmedButtonClicked());
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(addButton);
	
		table = new TableView<Flight>();
		
		table.getColumns().addAll(nameColumn, pricecol, orgCol, deptCol, Departure_time, Arrival_Time, status);
		
		VBox.getChildren().addAll(table, hbox);
	}
	
	public void getResults(ArrayList<Flight> flights) {
		table.getItems().clear();
		
		for(int i = 0; i < flights.size(); i++) {
			Flight f = flights.get(i);
			table.getItems().add(f);
		}
	}
	
	// TODO Customers should be able to reserved the clicked flight.
	public void ConfirmedButtonClicked() {
		
	}
}