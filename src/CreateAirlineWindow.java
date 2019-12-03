import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CreateAirlineWindow extends Application {

	@Override
	public void start(Stage stage) {
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(15);
		
		Label welcome = new Label("Add an Airline");
		welcome.setFont(new Font(20));
		Label message = new Label("");
		
		TextField airlineForm = new TextField();
		airlineForm.setPromptText("Airline Name");
		airlineForm.setMaxWidth(200);
		
		Button submit = new Button("Submit");
		
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(airlineForm.getText().isEmpty() ) {
					message.setTextFill(Color.RED);
					message.setText("Field is blank");
				}
				
				else {
					try {
						String name = airlineForm.getText();
						DatabaseManager.createNewAirline(name);
						message.setTextFill(Color.GREEN);
						message.setText("Airline successfully created");
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
		
		root.getChildren().addAll(welcome, airlineForm, bottom);
		
		Scene s = new Scene(root, 300, 400);
		stage.setScene(s);
		stage.show();
	}
}
