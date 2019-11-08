import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
	// CSS Parameters;
	// Classes
	private final String MAIN_TEXT;
	private final String LOG_IN_LABEL;
	private final String LOG_IN_TEXT;
			
	// I.D.'s
	private final String LEFT_LAYOUT;
	private final String CENTER_LAYOUT;
	
	MainWindow() { 
		SCENE_WINDOW_WIDTH = 1200;
		SCENE_WINDOW_HEIGHT = 500;
		
		MAIN_TEXT = "main_text";
		LOG_IN_LABEL = "log_in_label";
		LOG_IN_TEXT = "log_in_text";
		
		LEFT_LAYOUT = "left_layout";
		CENTER_LAYOUT = "center_layout";
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
		TabPane centerLayout = setCenterLayout();
		
		// Set the areas of the Border Pane
		outerLayout.setLeft(leftLayout);
		outerLayout.setCenter(centerLayout);
		
		return outerLayout;
	}
	
	// Sets the leftmost layout of the Main Window
	private GridPane setLeftLayout() {
		GridPane leftLayout = new GridPane();
		
		leftLayout.setId(LEFT_LAYOUT);
		
		// Title Setup
		Text sceneTitle = new Text("Log in");
		sceneTitle.getStyleClass().add(LOG_IN_LABEL);
		leftLayout.add(sceneTitle, 0, 0);
		
		// Account Creation Setup
		Text createAccountText = new Text("Create Account");
		createAccountText.getStyleClass().add(LOG_IN_TEXT);
		
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
		userName.getStyleClass().add(LOG_IN_LABEL);
		leftLayout.add(userName, 0, 1);
		
		TextField userTextField = new TextField();
		leftLayout.add(userTextField, 1, 1);
		
		// Password Setup
		Label passWord = new Label("Password:");
		passWord.getStyleClass().add(LOG_IN_LABEL);
		leftLayout.add(passWord, 0, 3);
		
		PasswordField passwordField = new PasswordField();
		leftLayout.add(passwordField, 1, 3);
		
		
		return leftLayout;
	}
	
	public TabPane setCenterLayout() {
		TabPane tabPane = new TabPane();
		tabPane.setId(CENTER_LAYOUT);
		
		// Tab 1 Setup
		Tab tab1 = new Tab("Booking Info");
		tab1.setClosable(false);
		
		// Tab 2 Setup
		Tab tab2 = new Tab("Departures", new Label("Departure Info"));
		tab2.setClosable(false);
		
		// Tab 3 Setup
		Tab tab3 = new Tab("Arrivals", new Label("Arrival Info"));
		tab3.setClosable(false);
		
		tabPane.getTabs().addAll(tab1, tab2, tab3);
		
		return tabPane;
	}
}
