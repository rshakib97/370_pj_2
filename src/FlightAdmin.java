/* Flight Admins should be able to add additional Airlines and Flights*/ 

public class FlightAdmin extends Account {

	public FlightAdmin(int id, String fn, String ln, String username, String password, Clearance c) {
		super(id, fn, ln, username, password, c);
	}
}
