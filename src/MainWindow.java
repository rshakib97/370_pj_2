/* First window the user will see when the program is run, can log in, create a new account and find flights */

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.text.ParseException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
	private Stage primaryStage;
	private Scene scene;
	
	private final int SCENE_WINDOW_WIDTH;
	private final int SCENE_WINDOW_HEIGHT;
	// CSS Parameters;
	// Classes
	private final String TEXT_NODE;
	private final String HYPERLINK;
			
	// I.D.'s
	private final String LEFT_LAYOUT;
	private final String CENTER_LAYOUT;
	private final String LOG_IN_BUTTON;
	
	private final String CSSFILENAME;
	
	MainWindow() throws ParseException { 
		SCENE_WINDOW_WIDTH = 900;
		SCENE_WINDOW_HEIGHT = 400;
		
		// Classes
		TEXT_NODE = "text_node_left_layout";
		HYPERLINK = "hyperlink";
		
		// I.D.'s
		LEFT_LAYOUT = "left_layout";
		CENTER_LAYOUT = "center_layout";
		LOG_IN_BUTTON = "log_in_button";
		
		CSSFILENAME = "styles.css";
	}
	
	@Override // Sets and displays the primary stage
	public void start(Stage ps) throws Exception {
		primaryStage = ps;
		primaryStage.setTitle("Airline Reservation Welcome");
		BorderPane outerLayout = setOuterLayout();
		
		scene = new Scene(outerLayout, SCENE_WINDOW_WIDTH, SCENE_WINDOW_HEIGHT);
		scene.getStylesheets().add(CSSFILENAME);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Sets the outer most layout of the Main Window
	private BorderPane setOuterLayout() {
		BorderPane outerLayout = new BorderPane();
		GridPane leftLayout = setLeftLayout();
		GridPane centerLayout = setCenterLayout();
		
		// Set the areas of the Border Pane
		outerLayout.setLeft(leftLayout);
		outerLayout.setCenter(centerLayout);
		
		return outerLayout;
	}
	
	// Sets the leftmost layout of the Main Window
	private GridPane setLeftLayout() {
		GridPane gp = new GridPane();
		gp.setId(LEFT_LAYOUT);
	
		// Row 0
		Label userName = new Label("User Name:");
		Label passWord = new Label("Password:");
		Button logInButton = new Button("Log in");
		
		// Row 1
		TextField userTextField = new TextField();
		PasswordField passwordField = new PasswordField();
		Hyperlink link = setCreateAccountLink();
		
		// Set Classes
		Node textNodes[] = new Node[] { userName, passWord };
		for(int i = 0; i < textNodes.length; i++) { textNodes[i].getStyleClass().add(TEXT_NODE);}
		link.getStyleClass().add(HYPERLINK);
		
		// Set I.D.'s
		logInButton.setId(LOG_IN_BUTTON);
		
		Node nodesAtCol0[] = new Node[] { userName, passWord, logInButton };
		for(int i = 0; i < nodesAtCol0.length; i++) { gp.add(nodesAtCol0[i], 0, i); }
		
		Node nodesAtCol1[] = new Node[] { userTextField, passwordField, link };
		for(int i = 0; i < nodesAtCol1.length; i++) { gp.add(nodesAtCol1[i], 1, i); }
		
		return gp;
	}
	
	private GridPane setCenterLayout() {
		GridPane gp = new GridPane();
		gp.setId(CENTER_LAYOUT);
		
		Label fromLabel = new Label("From");
		Label toLabel = new Label("To");
		Label departLabel = new Label("Depart");
		Label returnLabel = new Label("Return");
		
		Label labels[] = new Label[] { fromLabel, toLabel, departLabel, returnLabel };
		for(int i = 0; i < labels.length; i++) { gp.add(labels[i], i, 0);}
		
		TextField fromField = new TextField();
		TextField toField = new TextField();
		DatePicker departDate = setDatePicker();
		DatePicker returnDate = setDatePicker();
		
		Node fields[] = new Node[] { fromField, toField, departDate, returnDate };
		for(int i = 0; i < fields.length; i++) { gp.add(fields[i], i, 1); }
		
		// TODO set classes for styling
	
		return gp;
	}
	
	private Hyperlink setCreateAccountLink() {
		// Link Setup
		Text createAccountText = new Text("Create Account");
		createAccountText.getStyleClass().add(TEXT_NODE);
		
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
		
		return link;
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
}
