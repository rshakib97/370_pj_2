/* Flight Admins should be able to add additional Airlines and Flights*/ 

public class FlightAdmin extends Account {

	public FlightAdmin(int id, String username, String password) {
		super(id, username, password);
		
		status = Clearance.FADMIN;
	}
}
