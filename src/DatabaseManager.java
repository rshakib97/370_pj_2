import java.sql.*;

public final class DatabaseManager {
	private String databaseName;
	private String url;
	private String user;
	private String password;
	
	public DatabaseManager() {
		databaseName = "caad3435";
		url = "jdbc:mysql://149.4.211.180/" + databaseName;
		user = "caad3435";
		password = "23033435";
		
		initiateConnection();
	}
	
	private void initiateConnection() {
		try {
			System.out.println("Connection Successful");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM caad3435.airline");
	
			while(rs.next() ) {
				// Get results and do stuff with it
			}
			
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
