package project_2_370;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class newcustomersform extends Application{
 
public static void main(String[] args) {
// TODO Auto-generated method stub
launch(args);
}
 
@Override
public void start(Stage primaryStage) throws Exception {
// TODO Auto-generated method stub
primaryStage.setTitle("New Customer Form");
TextField firstname = new TextField();
firstname.setText("firstname");
TextField lastname = new TextField();
lastname.setText("lastname");
TextField middleinitial = new TextField();
middleinitial.setText("middle initial");
Button btn = new Button("Click me to reveal the above text");
btn.setOnAction(new EventHandler<ActionEvent>() {
 
@Override
public void handle(ActionEvent event) {
// TODO Auto-generated method stub
System.out.println("Entered text is " + firstname.getText());
firstname.clear();
}
});
BorderPane pane = new BorderPane();
pane.setPadding(new Insets(10));
VBox paneCenter = new VBox();
paneCenter.setSpacing(10);
//pane.setCenter(paneCenter);
pane.setLeft(paneCenter);
paneCenter.getChildren().add(firstname);
paneCenter.getChildren().add(lastname);
paneCenter.getChildren().add(middleinitial);
paneCenter.getChildren().add(btn);
Scene scene= new Scene(pane, 600, 450);
primaryStage.setScene(scene);
primaryStage.show();
}
 
}