import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreateAirportWindow extends Application {
	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(15);
		
		Label welcome = new Label("Add an Airport");
		welcome.setFont(new Font(20));
		Label message = new Label("");
		
		TextField airportForm = new TextField();
		airportForm.setPromptText("Airport Name");
		airportForm.setMaxWidth(200);
		
		Button submit = new Button("Submit");
		
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(airportForm.getText().isEmpty() ) {
					message.setTextFill(Color.RED);
					message.setText("Field is blank");
				}
				
				else {
					try {
						String name = airportForm.getText();
						DatabaseManager.createNewAirport(name);
						message.setTextFill(Color.GREEN);
						message.setText("Airport successfully created");
					}
					
					catch(Exception e) {
						message.setTextFill(Color.RED);
						message.setText("Not a valid name");
					}
				}
			}
		});
		
		VBox bottom = new VBox();
		bottom.setSpacing(5);
		bottom.setAlignment(Pos.CENTER);
		
		bottom.getChildren().addAll(submit, message);
		
		root.getChildren().addAll(welcome, airportForm, bottom);
		
		Scene s = new Scene(root, 300, 400);
		stage.setScene(s);
		stage.show();
	}
}
