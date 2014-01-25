package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Vector2D
{
    /**
     * The coordinates
     */
    double _x;
    double _y;

    /**
     * Constructor
     */
    public Vector2D(double x, double y)
    {
        _x = x;
        _y = y;
    }

    /**
     * Constructor
     */
    public Vector2D(Point point)
    {
        _x = point.getX();
        _y = point.getY();
    }

    /**
     * X-getter
     */
    public double getX()
    {
        return _x;
    }

    /**
     * X-setter
     */
    public void setX(double x)
    {
        _x = x;
    }

    /**
     * Y-getter
     */
    public double getY()
    {
        return _y;
    }

    /**
     * Y-setter
     */
    public void setY(double y)
    {
        _y = y;
    }

    /**
     * Returns the sum of the vectors
     * @param v0
     * @param v1
     * @return
     */
    public static Vector2D add(Vector2D v0, Vector2D v1)
    {
        return new Vector2D(v0.getX() + v1.getX(), v0.getX() + v1.getX());
    }

    /**
     * To-string
     */
    public String toString()
    {
        return "(x = " + _x + ", y = " + _y + ")";
    }
}
