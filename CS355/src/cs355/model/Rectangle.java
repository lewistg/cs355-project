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
    /**Lower left corner (Note: appears as upper left on the screen)*/
    Point2D _lowerLeft;
    /**The width of the rectangle*/
    double _width;
    /**The height of the rectangle*/
    double _height;

    /**
     * Constructor
     */
    public Rectangle(Point p0, Point p1, Color color)
    {
        super(color);
        _lowerLeft = new Point2D(Math.min(p0.getX(), p1.getX()), Math.min(p0.getY(), p1.getY()));
        Point2D upperRight = new Point2D(Math.max(p0.getX(), p1.getX()), Math.max(p0.getY(), p1.getY()));

        _width = upperRight.getX() - _lowerLeft.getX();
        _height = upperRight.getY() - _lowerLeft.getY();
    }

    /**
     * Getter for the upper left
     */
    public Point2D getLowerLeft()
    {
        return _lowerLeft;
    }

    /**
     * Getter for the width
     */
    public double getWidth()
    {
        return _width;
    }

    /**
     * Getter for the height
     */
    public double getHeight()
    {
        return _height;
    }

    public String toString()
    {
        String rect = "Lower lower left: " + _lowerLeft.toString() + ", width = " + _width + ", height = "  + _height;
        return rect;
    }
}
