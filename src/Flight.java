/* Should have a fare cost and where this flight will be going. */
public class Flight {
	private static int flightNumber = 0;
	private int filledSeats, maxSeats;
	private double cost;
	private Flight destination;
	
	public Flight(int max, Flight f) {
		maxSeats = max;
		destination = f;
		
		flightNumber++;
		filledSeats = 0;
		cost = 100.00; // TODO This is only a base cost, add a way to create a new cost based on available seating
	}

	// Getter Methods
	public int getFilledSeats() { return filledSeats; }
	public int getMaxSeats() { return maxSeats; }
	public double getCost() { return cost; }
	public Flight getDestination() { return destination; }
	
	// Setter Methods
	public void setMaxSeats(int max) { maxSeats = max; }
	public void setDestination(Flight f) { destination = f; }
	
	// Class Methods
	public boolean seatsAvailable() { return filledSeats >= maxSeats; }
	public void addFilledSeat() {
		if(seatsAvailable() ) { filledSeats++; } 
		else { System.out.println("Can not book flight number " + flightNumber); }
	}
}
