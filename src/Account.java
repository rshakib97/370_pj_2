/* Base class where all types of accounts will inherit from */

public abstract class Account {
	protected enum Clearance { CUST, FADMIN, SADMIN } 
	protected Clearance status;
	
	protected int accountID;
	protected String username;
	protected String password;
	
	public Account(int id, String un, String pw) {
		accountID = id;
		username = un;
		password = pw;
	}
}
