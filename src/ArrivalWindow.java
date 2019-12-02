import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArrivalWindow extends Application {
	@Override
    public void start(Stage stage) {
        // Create the Label
        Label airportsplz = new Label("Select an airport: ");
        Label arrLabel = new Label("Arrivals");
        
        // Create the ListView
        final ListView<String> globalAirportList = new ListView<>();
        // Add the items to the List 
        globalAirportList.getItems().addAll(DatabaseManager.getAllAirports() );
        // Set the size of the ListView
        globalAirportList.setPrefSize(120, 120);
        // Enable multiple selection
        globalAirportList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        FlightTable arrs = new FlightTable();
         
        // Update the message Label when the selected item changes
        globalAirportList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
            	ArrayList<Flight> arrivals = DatabaseManager.getArrivalsFromAirport(globalAirportList.getSelectionModel().getSelectedItem());
                arrs.getResults(arrivals);
        }});
 
        // Create the HBox for the Airports
        HBox airportsSelection_box = new HBox();
        // Set Spacing to 10 pixels
        airportsSelection_box.setSpacing(10);
        // Add the Label and the List to the HBox
        airportsSelection_box.getChildren().addAll(airportsplz, globalAirportList);
         
        // Create the Selection HBox
        HBox selection = new HBox();
        // Set Spacing to 10 pixels
        selection.setSpacing(10);
        // Add the List and the Buttons to the HBox
        selection.getChildren().addAll(airportsSelection_box);
         
        // Create the GridPane
        GridPane pane = new GridPane();
        // Set the horizontal and vertical gaps between children
        pane.setHgap(10);       
        pane.setVgap(5);        
        // Add the HBox to the GridPane at position 0
        pane.addColumn(0, selection);
         
        // Create the VBox
        VBox root = new VBox();
        // Set Spacing to 10 pixels
        root.setSpacing(10);
        // Add the GridPane and the TextArea to the VBox
        root.getChildren().addAll(pane, arrLabel, arrs);
         
        // Set the Style-properties of the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");
 
        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title
        stage.setTitle("Airports");
        // Display the Stage
        stage.show();       
	}
}
