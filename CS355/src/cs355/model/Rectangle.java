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
    /**The width of the rectangle*/
    double _width;
    /**The height of the rectangle*/
    double _height;

    /**
     * Constructor
     */
    public Rectangle(Point lowerLeft, double width, double height, Color color)
    {
        super(color, new Vector2D(lowerLeft.getX() + (width/2.0), lowerLeft.getY() + (height/2.0)));
        _width = width;
        _height = height;
    }

    /**
     * Getter for the upper left
     */
    public Vector2D getLowerLeft()
    {
        Vector2D center = getCenter();
        Vector2D cornerOffset = new Vector2D(-_width / 2.0, -_height / 2.0);
        Vector2D calculatedLowerLeft = Vector2D.add(center, cornerOffset);
        return calculatedLowerLeft;
    }

    /**
     * Getter for the width
     */
    public double getWidth()
    {
        return _width;
    }

    /**
     * Width setter
     */
    public void setWidth(double width)
    {
        assert(width >= 0);
        _width = width;
    }

    /**
     * Getter for the height
     */
    public double getHeight()
    {
        return _height;
    }

    /**
     * Height setter
     * @return
     */
    public void setHeight(double height)
    {
        assert(height >= 0);
        _height = height;
    }

    public String toString()
    {
        String rect = "Lower lower left: " + getLowerLeft().toString() + ", width = " + _width + ", height = "  + _height;
        return rect;
    }
}
