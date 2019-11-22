import java.sql.*;

public final class DatabaseManager {
	private String databaseName;
	private String url;
	private String user;
	private String password;
	
	private static String customerDatabase;
	
	private static Connection con;
	
	public DatabaseManager() {
		databaseName = "caad3435";
		url = "jdbc:mysql://149.4.211.180/" + databaseName;
		user = "caad3435";
		password = "23033435";
		
		customerDatabase = "caad3435.Customers";
		
		initiateConnection();
	}
	
	private void initiateConnection() {
		try {
			System.out.println("Connection Successful");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		}
		
		catch(Exception e) { System.out.println(e); }
	}
	
	// Puts a new customer into the database
	public static void insertCustomer(String fn, String ln, String un, String pw) {
		try {
			String params[] = new String[] { fn, ln, un, pw };
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + customerDatabase + " VALUES(DEFAULT,?,?,?,?)");
			
			for(int i = 1; i <= params.length; i++) { ps.setString(i, params[i - 1]); }
			
			ps.executeUpdate();
		}
		
		catch(Exception e) { System.out.print(e); }
	}
	
	public static Customer retrieveCustomer(String un, String pw) {
		Customer c = null;
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + customerDatabase + " WHERE userName = ?" + " AND passWord = ?");
			ps.setString(1, un);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next() ) {
				int id = rs.getInt("customerID");
				String userName = rs.getString("userName");
				String password = rs.getString("passWord");
				String fn = rs.getString("firstName");
				String ln = rs.getString("lastName");
				
				c = new Customer(id, userName, password, fn, ln);
			}
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return c;
	}
}
