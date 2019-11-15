import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class CustomerForm extends Application{
	public void bothmissing(Stage stage) 
    { 
   
        // set title for the stage 
        stage.setTitle("Creating popup"); 
   
        // create a button 
        Button button = new Button("button"); 
   
        // create a tile pane 
        TilePane tilepane = new TilePane(); 
   
        // create a label 
        Label label = new Label(" Please enter a valid name !"); 
        label.centerShapeProperty();
        // create a popup 
    //    Popup popup = new Popup(); 
   
        // set background 
       // label.setStyle(" -fx-background-color: white;"); 
   
        // add the label 
      //  popup.getContent().add(label); 
   
        // set size of label 
        label.setMinWidth(50); 
        label.setMinHeight(50); 
   
        // action event 
 //       EventHandler<ActionEvent> event =  
//        new EventHandler<ActionEvent>() { 
//   
//            public void handle(ActionEvent e) 
//            { 
//                if (!popup.isShowing()) 
//                    popup.show(stage); 
//                else
//                    popup.hide(); 
//            } 
//        }; 
   
        // when button is pressed 
       // button.setOnAction(event); 
      //  button.isCenterShape();
        // add button 
   //     tilepane.getChildren().add(button); 
        tilepane.getChildren().add(label);
        // create a scene 
        Scene scene = new Scene(tilepane, 200, 100); 
   
        // set the scene 
        stage.setScene(scene); 
   
        stage.show(); 
    } 
	public void start2(Stage stage) 
    { 
   
        // set title for the stage 
        stage.setTitle("Creating popup"); 
   
        // create a button 
        Button button = new Button("button"); 
   
        // create a tile pane 
        TilePane tilepane = new TilePane(); 
   
        // create a label 
        Label label = new Label(" Please enter a valid lastname !"); 
        label.centerShapeProperty();
        // create a popup 
    //    Popup popup = new Popup(); 
   
        // set background 
       // label.setStyle(" -fx-background-color: white;"); 
   
        // add the label 
      //  popup.getContent().add(label); 
   
        // set size of label 
        label.setMinWidth(50); 
        label.setMinHeight(50); 
   
        // action event 
 //       EventHandler<ActionEvent> event =  
//        new EventHandler<ActionEvent>() { 
//   
//            public void handle(ActionEvent e) 
//            { 
//                if (!popup.isShowing()) 
//                    popup.show(stage); 
//                else
//                    popup.hide(); 
//            } 
//        }; 
   
        // when button is pressed 
       // button.setOnAction(event); 
      //  button.isCenterShape();
        // add button 
   //     tilepane.getChildren().add(button); 
        tilepane.getChildren().add(label);
        // create a scene 
        Scene scene = new Scene(tilepane, 200, 100); 
   
        // set the scene 
        stage.setScene(scene); 
   
        stage.show(); 
    } 
	public void start1(Stage stage) 
    { 
   
        // set title for the stage 
        stage.setTitle("Creating popup"); 
   
        // create a button 
        Button button = new Button("button"); 
   
        // create a tile pane 
        TilePane tilepane = new TilePane(); 
   
        // create a label 
        Label label = new Label(" Please enter a valid first name !"); 
        label.centerShapeProperty();
        // create a popup 
    //    Popup popup = new Popup(); 
   
        // set background 
       // label.setStyle(" -fx-background-color: white;"); 
   
        // add the label 
      //  popup.getContent().add(label); 
   
        // set size of label 
        label.setMinWidth(50); 
        label.setMinHeight(50); 
   
        // action event 
 //       EventHandler<ActionEvent> event =  
//        new EventHandler<ActionEvent>() { 
//   
//            public void handle(ActionEvent e) 
//            { 
//                if (!popup.isShowing()) 
//                    popup.show(stage); 
//                else
//                    popup.hide(); 
//            } 
//        }; 
   
        // when button is pressed 
       // button.setOnAction(event); 
      //  button.isCenterShape();
        // add button 
   //     tilepane.getChildren().add(button); 
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
primaryStage.setTitle("New Customer Form");
TextField firstname = new TextField();
firstname.setText("first name");
TextField lastname = new TextField();
lastname.setText("last name");
TextField middleinitial = new TextField();
middleinitial.setText("middle initial");
Button btn = new Button("submit");
btn.setOnAction(new EventHandler<ActionEvent>() {
 
@Override
public void handle(ActionEvent event) {
// TODO Auto-generated method stub
	int counter =0;
	if((firstname.getLength()==0&&lastname.getLength()==0)) {
		Stage trying = new Stage();
		bothmissing( trying) ;
		counter =1;
	}
	System.out.println("Entered text is " + firstname.getText());
	if(firstname.getLength()==0&& counter ==0) {
		Stage trying = new Stage();
		start1( trying) ;
		System.out.println("please enter your first name");
		
	}
	System.out.println("Entered text is " + lastname.getText());
	if(lastname.getLength()==0 &&counter ==0) {
		Stage trying = new Stage();
		start2( trying) ;
		System.out.println("please enter your first name");
		
	}
System.out.println("Entered text is " + middleinitial.getText());
if(middleinitial.getLength()==0)
middleinitial.clear();
}
});
BorderPane pane = new BorderPane();
BorderPane paneright = new BorderPane();
pane.setPadding(new Insets(10));

VBox paneCenter = new VBox();
VBox paneCenter2 = new VBox();
paneCenter.setSpacing(10);
//pane.setCenter(paneCenter);
pane.setLeft(paneCenter);
paneright.setRight(paneCenter2);
paneCenter.getChildren().add(firstname);
paneCenter.getChildren().add(lastname);
paneCenter.getChildren().add(middleinitial);
paneCenter.getChildren().add(btn);
Scene scene= new Scene(pane, 600, 450);
primaryStage.setScene(scene);
primaryStage.show();
}
 
}