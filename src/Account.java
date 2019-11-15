/* Base class where all types of accounts will inherit from */

public abstract class Account {
	protected String username;
	protected String password;
	
	public Account(String un, String pw) {
		username = un;
		password = pw;
	}
}
