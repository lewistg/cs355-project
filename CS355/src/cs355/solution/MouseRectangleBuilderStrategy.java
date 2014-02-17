package cs355.solution;

import cs355.model.Canvas;
import cs355.model.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MouseRectangleBuilderStrategy extends MouseShapeBuilderStrategy
{
    /**Points defining the current rectangle*/
    Point _p0;
    Point _p1;


    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
         _p0 = WorldToScreen.getInstance().getInWorldCoords(mouseEvent.getPoint());
         _p1 = _p0;

        Point lowerLeft = getLowerLeft();
        double width = getWidth();
        double height = getHeight();

        Rectangle rectangle = new Rectangle(lowerLeft, width, height, cs355.model.Context.getInstance().getCurrentColor());
        Canvas.getInstance().addShape(rectangle);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        _p1 = WorldToScreen.getInstance().getInWorldCoords(mouseEvent.getPoint());

        Point lowerLeft = getLowerLeft();
        double width = getWidth();
        double height = getHeight();

        Rectangle rectangle = new Rectangle(lowerLeft, width, height, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.Canvas.getInstance().setLastShapeAdded(rectangle);
    }

    /**
     * Calculates the lower left corner of the rectangle
     * @return
     */
    private Point getLowerLeft()
    {
        double minX = Math.min(_p0.getX(), _p1.getX());
        double minY = Math.min(_p0.getY(), _p1.getY());
        Point lowerLeft = new Point((int) minX, (int) minY);

        return lowerLeft;
    }

    /**
     * Gets the width of the rectangle
     * @return
     */
    private double getWidth()
    {
        return Math.abs(_p0.getX() - _p1.getX());
    }

    /**
     * Gets the height of the rectangle
     */
    private double getHeight()
    {
        return Math.abs(_p0.getY() - _p1.getY());
    }
}
