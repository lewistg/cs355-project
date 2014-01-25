package cs355.solution;

import cs355.model.Canvas;
import cs355.model.Shape;
import cs355.model.Vector2D;

import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/25/14
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseSelectionBehavior extends MouseShapeBuilderStrategy
{
    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        Canvas canvas = Canvas.getInstance();
        Shape selectedShape = canvas.getSelectedShape(new Vector2D(mouseEvent.getPoint()), 4);
        if(selectedShape != null)
            System.out.println("Hit: " + selectedShape.toString());
        else
            System.out.println("Nothing..");
    }
}
