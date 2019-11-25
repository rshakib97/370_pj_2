/* Should have a fare cost and where this flight will be going. */
public class Flight {
	private int flightID, maxCap, reserved;
	private String date, deptTime, arrTime, origin, destination, airline;
	private double fare;
	
	public Flight(int id, String d, String a, String des) {
		flightID = id;
		deptTime = d;
		airline = a;
		destination = d;
	}
	
	public Flight(int id, int max, int res, String d, String dt, String at, String o, String dest, String a, double f) {
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
	}

	// Getter Methods
	public int getFlightID() { return flightID; }
	public int getMaxCap() { return maxCap; }
	public int getReserved() { return reserved; }
	public String getDate() { return date; }
	public String getdeptTime() { return deptTime; }
	public String getArrTime() { return arrTime; }
	public String getOrigin() { return origin; }
	public String getDesination() { return destination; }
	public String getAirline() { return airline; }
	public double getFare() { return fare; }
	
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
