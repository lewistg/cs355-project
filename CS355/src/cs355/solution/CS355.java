/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.solution;

import cs355.GUIFunctions;
import cs355.view.LabOneViewRefresher;

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
        LabOneController controller = LabOneController.getInstance();
        LabOneViewRefresher viewRefresher = LabOneViewRefresher.getInstance();
        GUIFunctions.createCS355Frame(controller, viewRefresher, controller, controller);
        controller.init();
        
        GUIFunctions.refresh();        
    }
}