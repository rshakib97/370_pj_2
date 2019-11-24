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
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerForm extends Application {
	public int Counter_To_Check_If_All_Fields_Are_Valid = 0;

	public void Invalid_Password(Stage stage) {

		// set title for the stage
		stage.setTitle("Invalid");

		// create a tile pane
		TilePane tilepane = new TilePane();

		// create a label
		Label label = new Label(" Please Enter A Valid Password!");
		label.centerShapeProperty();

		// set size of label
		label.setMinWidth(50);
		label.setMinHeight(50);

		// tilepane.getChildren().add(button);
		tilepane.getChildren().add(label);
		// create a scene
		Scene scene = new Scene(tilepane, 200, 100);

		// set the scene
		stage.setScene(scene);

		stage.show();
	}

	public void Last_Name_Missing(Stage stage) {

		// set title for the stage
		stage.setTitle("Invalid");

		// create a tile pane
		TilePane tilepane = new TilePane();

		// create a label
		Label label = new Label(" Please Enter A Valid Last Name !");
		label.centerShapeProperty();

		// set size of label
		label.setMinWidth(50);
		label.setMinHeight(50);

		// tilepane.getChildren().add(button);
		tilepane.getChildren().add(label);
		// create a scene
		Scene scene = new Scene(tilepane, 200, 100);

		// set the scene
		stage.setScene(scene);

		stage.show();
	}

	public void First_Name_Missing(Stage stage) {

		// set title for the stage
		stage.setTitle("Invalid");

		// create a tile pane
		TilePane tilepane = new TilePane();

		// create a label
		Label label = new Label(" Please Enter A Valid First Name !");
		label.centerShapeProperty();

		// set size of label
		label.setMinWidth(50);
		label.setMinHeight(50);

		tilepane.getChildren().add(label);
		// create a scene
		Scene scene = new Scene(tilepane, 200, 100);

		// set the scene
		stage.setScene(scene);

		stage.show();
	}

	public void User_Name_Missing(Stage stage) {

		// set title for the stage
		stage.setTitle("Invalid");

		// create a tile pane
		TilePane tilepane = new TilePane();

		// create a label
		Label label = new Label(" Please Enter A Valid Username!");
		label.centerShapeProperty();

		// set size of label
		label.setMinWidth(100);
		label.setMinHeight(50);

		tilepane.getChildren().add(label);
		// create a scene
		Scene scene = new Scene(tilepane, 200, 100);

		// set the scene
		stage.setScene(scene);

		stage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
// TODO Auto-generated method stub
		final Label message = new Label("");
		primaryStage.setTitle("New Customer Form");

		Label FirstName_Label = new Label("First Name");
		TextField firstname_TextField = new TextField();

		Label LastName_Label = new Label("Last Name");
		TextField lastname_TextField = new TextField();

		Label User_Name_Label = new Label("User Name");
		TextField User_Name_TextField = new TextField();

		Label First_Password_Label = new Label("Enter a password, 6 characters minimum ");
		PasswordField First_Password_TextField = new PasswordField();

		PasswordField Second_Password_Label = new PasswordField();
		Label Second_Password_TextField = new Label("Re-Enter password, 6 characters minimum");

		Button btn = new Button("submit");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
// TODO Auto-generated method stub
				Counter_To_Check_If_All_Fields_Are_Valid = 0;
//	System.out.println("Entered text is " + firstname_TextField.getText());
				if (firstname_TextField.getLength() == 0) {
					Stage pop_up = new Stage();
					First_Name_Missing(pop_up);

				} else {
					Counter_To_Check_If_All_Fields_Are_Valid++;
				}
//	System.out.println("Entered text is " + lastname_TextField.getText());
				if (lastname_TextField.getLength() == 0) {
					Stage pop_up = new Stage();
					Last_Name_Missing(pop_up);

				} else {
					Counter_To_Check_If_All_Fields_Are_Valid++;
				}
//System.out.println("Entered text is " + User_Name_TextField.getText());
				if (User_Name_TextField.getLength() == 0) {
					message.setText("Your password is incorrect!");
					Stage pop_up = new Stage();
					User_Name_Missing(pop_up);

				} else {
					Counter_To_Check_If_All_Fields_Are_Valid++;
				}

				if (!(First_Password_TextField.equals(Second_Password_Label))
						&& (First_Password_TextField.getLength() < 6)) {
					Stage pop_up = new Stage();
					Invalid_Password(pop_up);

				} else {
					Counter_To_Check_If_All_Fields_Are_Valid++;
				}
				if (Counter_To_Check_If_All_Fields_Are_Valid == 4) { // if all fields were filled out correct
					System.out.println("it worked, fill in the data base!"); // then write to the database from all text
																				// fields
					Counter_To_Check_If_All_Fields_Are_Valid = 0;
					primaryStage.close();
				}

				DatabaseManager.insertCustomer(firstname_TextField.getText(), lastname_TextField.getText(),
						User_Name_TextField.getText(), First_Password_TextField.getText());

//User_Name_TextField.clear();

			}
		});
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10));

		VBox paneCenter = new VBox();
		paneCenter.setSpacing(5);

		pane.setLeft(paneCenter);
		paneCenter.getChildren().add(FirstName_Label);
		paneCenter.getChildren().add(firstname_TextField);
		paneCenter.getChildren().add(LastName_Label);
		paneCenter.getChildren().add(lastname_TextField);
		paneCenter.getChildren().add(User_Name_Label);
		paneCenter.getChildren().add(User_Name_TextField);
		paneCenter.getChildren().add(First_Password_Label);
		paneCenter.getChildren().add(First_Password_TextField);
		paneCenter.getChildren().add(Second_Password_TextField);
		paneCenter.getChildren().add(Second_Password_Label);

		paneCenter.getChildren().add(btn);
		Scene scene = new Scene(pane, 600, 450);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}