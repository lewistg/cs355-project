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
    /**The actual square is stored as rectangle underneath*/
    private Vector2D _lowerLeft;
    /**The width of the rectangle*/
    private double _size;

    /**
     * Constructor
     */
    public Square(Point lowerLeft, double size, Color color)
    {
        super(color);
        _lowerLeft = new Vector2D(lowerLeft);
        _size = size;
    }

    /**
     * Getter for lower left corner
     */
    public Vector2D getLowerLeft()
    {
        Vector2D center = getCenter();
        return _lowerLeft;
    }

    /**
     * Getter for the width
     */
    public double size()
    {
        return _size;
    }
}
