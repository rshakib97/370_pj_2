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
import javafx.geometry.Pos;
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
	private final String TEXT_NODE_LEFT_LAYOUT;
	private final String TEXT_NODE_CENTER_LAYOUT;
	private final String HYPERLINK;
			
	// I.D.'s
	private final String LOGIN_WINDOW;
	private final String LOGGED_IN_WINDOW;
	private final String CENTER_LAYOUT;
	private final String LOG_IN_BUTTON;
	private final String LOG_OUT_BUTTON;
	private final String WARNING_LABEL;
	
	private final String CSSFILENAME;
	
	// Panes
	private BorderPane outerLayout;
	private GridPane loginWindow;
	private GridPane loggedInWindow;
	private GridPane centerLayout;
	
	// Warning Label, initialized in the setLoginWindow
	private Label warningLabel;
	
	MainWindow() throws ParseException { 
		SCENE_WINDOW_WIDTH = 950;
		SCENE_WINDOW_HEIGHT = 400;
		
		// Classes
		TEXT_NODE_LEFT_LAYOUT = "text_node_left_layout";
		TEXT_NODE_CENTER_LAYOUT = "text_node_center_layout";
		HYPERLINK = "hyperlink";
		
		// I.D.'s
		LOGIN_WINDOW = "login_window";
		LOGGED_IN_WINDOW = "logged_in_window";
		CENTER_LAYOUT = "center_layout";
		LOG_IN_BUTTON = "log_in_button";
		LOG_OUT_BUTTON = "log_out_button";
		WARNING_LABEL = "warning_label";
		
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
		outerLayout = new BorderPane();
		loginWindow = setLoginWindow();
		centerLayout = setCenterLayout();
		
		// Set the areas of the Border Pane
		outerLayout.setLeft(loginWindow);
		outerLayout.setCenter(centerLayout);
		
		return outerLayout;
	}
	
	// Sets the leftmost layout of the Main Window
	private GridPane setLoginWindow() {
		GridPane gp = new GridPane();
		gp.setId(LOGIN_WINDOW);
	
		// Row 0
		Label userName = new Label("User Name:");
		Label password = new Label("Password:");
		warningLabel = new Label();
		Button logInButton = new Button();
		
		// Row 1
		TextField userTextField = new TextField();
		PasswordField passwordField = new PasswordField();
		Hyperlink link = setCreateAccountLink();
		
		// Set Classes
		Node textNodes[] = new Node[] { userName, password };
		for(int i = 0; i < textNodes.length; i++) { textNodes[i].getStyleClass().add(TEXT_NODE_LEFT_LAYOUT);}
		link.getStyleClass().add(HYPERLINK);
		
		// Set login handler
		logInButton = setLoginButton(userTextField, passwordField);
		
		// Set I.D.'s
		logInButton.setId(LOG_IN_BUTTON);
		warningLabel.setId(WARNING_LABEL);
				
		Node nodesAtCol0[] = new Node[] { userName, password, logInButton, warningLabel};
		for(int i = 0; i < nodesAtCol0.length; i++) { gp.add(nodesAtCol0[i], 0, i); }
		
		Node nodesAtCol1[] = new Node[] { userTextField, passwordField, link };
		for(int i = 0; i < nodesAtCol1.length; i++) { gp.add(nodesAtCol1[i], 1, i); }
		
		return gp;
	}
	
	private GridPane setLoggedInWindow(Customer c) {
		GridPane gp = new GridPane();
		gp.setId(LOGGED_IN_WINDOW);
		
		// Customer Data
		String fn = c.getFirstName();
		String ln = c.getLastName();
		
		// Row 0
		Label p = new Label("Profile: ");
		Label r = new Label("Reservations: ");
		Button b = setLogoutButton();
		
		// Row 1
		Text name = new Text(fn + " " + ln);
		
		// Set I.D.'s
		b.setId(LOG_OUT_BUTTON);
		
		// Set classes
		Node textNodes[] = new Node[] { p, r, name };
		for(int i = 0; i < textNodes.length; i++) { textNodes[i].getStyleClass().add(TEXT_NODE_LEFT_LAYOUT); }
		
		Node nodesAtCol0[] = new Node[] { p, r, b };
		for(int i = 0; i < nodesAtCol0.length; i++) { gp.add(nodesAtCol0[i], 0, i); }
		
		Node nodesAtCol1[] = new Node[] { name };
		for (int i = 0; i < nodesAtCol1.length; i++) { gp.add(nodesAtCol1[i], 1, i); }
		
		return gp;
	}
	
	private GridPane setCenterLayout() {
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.TOP_CENTER);
		gp.setId(CENTER_LAYOUT);
		
		// Row 0
		Label fromLabel = new Label("From");
		Label toLabel = new Label("To");
		Label departLabel = new Label("Depart");
		Label returnLabel = new Label("Return");
		
		// Row 1
		TextField fromField = new TextField();
		TextField toField = new TextField();
		DatePicker departDate = setDatePicker();
		DatePicker returnDate = setDatePicker();
		
		Label labels[] = new Label[] { fromLabel, toLabel, departLabel, returnLabel };
		for(int i = 0; i < labels.length; i++) { 
			labels[i].getStyleClass().add(TEXT_NODE_CENTER_LAYOUT);
			gp.add(labels[i], i, 0);
		}
		
		Node fields[] = new Node[] { fromField, toField, departDate, returnDate };
		for(int i = 0; i < fields.length; i++) { gp.add(fields[i], i, 1); }
		
		// TODO set classes for styling
	
		return gp;
	}
	
	private Button setLoginButton(TextField tf, PasswordField pf) {
		Button b = new Button("Log In");
		
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String un = tf.getText();
				String pw = pf.getText();
				
				// TODO make a more meaningful message to user
				if(validateFields(un, pw) ) { 
					Customer c = DatabaseManager.retrieveCustomer(un, pw); 
					
					if(c != null) { 
						outerLayout.getChildren().remove(loginWindow);
						
						loggedInWindow = setLoggedInWindow(c);
						
						outerLayout.setLeft(loggedInWindow);
					}
					else {
						warningLabel.setText("Invalid username\nor password");
					}
				}
			}
		});
		
		return b;
	}
	
	private Button setLogoutButton() {
		Button b = new Button("Log Out");
		
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				outerLayout.getChildren().remove(loggedInWindow);
				loginWindow = setLoginWindow();
				outerLayout.setLeft(loginWindow);
				
			}
			
		});
		
		return b;
	}
	
	private Hyperlink setCreateAccountLink() {
		Text createAccountText = new Text("Create Account");
		createAccountText.getStyleClass().add(TEXT_NODE_LEFT_LAYOUT);
		
		Hyperlink link = new Hyperlink("", createAccountText);	
		
		link.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				CustomerForm cf = new CustomerForm();
				Stage stage = new Stage();
				try {
					cf.start(stage);
				} 
				catch (Exception e) { e.printStackTrace(); }
			}			
		});
		
		return link;
	}
	
	// TODO restrict past dates
	private DatePicker setDatePicker() {
		DatePicker datePicker = new DatePicker();
		
		datePicker.setOnAction(new EventHandler<ActionEvent>() {

			@Override // TODO Will make sure you can't make reservations for the past.
			public void handle(ActionEvent e) {
				LocalDate currentDate = LocalDate.now();
				
				System.out.print(datePicker.getValue() );
				
				System.out.println(currentDate.isBefore(datePicker.getValue() ) );
			}
		});
		
		return datePicker;
	}
	
	private boolean validateFields(String un, String pw) {
		if(un.isEmpty() ) { 
			warningLabel.setText("Username field\nis empty.");
			return false; 
		}
		else if(pw.isEmpty() ) { 
			warningLabel.setText("Password field\nis empty.");
			return false; 
		}
		
		return true;
	}
}
