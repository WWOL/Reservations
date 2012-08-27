/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mccarthy.brian.reservations.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Brian McCarthy
 */
public class GUIInputListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(GUIInputGetter.btn_new)) {
            GUIBase.getGUINew().setVisible(true);
        } else if (event.getSource().equals(GUIInputGetter.btn_remove)) {
            GUIBase.getGUIRemove().setVisible(true);
        } else if (event.getSource().equals(GUIInputGetter.btn_view)) {
            GUIBase.getGUIView().setVisible(true);
        }
    }
    
}
