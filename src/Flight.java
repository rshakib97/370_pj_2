/* Should have a fare cost and where this flight will be going. */
public class Flight {
	private int flightID, maxCap, reserved;
	private String date, deptTime, arrTime, origin, destination;
	private double fare;
	
	public Flight(int id, int max, int res, String d, String dt, String at, String o, String dest, double f) {
		 flightID = id;
		 maxCap = max;
		 reserved = res;
		 
		 date = d;
		 deptTime = dt;
		 arrTime = at;
		 origin = o;
		 destination = dest;
		 
		 fare = f;
	}

	// Getter Methods
	public int getflightID() { return flightID; }
	public int getmaxCap() { return maxCap; }
	public int getReserved() { return reserved; }
	public String getDate() { return date; }
	public String getdeptTime() { return deptTime; }
	public String getArrTime() { return arrTime; }
	public String getOrigin() { return origin; }
	public String getDesination() { return destination; }
	public double getFare() { return fare; }
}
