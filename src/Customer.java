/* Customers should be able to add/cancel reservations and search arrivals/departures */

public class Customer extends Account {
	// TODO Customer must have a data structure for reservations
	private String firstName;
	private String lastName;
	
	public Customer(int id, String username, String password, String fn, String ln) {
		super(id, username, password);
		firstName = fn;
		lastName = ln;
	}
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
}
