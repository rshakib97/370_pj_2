/* Base class where all types of accounts will inherit from */

public abstract class Account {
	protected Clearance status;
	protected int accountID;
	protected String firstName, lastName, userName, password;
	
	public Account(int id, String fn, String ln, String un, String pw, Clearance c) {
		accountID = id;
		firstName = fn;
		lastName = ln;
		userName = un;
		password = pw;
		status = c;
	}
	
	public int getAccountID() { return accountID; }
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public String getUserName() { return userName; }
	public Clearance getStatus() { return status; }
}
