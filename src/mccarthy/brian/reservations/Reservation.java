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
	 * Holds data such as if a person has paid, any string
	 */
	String notes;

	/**
	 * Create a new {@link Reservation}
	 * @param seatID unique id of the seat, any non-blank string
	 * @param name name of person reserving seat
	 * @param notes any notes
	 */
	public Reservation(String seatID, String name, String notes) {
		this.seatID = seatID;
		this.name = name;
		this.notes = notes;
	}
	
	/**
	 * Create a new {@link Reservation} with deafault params
	 * @see #setSeatID(String)
	 * @see #setName(String)
	 * @see #setNotes(String)
	 */
	public Reservation() {
		this.seatID = "UNKNOWN";
		this.name = "UNKNOWN";
		this.notes = "UNKNOWN";
	}

	/**
	 * Get the seatID
	 * @return seatID of this reservation 
	 */
	public String getSeatID() {
		return seatID;
	}

	/**
	 * Set seatID for this reservation
	 * @param seatID the new seatID
	 */
	public void setSeatID(String seatID) {
		this.seatID = seatID;
	}

	/**
	 * Get the name of the reserver for this reservation
	 * @return reservation name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name for this reservation
	 * @param name new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the notes attached to this reservation
	 * @return notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Set the notes for this reservation 
	 * @param notes new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
