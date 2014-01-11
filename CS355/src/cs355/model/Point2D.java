package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class Point2D
{
    /**
     * The coordinates
     */
    double _x;
    double _y;

    /**
     * Constructor
     */
    public Point2D(double x, double y)
    {
        _x = x;
        _y = y;
    }

    /**
     * Constructor
     */
    public Point2D(Point point)
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
     * To-string
     */
    public String toString()
    {
        return "(x = " + _x + ", y = " + _y + ")";
    }
}
