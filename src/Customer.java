import java.util.ArrayList;

/* Customers should be able to add/cancel reservations and search arrivals/departures */

public class Customer extends Account {
	// TODO Customer must have a data structure for reservations
	private String firstName;
	private String lastName;
	
	private ArrayList<Flight> reservedFlights;
	
	public Customer(int id, String username, String password, String fn, String ln, Clearance c) {
		super(id, username, password);
		
		status = c;
		
		firstName = fn;
		lastName = ln;
		
		reservedFlights = new ArrayList<Flight>();
	}
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public ArrayList<Flight> getAllReservedFlights() { return reservedFlights; }
	public Flight getReservedFlight(int index) { return reservedFlights.get(index); }
}
