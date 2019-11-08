import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
	private final int SCENE_WINDOW_WIDTH;
	private final int SCENE_WINDOW_HEIGHT;
	
	MainWindow() { 
		SCENE_WINDOW_WIDTH = 1200;
		SCENE_WINDOW_HEIGHT = 500;
	}
	
	@Override // Sets and displays the primary stage
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Airline Reservation Welcome");
		BorderPane outerLayout = setOuterLayout();
		
		Scene scene = new Scene(outerLayout, SCENE_WINDOW_WIDTH, SCENE_WINDOW_HEIGHT);
		scene.getStylesheets().add("main_window_theme.css");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Sets the outer most layout of the Main Window
	public BorderPane setOuterLayout() {
		BorderPane outerLayout = new BorderPane();
		GridPane leftLayout = setLeftLayout();
		outerLayout.setLeft(leftLayout);
		
		return outerLayout;
	}
	
	// Sets the leftmost layout of the Main Window
	private GridPane setLeftLayout() {
		// CSS Parameters;
		// Classes
		final String MAIN_TEXT = "main_text";
		
		// I.D.'s
		final String LEFT_LAYOUT = "left_layout";
		
		GridPane leftLayout = new GridPane();
		
		leftLayout.setId(LEFT_LAYOUT);
		
		// Title Setup
		Text sceneTitle = new Text("Log in");
		sceneTitle.getStyleClass().add(MAIN_TEXT);
		leftLayout.add(sceneTitle, 0, 0);
		
		// Account Creation Setup
		Text createAccountText = new Text("Create Account");
		createAccountText.getStyleClass().add(MAIN_TEXT);
		
		// Link Setup
		Hyperlink link = new Hyperlink("", createAccountText);
		
		link.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Will Open an account creation window
			}
					
		});
		
		leftLayout.add(link, 1, 0);
		
		// Username Setup
		Label userName = new Label("User Name:");
		userName.getStyleClass().add(MAIN_TEXT);
		leftLayout.add(userName, 0, 1);
		
		TextField userTextField = new TextField();
		leftLayout.add(userTextField, 1, 1);
		
		// Password Setup
		Label passWord = new Label("Password:");
		passWord.getStyleClass().add(MAIN_TEXT);
		leftLayout.add(passWord, 0, 3);
		
		PasswordField passwordField = new PasswordField();
		leftLayout.add(passwordField, 1, 3);
		
		
		return leftLayout;
	}
}
