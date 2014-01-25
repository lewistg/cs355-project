package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Line extends Shape
{
    /**The start point*/
    private Vector2D _p0;
    /**The end point*/
    private Vector2D _p1;

    /**
     * Constructor
     */
    public Line(Point p1, Point p0, Color color)
    {
        super(color);
        _p0 = new Vector2D(p0);
        _p1 = new Vector2D(p1);
    }

    /**
     * Getter for start point
     */
    public Vector2D getStartPoint()
    {
        return _p0;
    }

    /**
     * Sets the start point
     */
    public void setStartPoint(Point p0)
    {
        _p0 = new Vector2D(p0);
    }

    /**
     * Getter for the end point
     */
    public Vector2D getEndPoint()
    {
        return _p1;
    }

    /**
     * Sets the end point
     */
    public void setEndPoint(Point p1)
    {
        _p1 = new Vector2D(p1);
    }

    /**
     * To-string method
     */
    public String toString()
    {
        String lineStr = "Line from: " + _p0.toString() + " to " + _p1.toString();
        return lineStr;
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
