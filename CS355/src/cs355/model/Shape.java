package cs355.model;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Shape
{
    /**The shape's color*/
    private Color _color;
    /**The shape's center*/
    private Point2D _center;

    /**
     * Constructor
     * @param color
     */
    public Shape(Color color)
    {
        _color = color;
    }

    /**
     * Setter for the color
     */
    public void setColor(Color color)
    {
        _color = color;
    }

    /**
     * Getter for the color
     * @return
     */
    public Color getColor()
    {
        return _color;
    }

    /**
     * Getter for the center
     */
    public Point2D getCenter()
    {
        return _center;
    }
}
