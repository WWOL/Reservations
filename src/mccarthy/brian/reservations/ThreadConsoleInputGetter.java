package mccarthy.brian.reservations;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * A test class that accepts input from the command line, an early replacement for the GUI
 * Unused in current code.
 * @author Brian McCarthy
 *
 */
@Deprecated
public class ThreadConsoleInputGetter extends Thread implements Shutdown {

	boolean running;

	public ThreadConsoleInputGetter() {
		running = true;
		Reservations.getInstance().registerShutdownListener(this);
	}

	public void run() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while((line = reader.readLine()) != null) {
				try {
					String[] args = line.split(" ");
					if (args[0].equalsIgnoreCase("new")) {
						StringBuilder sb = new StringBuilder();
						for (int i = 3; i < args.length; i++) {
							sb.append(args[i]);
							sb.append(" ");
						}
						//sb.setCharAt(sb.length() - 1, '\u0000');
						Reservation reservation = new Reservation(args[1], args[2], sb.substring(0, sb.length() - 1));
						Reservations.getInstance().addReservation(reservation);
					} else if (args[0].equalsIgnoreCase("list")) {
						System.out.println("Listing.");
						for (Reservation reservation : Reservations.getInstance().getReservations()) {
							StringBuilder sb = new StringBuilder();
							sb.append("Seat: ");
							sb.append(reservation.getSeatID());
							sb.append(", ");
							sb.append("Name: ");
							sb.append(reservation.getName());
							sb.append(", ");
							sb.append("Notes: ");
							sb.append(reservation.getNotes());
							sb.append(".\n");
							sb.append("=======================================================================");
							System.out.println(sb.toString());
						}
					} else if (args[0].equalsIgnoreCase("remove")) {
						Reservations.getInstance().removeReservation(args[1]);
					}
				} catch (Exception e) {
					System.out.println("Unknown Command!");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		running = false;
	}

}
