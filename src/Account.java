/* Base class where all types of accounts will inherit from */

public abstract class Account {
	protected Clearance status;
	protected int accountID;
	protected String firstName, lastName, username, password;
	
	public Account(int id, String fn, String ln, String un, String pw, Clearance c) {
		accountID = id;
		firstName = fn;
		lastName = ln;
		username = un;
		password = pw;
		status = c;
	}
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public Clearance getStatus() { return status; }
}
