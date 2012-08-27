package mccarthy.brian.reservations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author Brian McCarthy
 *
 */
public class DatabaseConnector {

	private String url;
	private String user;
	private String pass;
	private Connection connection;
	private DatabaseReader reader;
	private DatabaseWriter writer;

	public DatabaseConnector(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		reader = new DatabaseReader();
		writer = new DatabaseWriter();
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			System.err.println("Error creating connection! Cannot continue!");
			e.printStackTrace();
			Reservations.getInstance().shutdown();
		}
		createTables(connection);
		System.err.println("Reader: " + reader);
		reader.loadAll();
		//test();
	}
	
	private void test() {
		PreparedStatement preStatement;
		ResultSet resultSet;
		try {
			preStatement = connection.prepareStatement("SELECT * FROM data");
			resultSet = preStatement.executeQuery();
			
			while (resultSet.next()) {
				System.out.println("ID: " + resultSet.getInt(1));
				System.out.println("Name: " + resultSet.getString(2));
			}
		} catch (SQLException e) {
			System.err.println("Error creating prepared statment!");
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Get a connection to the database
	 * Must be closed by implementation
	 * @return null if no connection can be made
	 */
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DatabaseReader getReader() {
		return reader;
	}

	public void setReader(DatabaseReader reader) {
		this.reader = reader;
	}

	public DatabaseWriter getWriter() {
		return writer;
	}

	public void setWriter(DatabaseWriter writer) {
		this.writer = writer;
	}

	public void createTables(Connection connection) {
		PreparedStatement preStatement;
		try {
			preStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS data(seatID VARCHAR(10) NOT NULL, name VARCHAR(40) NOT NULL, notes VARCHAR(500) NOT NULL)");
			preStatement.executeUpdate();
			System.out.println("Created new table.");
		} catch (SQLException e) {
			System.err.println("Error creating prepared statment!");
			e.printStackTrace();
			return;
		}
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println("Error closing connection! " + e.getMessage());
		}
	}
	
}
