package mccarthy.brian.reservations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A connection to the database (MySQL)
 * Contains references to the reader and writer
 * @see DatabaseReader
 * @see DatabaseWriter
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

	/**
	 * Constructor, also sets up reader and writer
	 * @param url the JDBC url to the database
	 * @param user the user name of the database
	 * @param pass the password for the database
	 */
	public DatabaseConnector(String url, String user, String pass) {
		this.url = url;
		this.user = user;
		this.pass = pass;
		reader = new DatabaseReader();
		writer = new DatabaseWriter();
	}
	
	/**
	 * Creates a connection, sets default tables (if needed) and loads all reservations from the database reader.
	 * @see DatabaseReader
	 */
	public void connect() {
		try {
			connection = getConnection();
		} catch (Exception e) {
			System.err.println("Error creating connection! Cannot continue!");
			e.printStackTrace();
			Reservations.getInstance().shutdown();
		}
		createTables(connection);
		reader.loadAll();
		closeConnection(connection);
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
	
	/**
	 * Get the database reader to read reservations directly from the database
	 * @return {@link DatabaseReader} for this connection
	 */
	public DatabaseReader getReader() {
		return reader;
	}

	/**
	 * Sets the instance of database reader
	 * @param reader the {@link DatabaseReader} to set
	 */
	public void setReader(DatabaseReader reader) {
		this.reader = reader;
	}

	/**
	 * Get the database writer to write new reservations or update old ones
	 * @return the {@link DatabaseWriter} for this connection
	 */
	public DatabaseWriter getWriter() {
		return writer;
	}

	/**
	 * Set the database writer
	 * @param writer the {@link DatabaseWriter} to set
	 */
	public void setWriter(DatabaseWriter writer) {
		this.writer = writer;
	}

	/**
	 * Create the tables needed for Reservations to work
	 * Only creates tables if they don't exist
	 * @param connection the connection to the database to use
	 */
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
	
	/**
	 * Closes the given connection
	 * This is important as unclosed connections are a security risk and each database has a limited number of concurrent connections allowed
	 * @param connection
	 */
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println("Error closing connection! " + e.getMessage());
		}
	}
	
}
