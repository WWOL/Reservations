package mccarthy.brian.reservations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import mccarthy.brian.reservations.gui.GUIBase;

/**
 * Reservations app
 * For Tyler and Jordan
 * @author Brian McCarthy
 *
 */
public class Reservations {

	private static Reservations instance;
	private static DatabaseConnector db;
	private List<Shutdown> shutdownListeners;
	private List<Reservation> reservations;
	public Properties props;

	/**
	 * Main method, set up lists, init properties, set up database connection and connect, show GUI
	 * @param args
	 */
	public static void main(String[] args) {
		Reservations reservations = new Reservations();
		instance = reservations;
		instance.shutdownListeners = new ArrayList<Shutdown>();
		instance.reservations = new ArrayList<Reservation>();
		instance.initProps();
		DatabaseConnector database = new DatabaseConnector(instance.props.getProperty("URL","jdbc:mysql://localhost:3306/Reservations"), instance.props.getProperty("user", "root"), instance.props.getProperty("pass", "rooy"));
		db = database;
		database.connect();
		//new ThreadConsoleInputGetter().start();
		new GUIBase().show();
	}

	/**
	 * Create the new properties file if needed and write defaults to it, load props from file
	 */
	public void initProps() {
		File file = new File("db.properties");
		if (!file.exists()) {
			try {
				file.createNewFile();
				FileWriter writer = new FileWriter(file);
				writer.write("url=jdbc:mysql://localhost:3306/Reservations\n");
				writer.write("user=root\n");
				writer.write("pass=root\n");
				writer.flush();
				writer.close();
			} catch (Exception e) {
				System.out.println("Could not create db.properties!");
			}
		}
		props = new Properties();
		try {
			props.load(new FileInputStream("db.properties"));
		} catch (Exception e) {
			System.out.println("Failed to load props!");
		}
	}

	/**
	 * Get the current instance
	 * @return {@link Reservations} instance
	 */
	public static Reservations getInstance() {
		return instance;
	}

	/**
	 * Get the database connection
	 * @return singleton {@link DatabaseConnector}
	 */
	public static DatabaseConnector getDB() {
		return db;
	}

	/**
	 * Get this instances reservation list
	 * @return instance of List<Reservation> 
	 */
	public List<Reservation> getReservations() {
		return reservations;
	}

	/**
	 * Add a reservation to the list and write to the database
	 * @param reservation {@link Reservation} to add
	 */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		getDB().getWriter().writeReservation(reservation);
		System.out.println("Added reservation: " + reservation.getSeatID());
		//Debugging call
		/*System.out.println("=======================================================");
		System.out.println("addReservationStack");
		Error e = new Error("TEST ERROR. Tell Brian McCarthy if you ever see this in a real enviroment!");
		e.printStackTrace();
		System.out.println("=======================================================");*/
	}

	/**
	 * Remove a reservation from the list and from the database
	 * NOTE: This only need an seatID as that is the primary key in the database so only this is needed
	 * Also from the GUI it is impossible to get an instance of {@link Reservation} without creating a blank instance and just adding the seatID using setSeatID(String) 
	 * @param seatID seatID of {@link Reservation} to remove
	 */
	public void removeReservation(String seatID) {
		for (Reservation reservation : reservations) {
			if (reservation.getSeatID().equals(seatID)) {
				reservations.remove(reservation);
				break;
			}
		}
		getDB().getWriter().removeReservation(seatID);
		System.out.println("Removed reservation: " + seatID);
	}

	/**
	 * Call all the shutdown listeners
	 * This lets threads etc shut down properly
	 */
	public void shutdown() {
		for (Shutdown shutdown : shutdownListeners) {
			shutdown.shutdown();
		}
	}

	/**
	 * Register a new shutdown listener
	 * @param shutdown instance of {@link Shutdown}
	 */
	public void registerShutdownListener(Shutdown shutdown) {
		shutdownListeners.add(shutdown);
		System.out.println("Regested new shutdown listener: " + shutdown.getClass().getName());
	}

}
