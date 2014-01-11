package cs355.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Triangle extends Shape
{
    /**
     * Corners of the triangle
     */
    private ArrayList<Point2D> _vertices;

    /**
     * Constructor
     */
    public Triangle(Point p0, Point p1, Point p2, Color color)
    {
        super(color);
        _vertices = new ArrayList<Point2D>();
        _vertices.add(new Point2D(p0));
        _vertices.add(new Point2D(p1));
        _vertices.add(new Point2D(p2));
    }

    /**
     * Getter for the vertices
     */
    public ArrayList<Point2D> getVertices()
    {
        return _vertices;
    }
}
