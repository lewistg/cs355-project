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
        _selectedShape = canvas.selectShape(new Vector2D(mouseEvent.getPoint()), 4);
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

        rotatingSelected(mouseEvent);

        /*Point p1 = mouseEvent.getPoint();
        _p0 = p1;
        DrawingFacade.getInstance().translateSelectedShape(new Vector2D(xOffset, yOffset));*/
    }

    private void rotatingSelected(MouseEvent mouseEvent)
    {
        Point p1 = mouseEvent.getPoint();
        Vector2D center = _selectedShape.getCenter();
        ObjToWorldTransform objToWorldTransform = _selectedShape.getObjToWorldTransform();
        Vector2D centerWC = objToWorldTransform.getWorldCoords(center);
        double xOffset = p1.getX() - centerWC.getX();
        double yOffset = p1.getY() - centerWC.getY();

        Vector2D v0 = new Vector2D(_p0);
        v0.normalize();
        Vector2D v1 = new Vector2D(xOffset, yOffset);
        v1.normalize();
        double theta = Math.asin(Vector2D.dot(v0, v1));

        //double theta = Math.atan2(yOffset, xOffset);

        System.out.println("Theta: " + Math.toDegrees(theta));

        DrawingFacade.getInstance().rotateSelectedShape(theta);
    }
}
