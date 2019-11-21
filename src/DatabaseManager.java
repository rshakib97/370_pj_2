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
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void insertCustomer(String un, String pw) {
		try {
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM " + customerDatabase);
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();
	
			for(int i = 1; i <= colCount; i++) { 
				s.executeUpdate("INSERT INTO " + customerDatabase + " " + rsmd.getColumnName(i) + "VALUES ('albert'); ");
			}
			
			//s.executeUpdate("INSERT INTO `caad3435`.`RayhanAirCustomers` (`firstName`) VALUES ('albert');");
			//s.executeUpdate("INSERT INTO " + customerDatabase + " (`firstName`) VALUES ('albert');");
		}
		
		catch(Exception e) {
			System.out.print(e);
		}
		
	}
}
