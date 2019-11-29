
public class Reservation {
	private Integer reservationID, flightID;
	private String custFN, custLN;
	
	public Reservation(String fn, String ln, int resID, int fID) {
		custFN = fn;
		custLN = ln;
		
		reservationID = resID;
		flightID = fID;
	}
	
	public String getCustFN() { return custFN; }
	public String getCustLN() { return custLN; }
	
	public int getReservationID() { return reservationID; }
	public int getFlightID() { return flightID; }
}
