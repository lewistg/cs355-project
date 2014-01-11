package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rectangle extends Shape
{
    /**Lower left corner*/
    Point2D _lowerLeft;
    /**Upper right corner*/
    Point2D _upperRight;

    /**
     * Constructor
     */
    public Rectangle(Point p0, Point p1, Color color)
    {
        super(color);
        _lowerLeft = new Point2D(Math.min(p0.getX(), p1.getX()), Math.min(p0.getY(), p1.getY()));
        _upperRight = new Point2D(Math.max(p0.getX(), p1.getX()), Math.max(p0.getY(), p1.getY()));
    }

    /**
     * Getter for the upper left
     */
    public Point2D getLowerLeft()
    {
        return _lowerLeft;
    }

    /**
     * Getter for the lower right
     */
    public Point2D getUpperRight()
    {
        return _upperRight;
    }

    /**
     * Getter for the width
     */
    public double getWidth()
    {
        return Math.abs(_upperRight.getX() - _lowerLeft.getX());
    }

    /**
     * Getter for the height
     */
    public double getHeight()
    {
        return Math.abs(_upperRight.getY() - _lowerLeft.getY());
    }

    public String toString()
    {
        String rect = "Lower lower left: " + _lowerLeft.toString() + ", Upper right: " + _upperRight.toString();
        return rect;
    }
}
