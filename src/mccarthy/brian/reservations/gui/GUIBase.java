package mccarthy.brian.reservations.gui;

import javax.swing.UIManager;

/**
 * Base of GUI that holds singletons of the different forms
 * This means that there can only be one of each form at a time  
 * @author Brian McCarthy
 */
public class GUIBase {
    
    private static GUIInputGetter inputGetter;
    private static GUINew guiNew;
    private static GUIView guiView;
    private static GUIRemove guiRemove;
    
    public void show() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not set Look and Feel to " + UIManager.getSystemLookAndFeelClassName());
        }
        inputGetter = new GUIInputGetter();
        inputGetter.setVisible(true);
        guiNew = new GUINew();
        guiNew.setVisible(false);
        guiView = new GUIView();
        guiView.setVisible(false);
        guiRemove = new GUIRemove();
        guiRemove.setVisible(false);
        
        GUIInputListener inputListener = new GUIInputListener();
        GUIInputGetter.btn_new.addActionListener(inputListener);
        GUIInputGetter.btn_view.addActionListener(inputListener);
        GUIInputGetter.btn_remove.addActionListener(inputListener);
        
        GUINewListener newListener = new GUINewListener();
        GUINew.btn_save.addActionListener(newListener);
        GUINew.btn_check.addActionListener(newListener);
        
        GUIViewListener viewListener = new GUIViewListener();
        GUIView.btn_seatID.addActionListener(viewListener);
        GUIView.btn_all.addActionListener(viewListener);
        GUIView.btn_seatID.addActionListener(viewListener);
        GUIView.btn_name.addActionListener(viewListener);
        GUIView.btn_notes.addActionListener(viewListener);
        
    }
    
    public static GUIInputGetter getInputGetter() {
        return inputGetter;
    }
    
    public static GUINew getGUINew() {
        return guiNew;
    }
    
    public static GUIView getGUIView() {
        return guiView;
    }
    
    public static GUIRemove getGUIRemove() {
        return guiRemove;
    }
    
}
