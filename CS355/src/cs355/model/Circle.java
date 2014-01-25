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
    /**The radius of the circle*/
    double _radius;

    /**
     * Constructor
     */
    public Circle(Vector2D center, double radius, Color color)
    {
        super(color, center);
        assert(radius >= 0);
        _radius = radius;
    }

    /**
     * Getter for the radius
     */
    public double getRadius()
    {
        return _radius;
    }
}
