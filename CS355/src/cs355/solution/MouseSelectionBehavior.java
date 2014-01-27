package cs355.solution;

import cs355.model.*;
import cs355.model.Canvas;
import cs355.model.Shape;

import java.awt.*;
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
    /**The currently selected shape*/
    private Shape _selectedShape;
    /**The first click point*/
    Point _p0;

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        Canvas canvas = Canvas.getInstance();
        _selectedShape = canvas.getSelectedShape(new Vector2D(mouseEvent.getPoint()), 4);
        if(_selectedShape != null)
            System.out.println("Hit: " + _selectedShape.toString());
        else
            System.out.println("Nothing..");

        _p0 = mouseEvent.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        if(_selectedShape == null)
            return;

        Point p1 = mouseEvent.getPoint();
        double xOffset = p1.getX() - _p0.getX();
        double yOffset = p1.getY() - _p0.getY();
        _p0 = p1;
        DrawingFacade.getInstance().translateShape(_selectedShape, new Vector2D(xOffset, yOffset));
    }
}
