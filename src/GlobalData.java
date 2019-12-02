/* Allows access to the current date through the program and a logged in customer if any */

import java.time.LocalDate;

public final class GlobalData {
	private static Account loggedInAccount;
	private static LocalDate currentDate;
	
	public GlobalData() {
		loggedInAccount = null;
		currentDate = null;
	}
	
	// Getters
	public static Account getLoggedInAccount() { return loggedInAccount; }
	public static LocalDate getCurrentDate() { return currentDate; }
	
	// Setters
	public static void setLoggedInAccount(Account a) { loggedInAccount = a; }
	public static void setCurrentDate(LocalDate localDate) { currentDate = localDate; }
}
