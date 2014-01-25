package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Square extends Shape {
    /**The width of the rectangle*/
    private double _size;

    /**
     * Constructor
     */
    public Square(Point lowerLeft, double size, Color color)
    {
        super(color, new Vector2D(lowerLeft.getX() + (size/2.0), lowerLeft.getY() + (size/2.0)));
        _size = size;
    }

    /**
     * Getter for lower left corner
     */
    public Vector2D getLowerLeft()
    {
        Vector2D center = getCenter();
        Vector2D cornerOffset = new Vector2D(-_size / 2.0, -_size / 2.0);
        Vector2D calculatedLowerLeft = Vector2D.add(center, cornerOffset);
        return calculatedLowerLeft;
    }

    /**
     * Getter for the width
     */
    public double size()
    {
        return _size;
    }
}
