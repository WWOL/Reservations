package mccarthy.brian.reservations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mccarthy.brian.reservations.Reservation;
import mccarthy.brian.reservations.Reservations;

/**
 *
 * @author Brian McCarthy
 */
public class GUIViewListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(GUIView.btn_seatID)) {
            String seatID = GUIView.txt_seatID.getText();
            if (seatID.trim().equals("") || seatID.contains(" ")) {
                GUIView.txt_info.append("The seat " + seatID + " is invalid! \nPlease try again.\n");
                return;
            }
            Reservation reservation = null;
            for (Reservation r : Reservations.getInstance().getReservations()) {
                if (r.getSeatID().equals(seatID)) {
                    reservation = r;
                }
            }
            if (reservation == null) {
                GUIView.txt_info.append("The seat " + seatID + " could not be found! \nPlease try again.\n");
                return;
            }
            appendInfo(reservation);
        } else if (event.getSource().equals(GUIView.btn_all)) {
            if (Reservations.getInstance().getReservations().isEmpty()) {
                GUIView.txt_info.append("There are no reservation at this time!\n");
            }
            for (Reservation reservation : Reservations.getInstance().getReservations()) {
                appendInfo(reservation);
            }
        } else if (event.getSource().equals(GUIView.btn_name)) {
        	String name = GUIView.txt_seatID.getText();
            if (name.trim().equals("")) {
                GUIView.txt_info.append("The name " + name + " is invalid! \nPlease try again.\n");
                return;
            }
            Reservation reservation = null;
            for (Reservation r : Reservations.getInstance().getReservations()) {
                if (r.getName().equals(name)) {
                    reservation = r;
                }
            }
            if (reservation == null) {
                GUIView.txt_info.append("The name " + name + " could not be found! \nPlease try again.\n");
                return;
            }
            appendInfo(reservation);
        } else if (event.getSource().equals(GUIView.btn_notes)) {
        	String notes = GUIView.txt_seatID.getText();
            if (notes.trim().equals("")) {
                GUIView.txt_info.append("The note " + notes + " is invalid! \nPlease try again.\n");
                return;
            }
            Reservation reservation = null;
            for (Reservation r : Reservations.getInstance().getReservations()) {
                if (r.getNotes().equals(notes)) {
                    reservation = r;
                }
            }
            if (reservation == null) {
                GUIView.txt_info.append("The note " + notes + " could not be found! \nPlease try again.\n");
                return;
            }
            appendInfo(reservation);
        }
    }
    
    private void appendInfo(Reservation reservation) {
        GUIView.txt_info.append("Seat ID: " + reservation.getSeatID() + "\nName: " + reservation.getName() + "\nNotes: " + reservation.getNotes() + "\n\n\n");
    }
    
}
