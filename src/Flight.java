public class Flight {
	private int flightID, maxCap, reserved;
	private String date, deptTime, arrTime, origin, destination, airline;
	private double fare;
	private FlightStatus flightStatus;
	
	public Flight(int id, int max, int res, String d, String dt, String at, String o, String dest, String a, double f, FlightStatus fs) {
		 flightID = id;
		 maxCap = max;
		 reserved = res;
		 
		 date = d;
		 deptTime = dt;
		 arrTime = at;
		 origin = o;
		 destination = dest;
		 airline = a;
		 
		 fare = f;
		 
		 flightStatus = fs;
	}

	// Getter Methods
	public int getFlightID() { return flightID; }
	public int getMaxCap() { return maxCap; }
	public int getReserved() { return reserved; }
	public String getDate() { return date; }
	public String getDeptTime() { return deptTime; }
	public String getArrTime() { return arrTime; }
	public String getOrigin() { return origin; }
	public String getDestination() { return destination; }
	public String getAirline() { return airline; }
	public double getFare() { return fare; }
	public FlightStatus getFlightStatus() { return flightStatus; }
	
	public String displayDepartures() {
		return  "Destination: " + destination + 
				"\tAirline: " + airline + 
				"\tFlight: " + flightID + 
				"\tDeparture Time: " + deptTime;
	}
	
	public String displayArrivals() {
		return "Origin: " + destination +
				"\tAirline: " + airline +
				"\tFlight: " + flightID +
				"\tArrival Time: " + arrTime;
	}
}
