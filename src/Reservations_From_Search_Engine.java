import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Reservations_From_Search_Engine {
	public String Airline;
	public double price;
	public String Arrival_Time;
	public String Departure_time;
	public String current_status;
	TableView<Reservations_From_Search_Engine>table;
	VBox VBox;

	public Reservations_From_Search_Engine() {
		this.Airline="";
		this.price =0;
		this.Arrival_Time="";
		this.Departure_time ="";
		this.current_status="";
		VBox = new VBox();
		VBox.setPrefHeight(300);
		
		start();
	}
	
	public Reservations_From_Search_Engine(String Airline, double price,String Departure_time, String Arrival_Time,String current_status) {
		
		this.Airline = Airline;
		this.price = price;
		this.Arrival_Time = Arrival_Time;
		this.Departure_time = Departure_time;
		this.current_status = current_status;
		VBox = new VBox();
		
		start();
	}
	
	public String getArrival_Time() {
		return Arrival_Time;
	}

	public void setArrival_Time(String arrival_Time) {
		Arrival_Time = arrival_Time;
	}

	public String getDeparture_time() {
		return Departure_time;
	}

	public void setDeparture_time(String departure_time) {
		Departure_time = departure_time;
	}

	public String getArrivalTime() {
		return Arrival_Time;
	}
	public String getName() {
		return Airline;
	}
	
	public String getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}

	public double getPrice() {
		return price;
	}
	
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public VBox getVBox() { return VBox; }

	public void start() {
		// TODO Auto-generated method stub
		
		//Airline Name column 
		TableColumn<Reservations_From_Search_Engine,String> nameColumn = new TableColumn<>("Airline");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
			
		// price Column
		TableColumn<Reservations_From_Search_Engine,Double> pricecol = new TableColumn<>("Price");
		pricecol.setMinWidth(100);
		pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		//Departure time column
		TableColumn<Reservations_From_Search_Engine,String> Departure_time = new TableColumn<>("Departure Time");
		Departure_time.setMinWidth(100);
		Departure_time.setCellValueFactory(new PropertyValueFactory<>("Departure_time"));
		
		//Arrival Time column
		TableColumn<Reservations_From_Search_Engine,String> Arrival_Time = new TableColumn<>("Arrival Time");
		Arrival_Time.setMinWidth(100);
		Arrival_Time.setCellValueFactory(new PropertyValueFactory<>("Arrival_Time"));
		
		//status column
		TableColumn<Reservations_From_Search_Engine,String> status = new TableColumn<>("Available");
		status.setMinWidth(100);
		status.setCellValueFactory(new PropertyValueFactory<>("current_status"));
			
		TextField reservationconfirm = new TextField();
		reservationconfirm.setPromptText("Confirmed");
		
		//button
		Button addButton = new Button("Book");
		addButton.setOnAction(e->ConfirmedButtonClicked());
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10,10,10,10));
		hbox.setSpacing(10);
		hbox.getChildren().addAll(addButton);
	
		table = new TableView<Reservations_From_Search_Engine>();
		//table.setItems(getProduct());
		table.getColumns().addAll(nameColumn,pricecol,Departure_time,Arrival_Time,status);
		
		VBox.getChildren().addAll(table, hbox);
	}
	public void ConfirmedButtonClicked() {
		ObservableList<Reservations_From_Search_Engine> productSelected, allProducts;
		allProducts = table.getItems();
		productSelected = table.getSelectionModel().getSelectedItems();
	}
	
	/*public ObservableList<Reservations_From_Search_Engine> getProduct(){
		ObservableList<Reservations_From_Search_Engine> products = FXCollections.observableArrayList();
		products.add(new Reservations_From_Search_Engine ("delta",89,"1534","1423"," Available"));
		products.add(new Reservations_From_Search_Engine ("southwest",859,"2345","0234"," Available"));
		products.add(new Reservations_From_Search_Engine ("delta",859,"2023","2344"," Available"));
		products.add(new Reservations_From_Search_Engine ("southwest",859,"2230","4233"," Available"));
		products.add(new Reservations_From_Search_Engine ("jetblue",859,"2032","1323"," Available"));
		return products;
	}*/
}