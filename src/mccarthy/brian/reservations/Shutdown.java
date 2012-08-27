package mccarthy.brian.reservations;

/**
 * Implement this and register with Reservations.registerShutdownListener(Shutdown) to let an object clean up on shutdown
 * @author Brian McCarthy
 *
 */
public interface Shutdown {

	public void shutdown();
}
