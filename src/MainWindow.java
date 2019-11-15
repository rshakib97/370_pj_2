import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import java.text.ParseException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
	private final int SCENE_WINDOW_WIDTH;
	private final int SCENE_WINDOW_HEIGHT;
	private final String[] tabText;
	// CSS Parameters;
	// Classes
	private final String LOG_IN_LABEL;
	private final String LOG_IN_TEXT;
			
	// I.D.'s
	private final String LEFT_LAYOUT;
	private final String CENTER_LAYOUT;
	
	private final String CSSFILENAME;
	
	MainWindow() throws ParseException { 
		SCENE_WINDOW_WIDTH = 1200;
		SCENE_WINDOW_HEIGHT = 500;
		tabText = new String[] {
		    "Departues",
		    "Arrivals"
		};
		
		// Classes
		LOG_IN_LABEL = "log_in_label";
		LOG_IN_TEXT = "log_in_text";
		
		// I.D.'s
		LEFT_LAYOUT = "left_layout";
		CENTER_LAYOUT = "center_layout";
		
		CSSFILENAME = "styles.css";
	}
	
	@Override // Sets and displays the primary stage
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Airline Reservation Welcome");
		BorderPane outerLayout = setOuterLayout();
		
		Scene scene = new Scene(outerLayout, SCENE_WINDOW_WIDTH, SCENE_WINDOW_HEIGHT);
		scene.getStylesheets().add(CSSFILENAME);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Sets the outer most layout of the Main Window
	private BorderPane setOuterLayout() {
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
				CustomerForm cf = new CustomerForm();
				Stage stage = new Stage();
				try {
					cf.start(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
		Button logInButton = new Button("Log in");
		logInButton.setId("log-in-button");
		leftLayout.add(logInButton, 0, 4);
		
		Separator separator = new Separator();
		leftLayout.add(separator, 0, 5);
		
		Label dateLabel = new Label("Set Booking Date:");
		dateLabel.getStyleClass().add(LOG_IN_LABEL);
		leftLayout.add(dateLabel, 0, 6);
		
		leftLayout.add(setDatePicker(), 1, 6);
		
		return leftLayout;
	}
	
	private TabPane setCenterLayout() {
		TabPane tabPane = new TabPane();
		tabPane.setId(CENTER_LAYOUT);
		
		// Set up the tabs based on the text in the tabText array.
		// If you want to add a new tab, just add a new string to the array in the constructor.
		for(int i = 0; i < tabText.length; i++) {
			Tab tab = new Tab(tabText[i]);
			tab.setClosable(false);
			tabPane.getTabs().add(tab);
		}
		
		tabPane.getTabs().get(0).setContent(bookingLayout() );;
		
		return tabPane;
	}
	
	// TODO restrict past dates
	private DatePicker setDatePicker() {
		DatePicker datePicker = new DatePicker();
		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override // Will make sure you can't make reservations for the past.
			public void handle(ActionEvent e) {
				LocalDate currentDate = LocalDate.now();
				
				System.out.print(datePicker.getValue() );
				
				System.out.println(currentDate.isBefore(datePicker.getValue() ) );
			}
			
		});
		
		return datePicker;
	}
	
	private HBox bookingLayout() {
		HBox hbox = new HBox();
		return hbox;
	}
}
