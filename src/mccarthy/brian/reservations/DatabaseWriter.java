package mccarthy.brian.reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseWriter {
	
	public void writeReservation(Reservation reservation) {
		if (reservation.getSeatID().equals("UNKNOWN")) {
			throw new IllegalArgumentException("Please properly initialize Reservation with correct seatID!");
		}
		PreparedStatement preStatement;
		Connection connection = Reservations.getDB().getConnection();
		try {
			preStatement = connection.prepareStatement("INSERT INTO Data(seatID, name, notes) VALUES(?, ?, ?)");
			preStatement.setString(1, reservation.getSeatID());
			preStatement.setString(2, reservation.getName());
			preStatement.setString(3, reservation.getNotes());
		} catch (Exception e) {
			System.err.println("Error creating prepared statment!");
			return;
		}
		
		try {
			preStatement.executeUpdate();
		} catch (Exception e) {
			System.err.println("Error executing prepared statment!");
			e.printStackTrace();
		}
		Reservations.getDB().closeConnection(connection);
	}
	
	public void removeReservation(String seatID) {
		PreparedStatement preStatement;
		Connection connection = Reservations.getDB().getConnection();
		try {
			preStatement = connection.prepareStatement("DELETE FROM data WHERE seatID='" + seatID + "'");
		} catch (Exception e) {
			System.err.println("Error creating prepared statment!");
			return;
		}
		
		try {
			preStatement.executeUpdate();
		} catch (Exception e) {
			System.err.println("Error executing prepared statment!");
			e.printStackTrace();
		}
		Reservations.getDB().closeConnection(connection);
	}
	
}
