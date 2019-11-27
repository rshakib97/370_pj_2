import java.sql.*;
import java.util.ArrayList;

public final class DatabaseManager {
	private static String databaseName;
	private String url;
	private String user;
	private String password;
	
	private static String accountsDatabase;
	private static String airportsDatabase;
	private static String flightsDatabase;
	
	private static Connection con;
	
	public DatabaseManager() {
		databaseName = "caad3435";
		url = "jdbc:mysql://149.4.211.180/" + databaseName;
		user = "caad3435";
		password = "23033435";
		
		accountsDatabase = "Accounts";
		airportsDatabase = "Airports";
		flightsDatabase = "Flights";
		
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
			String params[] = new String[] { fn, ln, un, pw, "CUST" };
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + accountsDatabase + " VALUES(DEFAULT,?,?,?,?,?)");
			
			for(int i = 1; i <= params.length; i++) { ps.setString(i, params[i - 1]); }
			
			ps.executeUpdate();
		}
		
		catch(Exception e) { System.out.print(e); }
	}
	
	public static Account retrieveAccount(String un, String pw) {
		Account a = null;
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + accountsDatabase + " WHERE userName = ?" + " AND passWord = ?");
			ps.setString(1, un);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next() ) {
				int id = rs.getInt("accountID");
				String userName = rs.getString("userName");
				String password = rs.getString("passWord");
				String fn = rs.getString("firstName");
				String ln = rs.getString("lastName");
				Clearance status = Clearance.valueOf(rs.getString("status"));
				
				if(status == Clearance.CUST) { a = new Customer(id, fn, ln, userName, password, status); }
				
				else if( status == Clearance.FADMIN) { a = new FlightAdmin(id, fn, ln, userName, password, status); }
				
				else if(status == Clearance.SADMIN) { a = new SearchEngineAdmin(id, fn, ln, userName, password, status); }
				
				else { System.out.println("Not a valid clearance level"); }
			}
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return a;
	}
	
	// TODO compare arrivals
	public static ArrayList<Flight> searchFlights(String from, String to) {
		ArrayList<Flight> flights = new ArrayList<>();
		
		try {
			from = from.toUpperCase();
			to = to.toUpperCase();
			
			PreparedStatement ps = con.prepareStatement("SELECT * " + 
					" FROM " + flightsDatabase + " AS f" + 
					" JOIN Airports AS a1 ON f.origin = a1.airportID" + 
					" JOIN Airports AS a2 ON f.dest = a2.airportID" + 
					" JOIN Airlines AS air ON f.airline = air.airlineID" +
					" WHERE a1.airportName LIKE ?");
			
			ps.setString(1, from + "%");
			//ps.setString(2, to);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				int id = rs.getInt("flightID");
				int maxCap = rs.getInt("maxCap");
				int res = rs.getInt("reserved");
				String date = rs.getString("date");
				String deptTime = rs.getString("depTime");
				String arrTime = rs.getString("arrTime");
				String origin = rs.getString("a1.airportName");
				String dest = rs.getString("a2.airportName");
				String airline = rs.getString("air.airlineName");
				double fare = rs.getDouble("fare");
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, airline, fare);
				
				flights.add(f);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return flights;
	}
	
	public static ArrayList<String> getAllAirports() {
		ArrayList<String> airports = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + airportsDatabase);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				airports.add(rs.getString("airportName"));
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return airports;
	}
	
	public static ArrayList<Flight> getDestinationsFromAirport(String airport) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * " + 
					" FROM " + flightsDatabase + " AS f" + 
					" JOIN Airports AS a1 ON f.origin = a1.airportID" + 
					" JOIN Airports AS a2 ON f.dest = a2.airportID" + 
					" JOIN Airlines AS air ON f.airline = air.airlineID WHERE a1.airportName=?");
			
			ps.setString(1, airport);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				int id = rs.getInt("flightID");
				int maxCap = rs.getInt("maxCap");
				int res = rs.getInt("reserved");
				String date = rs.getString("date");
				String deptTime = rs.getString("depTime");
				String arrTime = rs.getString("arrTime");
				String origin = rs.getString("a1.airportName");
				String dest = rs.getString("a2.airportName");
				String airline = rs.getString("airlineName");
				double fare = rs.getDouble("fare");
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, airline, fare);
				flights.add(f);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return flights;
	}
	
	public static ArrayList<Flight> getArrivalsFromAirport(String airport) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * " + 
					" FROM Flights AS f" + 
					" JOIN Airports AS a1 ON f.origin = a1.airportID" + 
					" JOIN Airports AS a2 ON f.dest = a2.airportID" + 
					" JOIN Airlines AS air ON f.airline = air.airlineID WHERE a2.airportName=?");
			
			ps.setString(1, airport);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				int id = rs.getInt("flightID");
				int maxCap = rs.getInt("maxCap");
				int res = rs.getInt("reserved");
				String date = rs.getString("date");
				String deptTime = rs.getString("depTime");
				String arrTime = rs.getString("arrTime");
				String origin = rs.getString("a2.airportName");
				String dest = rs.getString("a1.airportName");
				String airline = rs.getString("airlineName");
				double fare = rs.getDouble("fare");
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, airline, fare);
				flights.add(f);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return flights;
	}
}
