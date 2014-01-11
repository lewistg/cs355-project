package cs355.model;

import java.awt.*;
import java.util.ArrayList;

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
    private Point _p0;
    /**The end point*/
    private Point _p1;

    /**
     * Constructor
     */
    public Line(Color color, Point p0, Point p1)
    {
        super(color);
        _p0 = p0;
        _p1 = p1;
    }

    /**
     * Getter for start point
     */
    public Point getStartPoint()
    {
        return _p0;
    }

    /**
     * Sets the start point
     */
    public void setStartPoint(Point p0)
    {
        _p0 = p0;
    }

    /**
     * Getter for the end point
     */
    public Point getEndPoint()
    {
        return _p1;
    }

    /**
     * Sets the end point
     */
    public void setEndPoint(Point p1)
    {
        _p1 = p1;
    }

    /**
     * To-string method
     */
    public String toString()
    {
        String lineStr = "Line from: " + _p0.toString() + " to " + _p1.toString();
        return lineStr;
    }
}
