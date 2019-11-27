import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Reservations_From_Search_Engine {
	private VBox VBox;
	private FlightTable table;

	public Reservations_From_Search_Engine() {
		VBox = new VBox();
		VBox.setPrefHeight(350);
		
		table = new FlightTable();
		
		start();
	}
	
	public VBox getVBox() { return VBox; }
	public FlightTable getFlightTable() { return table; }

	public void start() {
		// Button
		Button addButton = new Button("Book");
		addButton.setOnAction(e->ConfirmedButtonClicked());
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(addButton);
	
		VBox.getChildren().addAll(table, hbox);
	}
	
	// TODO Customers should be able to reserved the clicked flight.
	public void ConfirmedButtonClicked() {
		
	}
}