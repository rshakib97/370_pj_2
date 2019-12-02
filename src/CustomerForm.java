import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CustomerForm extends Application {
	public int Counter_To_Check_If_All_Fields_Are_Valid = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
//TODO Auto-generated method stub
		final Label warningmessage = new Label("");
		warningmessage.setTextFill(Color.RED);
		primaryStage.setTitle("New Customer Form");

		Label FirstName_Label = new Label("First Name");
		TextField firstname_TextField = new TextField();

		Label LastName_Label = new Label("Last Name");
		TextField lastname_TextField = new TextField();

		Label User_Name_Label = new Label("User Name");
		TextField User_Name_TextField = new TextField();

		Label First_Password_Label = new Label("Enter a password, 6 characters minimum ");
		PasswordField First_Password_TextField = new PasswordField();

		PasswordField Second_Password_TextField = new PasswordField();
		Label Second_Password_Label = new Label("Re-Enter password, 6 characters minimum");

		Button submit = new Button("Submit");
		submit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (firstname_TextField.getText().isEmpty()) { warningmessage.setText("Invalid First Name!"); } 
				
				else if (lastname_TextField.getText().isEmpty()) { warningmessage.setText("Invalid Last Name"); } 
				
				else if (User_Name_TextField.getText().isEmpty()) { warningmessage.setText("Invalid User Name"); } 
				
				// Password must be a length greater than 6 and match both fields
				else if ((!(First_Password_TextField.getText().equals(Second_Password_TextField.getText())))
						|| First_Password_TextField.getLength() < 6 || Second_Password_TextField.getLength() < 6) {
					warningmessage.setText("Invalid Password");
				} 
				else {
					String fn = firstname_TextField.getText();
					String ln = lastname_TextField.getText();
					String un = User_Name_TextField.getText();
					String pw = First_Password_TextField.getText();
					warningmessage.setText("Success!");
					if (DatabaseManager.insertCustomer(fn, ln, un, pw) ) {
						warningmessage.setTextFill(Color.BLUE);
						warningmessage.setText("Success!");
					}
				}
			}
		});
		
		Button close = new Button("Close");
		close.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
				
			}
		
		});
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));

		VBox paneCenter = new VBox();
		paneCenter.setSpacing(5);
		
		HBox buttons = new HBox(5);
		buttons.getChildren().addAll(submit, close);

		pane.setLeft(paneCenter);
		paneCenter.getChildren().add(FirstName_Label);
		paneCenter.getChildren().add(firstname_TextField);
		paneCenter.getChildren().add(LastName_Label);
		paneCenter.getChildren().add(lastname_TextField);
		paneCenter.getChildren().add(User_Name_Label);
		paneCenter.getChildren().add(User_Name_TextField);
		paneCenter.getChildren().add(First_Password_Label);
		paneCenter.getChildren().add(First_Password_TextField);
		paneCenter.getChildren().add(Second_Password_Label);
		paneCenter.getChildren().add(Second_Password_TextField);
		paneCenter.getChildren().add(warningmessage);
		paneCenter.getChildren().add(buttons);
		Scene scene = new Scene(pane, 600, 450);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}