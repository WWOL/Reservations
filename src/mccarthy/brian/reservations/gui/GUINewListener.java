package mccarthy.brian.reservations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import mccarthy.brian.reservations.Reservation;
import mccarthy.brian.reservations.Reservations;

/**
 *
 * @author Brian McCarthy
 */
public class GUINewListener implements ActionListener {
    
    /**
     * Check validity of data
     * @return true if passed check
     */
    public boolean check() {
        String seatID = GUINew.txt_seatID.getText();
        String name = GUINew.txt_name.getText();
        String notes = GUINew.txt_notes.getText();
        if (seatID.trim().equals("")) {
            return false;
        }
        if (name.trim().equals("")) {
            return false;
        }
        if (notes.trim().equals("")) {
            return false;
        }
        if (seatID.contains(" ")) {
            return false;
        }
        for (Reservation reservation : Reservations.getInstance().getReservations()) {
            if (reservation.getSeatID().equals(seatID)) {
                return false;
            }
        }
    
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(GUINew.btn_save)) {
            boolean pass = check();
            JOptionPane.showMessageDialog(null, pass ? "All tests past. Saving." : "Tests failed! Check input!");
            if (!pass) {
                return;
            }
            Reservation reservation = new Reservation(GUINew.txt_seatID.getText(), GUINew.txt_name.getText(), GUINew.txt_notes.getText());
            Reservations.getInstance().addReservation(reservation);
            GUINew.txt_name.setText("");
            GUINew.txt_seatID.setText("");
            GUINew.txt_notes.setText("");
        } else if (event.getSource().equals(GUINew.btn_check)) {
            boolean pass = check();
            JOptionPane.showMessageDialog(null, pass ? "All tests past." : "Tests failed! Check input!");
        }
    }
    
}
