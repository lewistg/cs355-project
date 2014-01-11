package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Circle extends Shape
{
    /**Center of the circle*/
    Point2D _center;
    /**The radius of the circle*/
    double _radius;

    /**
     * Constructor
     */
    public Circle(Point2D center, double radius, Color color)
    {
        super(color);
        assert(radius >= 0);
        _center = center;
        _radius = radius;
    }

    /**
     * Getter for the center
     */
    public Point2D getCenter()
    {
        return _center;
    }

    /**
     * Getter for the radius
     */
    public double getRadius()
    {
        return _radius;
    }
}
