import java.util.ArrayList;

/* Customers should be able to add/cancel reservations and search arrivals/departures */

public class Customer extends Account {
	private ArrayList<Flight> reservedFlights;
	
	public Customer(int id, String fn, String ln, String username, String password, Clearance c) {
		super(id, fn, ln, username, password, c);
		reservedFlights = new ArrayList<Flight>();
	}
	
	public ArrayList<Flight> getAllReservedFlights() { return reservedFlights; }
	public Flight getReservedFlight(int index) { return reservedFlights.get(index); }
}
