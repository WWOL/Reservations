/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        if (event.getSource().equals(GUIView.btn_info)) {
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
        }
    }
    
    private void appendInfo(Reservation reservation) {
        GUIView.txt_info.append("Seat ID: " + reservation.getSeatID() + "\nName: " + reservation.getName() + "\nNotes: " + reservation.getNotes() + "\n");
    }
    
}
