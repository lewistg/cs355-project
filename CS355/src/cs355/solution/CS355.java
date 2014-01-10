/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.GUIFunctions;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author [your name here]
 */
public class CS355 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	// Fill in the parameters below with your controller, view, 
    	//   mouse listener, and mouse motion listener
        LabOneController controller = new LabOneController();
        LabOneViewRefresher viewRefresher = new LabOneViewRefresher();
        LabOneMouseListener mouseListener = new LabOneMouseListener();
        LabOneMouseMotionListener mouseMotionListener = new LabOneMouseMotionListener();
        GUIFunctions.createCS355Frame(controller,viewRefresher,mouseListener,mouseMotionListener);
        
        GUIFunctions.refresh();        
    }
}