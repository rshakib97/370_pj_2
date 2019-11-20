/* Main driver for the program. */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String args[]) throws Exception {
		DatabaseManager dm = new DatabaseManager();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception { 
		MainWindow mainWindow = new MainWindow(); 
		primaryStage.setResizable(false);
		mainWindow.start(primaryStage);
	}
}
