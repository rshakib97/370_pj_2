
public final class GlobalData {
	private static Account loggedInAccount;
	private static String currentDate;
	
	public GlobalData() {
		loggedInAccount = null;
		currentDate = null;
	}
	
	// Getters
	public static Account getLoggedInAccount() { return loggedInAccount; }
	public static String getCurrentDate() { return currentDate; }
	
	// Setters
	public static void setLoggedInAccount(Account a) { loggedInAccount = a; }
	public static void setCurrentDate(String d) { currentDate = d; }
}
