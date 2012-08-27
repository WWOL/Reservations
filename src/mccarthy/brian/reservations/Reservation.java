package mccarthy.brian.reservations;

public class Reservation {

	/**
	 * ID of seat EX: 1A
	 */
	String seatID;
	
	/**
	 * Name of the reserver
	 */
	String name;
	
	/**
	 * Holds data such as if a person has paid
	 */
	String notes;

	public Reservation(String seatID, String name, String notes) {
		this.seatID = seatID;
		this.name = name;
		this.notes = notes;
	}
	
	public Reservation() {
		this.seatID = "UNKNOWN";
		this.name = "UNKNOWN";
		this.notes = "UNKNOWN";
	}

	public String getSeatID() {
		return seatID;
	}

	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
