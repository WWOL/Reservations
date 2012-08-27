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
	
	public static Reservations getInstance() {
		return instance;
	}
	
	public static DatabaseConnector getDB() {
		return db;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
        @SuppressWarnings("CallToThreadDumpStack")
        public void addReservation(Reservation reservation) {
		reservations.add(reservation);
		getDB().getWriter().writeReservation(reservation);
		System.out.println("Added reservation: " + reservation.getSeatID());
                System.out.println("=======================================================");
                System.out.println("addReservationStack");
                Error e = new Error("TEST ERROR. Tell Brian McCarthy if you ever see this in a real enviroment!");
                e.printStackTrace();
                System.out.println("=======================================================");
	}
	
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
	
	public void shutdown() {
		for (Shutdown shutdown : shutdownListeners) {
			shutdown.shutdown();
		}
	}
	
	public void registerShutdownListener(Shutdown shutdown) {
		shutdownListeners.add(shutdown);
		System.out.println("Regested new shutdown listener: " + shutdown.getClass().getName());
	}
	
}
