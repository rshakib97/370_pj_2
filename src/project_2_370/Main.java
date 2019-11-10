package project_2_370;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception { 
		MainWindow mainWindow = new MainWindow(); 
		primaryStage.setResizable(false);
		mainWindow.start(primaryStage);
	}
}