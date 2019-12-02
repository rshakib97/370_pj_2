import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public final class DatabaseManager {
	private static String databaseName;
	private String url;
	private String user;
	private String password;
	
	private static String accountsDatabase;
	private static String airportsDatabase;
	private static String flightsDatabase;
	private static String reservationsDatabase;
	
	private static Connection con;
	
	public DatabaseManager() {
		databaseName = "caad3435";
		url = "jdbc:mysql://149.4.211.180/" + databaseName;
		user = "caad3435";
		password = "23033435";
		
		accountsDatabase = "Accounts";
		airportsDatabase = "Airports";
		flightsDatabase = "Flights";
		reservationsDatabase = "Reservations";
		
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
	public static boolean insertCustomer(String fn, String ln, String un, String pw) {
		try {
			String params[] = new String[] { fn, ln, un, pw, "CUST" };
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + accountsDatabase + " VALUES(DEFAULT,?,?,?,?,?)");
			
			for(int i = 1; i <= params.length; i++) { ps.setString(i, params[i - 1]); }
			
			ps.executeUpdate();
		}
		
		catch(Exception e) { return false; }
		
		return true;
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
				
				else if( status == Clearance.SADMIN) { a = new FlightAdmin(id, fn, ln, userName, password, status); }
				
				else if(status == Clearance.AADMIN) { a = new SearchEngineAdmin(id, fn, ln, userName, password, status); }
				
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
					" WHERE a1.airportName LIKE ? AND a2.airportName LIKE ? AND f.date = ?");
			
			ps.setString(1, from + "%");
			ps.setString(2, to + "%");
			ps.setDate(3, Date.valueOf(GlobalData.getCurrentDate() ) );
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
				FlightStatus status = FlightStatus.valueOf(rs.getString("status"));
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, airline, fare, status);
				
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
					" JOIN Airlines AS air ON f.airline = air.airlineID WHERE a1.airportName=? AND f.date = ?");
			
			ps.setString(1, airport);
			ps.setDate(2, Date.valueOf(GlobalData.getCurrentDate() ) );
			
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
				FlightStatus status = FlightStatus.valueOf(rs.getString("status"));
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, airline, fare, status);
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
					" JOIN Airlines AS air ON f.airline = air.airlineID WHERE a2.airportName=? AND f.date = ?");
			
			ps.setString(1, airport);
			ps.setDate(2, Date.valueOf(GlobalData.getCurrentDate() ) );
			
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
				FlightStatus status = FlightStatus.valueOf(rs.getString("status"));
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, airline, fare, status);
				flights.add(f);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return flights;
	}
	
	public static ArrayList<Airline> getAllAirlines() {
		ArrayList<Airline> airlines = new ArrayList<Airline>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Airlines");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				String name = rs.getString("airlineName");
				Airline a = new Airline(name);
				airlines.add(a);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return airlines;
	}
	
	public static ArrayList<Flight> getFlightsFromAirline(String airline) {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * " + 
					" FROM Flights AS f" + 
					" JOIN Airports AS a1 ON f.origin = a1.airportID" + 
					" JOIN Airports AS a2 ON f.dest = a2.airportID" + 
					" JOIN Airlines AS air ON f.airline = air.airlineID WHERE air.airlineName=? AND f.date = ?");
			
			ps.setString(1, airline);
			ps.setDate(2, Date.valueOf(GlobalData.getCurrentDate() ) );
			
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
				String a = rs.getString("airlineName");
				double fare = rs.getDouble("fare");
				FlightStatus status = FlightStatus.valueOf(rs.getString("status"));
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, a, fare, status);
				flights.add(f);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return flights;
	}
	
	public static ArrayList<Flight> getReservationsOf(String userName) {
		ArrayList<Flight> reservations = new ArrayList<Flight>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Reservations AS r" +
					" JOIN Flights AS f ON r.flightID = f.flightID" +
					" JOIN Airports AS a1 ON f.origin = a1.airportID" + 
					" JOIN Airports AS a2 ON f.dest = a2.airportID" + 
					" JOIN Accounts AS a ON r.customerID = a.accountID" + 
					" JOIN Airlines AS air ON f.airline = air.airlineID" + 
					" WHERE a.userName = ?");
			
			ps.setString(1, userName);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				int id = rs.getInt("f.flightID");
				int maxCap = rs.getInt("f.maxCap");
				int res = rs.getInt("f.reserved");
				String date = rs.getString("f.date");
				String deptTime = rs.getString("f.depTime");
				String arrTime = rs.getString("f.arrTime");
				String origin = rs.getString("a1.airportName");
				String dest = rs.getString("a2.airportName");
				String a = rs.getString("air.airlineName");
				double fare = rs.getDouble("f.fare");
				FlightStatus status = FlightStatus.valueOf(rs.getString("status"));
				
				Flight f = new Flight(id, maxCap, res, date, deptTime, arrTime, origin, dest, a, fare, status);
				reservations.add(f);
			}
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return reservations;
	}
	
	// Lets customers book reservations, will return false for any other account status
	// TODO disallow customers being able to book two of the same reservations
	public static boolean bookReservation(Account a, int flightID, BookStatus status) {
		if(a == null || a.getStatus() != Clearance.CUST) { return false; }
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO " + reservationsDatabase + " VALUES(DEFAULT,?,?,?)");
			ps.setInt(1, a.getAccountID() );
			ps.setInt(2, flightID);
			ps.setString(3, status.toString() );
			
			ps.executeUpdate();
			ps.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return true;
	}
	
	// Lets a customer cancel a reservation
	public static boolean cancelReservation(int flightID) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM " + reservationsDatabase + " WHERE flightID = ?");
			ps.setInt(1, flightID);
		
			ps.executeUpdate();
			ps.close();
		}
		
		catch(Exception e) { System.out.println(e); }
	
		return true;
	}
	
	public static ArrayList<Reservation> getReservationsFromSearchEngine() {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + reservationsDatabase + 
					" AS r JOIN Accounts AS a ON r.customerID = a.accountID"
					+ " WHERE bookingStatus = 'SE'");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				String fn = rs.getString("a.firstName");
				String ln = rs.getString("a.lastName");
				Integer rID = rs.getInt("r.reservationID");
				Integer fID = rs.getInt("r.flightID");
				
				Reservation r = new Reservation(fn, ln, rID, fID);
				reservations.add(r);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return reservations;
	}
	
	public static ArrayList<Reservation> getReservationsFromAirline(String name) {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Reservations AS r" +
					" JOIN Flights AS f ON r.flightID = f.flightID" +
					" JOIN Airports AS a1 ON f.origin = a1.airportID" + 
					" JOIN Airports AS a2 ON f.dest = a2.airportID" + 
					" JOIN Accounts AS a ON r.customerID = a.accountID" + 
					" JOIN Airlines AS air ON f.airline = air.airlineID" + 
					" WHERE air.airlineName = ?");
			
			ps.setString(1, name);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next() ) {
				String fn = rs.getString("a.firstName");
				String ln = rs.getString("a.lastName");
				Integer rID = rs.getInt("r.reservationID");
				Integer fID = rs.getInt("r.flightID");
				
				Reservation r = new Reservation(fn, ln, rID, fID);
				reservations.add(r);
			}
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
		
		return reservations;
	}
	
	public static void cancelFlight(int flightID) {
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE Flights SET status = 'CANC' WHERE flightID = ?");
			ps.setInt(1, flightID);
			
			ps.executeUpdate();
			
			ps.close();
		}
		
		catch(Exception e) { System.out.println(e); }
	}
	
	public static void addFlight(int max, LocalDate date, String deptTime, String arrTime, String origin, String dest, String airline, double fare) {
		try {
			int airlineID = 0;
			int originID = 0;
			int destinationID = 0;
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM Airlines WHERE airlineName = ?");
			ps.setString(1, airline);
			
			ResultSet rs = ps.executeQuery();
		
			while(rs.next() ) { airlineID = rs.getInt("airlineID"); }
			
			ps = con.prepareStatement("SELECT * FROM Airports WHERE airportName = ?");
			ps.setString(1, origin);
			
			rs = ps.executeQuery();
			
			while(rs.next() ) { originID = rs.getInt("airportID"); }
			
			System.out.println(originID);
			
			ps = con.prepareStatement("SELECT * FROM Airports WHERE airportName = ?");
			ps.setString(1, dest);
			
			rs = ps.executeQuery();
			
			while(rs.next() ) { destinationID = rs.getInt("airportID"); }
			
			System.out.println(destinationID);
			
			ps = con.prepareStatement("INSERT INTO Flights (flightID, date, airline, depTime, arrTime, origin, dest, maxCap, fare) VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setDate(1, Date.valueOf(date) );
			ps.setInt(2, airlineID);
			ps.setString(3, deptTime);
			ps.setString(4, arrTime);
			ps.setInt(5, originID);
			ps.setInt(6, destinationID);
			ps.setInt(7, max);
			ps.setDouble(8, fare);
			
			ps.executeUpdate();
			
			ps.close();
			rs.close();
		}
		
		catch(Exception e) { System.out.println(e); }
	}
}
