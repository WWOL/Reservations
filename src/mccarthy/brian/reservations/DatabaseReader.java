package mccarthy.brian.reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseReader {

	/**
	 * 
	 * @param seatID
	 * @return null if error occurs
	 */
	public Reservation readReservation(String seatID) {
		Reservation reservation = new Reservation();
		PreparedStatement preStatement;
		ResultSet rs;
		Connection connection = Reservations.getDB().getConnection();
		try {
			preStatement = connection.prepareStatement("SELECT * FROM Data WHERE seatID=" + seatID);
		} catch (Exception e) {
			System.err.println("Error creating prepared statment!");
			return null;
		}

		try {
			rs = preStatement.executeQuery();
		} catch (Exception e) {
			System.err.println("Error executing prepared statment!");
			e.printStackTrace();
			return null;
		}

		try {
			reservation.setSeatID(rs.getString(1));
			reservation.setName(rs.getString(2));
			reservation.setNotes(rs.getString(3));
		} catch (Exception e) {
			System.err.println("Error creating reservation from result set!");
		}
		Reservations.getDB().closeConnection(connection);
		return reservation;
	}

	public void loadAll() {
		PreparedStatement preStatement;
		ResultSet rs;
		System.err.println("Connection: " + Reservations.getDB());
		Connection connection = Reservations.getDB().getConnection();
		try {
			preStatement = connection.prepareStatement("SELECT * FROM Data");
		} catch (Exception e) {
			System.err.println("Error creating prepared statment!");
			return;
		}

		try {
			rs = preStatement.executeQuery();
		} catch (Exception e) {
			System.err.println("Error executing prepared statment!");
			e.printStackTrace();
			return;
		}
		try {
			while (rs.next()) {
				Reservation reservation = new Reservation();
				try {
					reservation.setSeatID(rs.getString(1));
					reservation.setName(rs.getString(2));
					reservation.setNotes(rs.getString(3));
				} catch (Exception e) {
					System.err.println("Error creating reservation from result set!");
				}
				Reservations.getInstance().addReservation(reservation);
			}
		} catch (Exception e) {
			System.out.println("Error reading past results from database!");
		}
		Reservations.getDB().closeConnection(connection);
	}

}
