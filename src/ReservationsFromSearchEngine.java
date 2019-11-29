import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReservationsFromSearchEngine {
	private VBox VBox;
	private FlightTable table;
	private BookStatus bookStat;

	public ReservationsFromSearchEngine() {
		VBox = new VBox();
		VBox.setPrefHeight(350);
		
		table = new FlightTable();
		
		bookStat = BookStatus.SE;
		
		start();
	}
	
	public VBox getVBox() { return VBox; }
	public FlightTable getFlightTable() { return table; }

	public void start() {
		// Button
		Button bookButton = new BookingButton("Book", table, bookStat);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(bookButton);
	
		VBox.getChildren().addAll(table, hbox);
	}
}