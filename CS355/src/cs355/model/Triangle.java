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
    private ArrayList<Vector2D> _vertices;

    /**
     * Constructor
     */
    public Triangle(Point p0, Point p1, Point p2, Color color)
    {
        super(color);
        _vertices = new ArrayList<Vector2D>();
        _vertices.add(new Vector2D(p0));
        _vertices.add(new Vector2D(p1));
        _vertices.add(new Vector2D(p2));

        Vector2D centroidWC = new Vector2D(0, 0);
        for(Vector2D p : _vertices)
            centroidWC = Vector2D.add(centroidWC, p);
        centroidWC.scale(1.0 / 3.0);
        setCenter(centroidWC);

        for(Vector2D p : _vertices)
            p.sub(centroidWC);

        Vector2D objToWorldTrans = new Vector2D(centroidWC.getX(), centroidWC.getY());
        ObjToWorldTransform objToWorldTransform = new ObjToWorldTransform(objToWorldTrans, 0.0);
        setObjToWorldTransform(objToWorldTransform);
    }

    /**
     * Getter for the vertices
     */
    public ArrayList<Vector2D> getVertices()
    {
        return _vertices;
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance)
    {
        ArrayList<Double> barryCoords = getBarrycentric(worldCoord);
        for(Double coord : barryCoords)
        {
            if(coord < 0)
                return false;
        }

        return true;
    }

    /**
     * Calculates the barycentric coordinates of a point
     */
    private ArrayList<Double> getBarrycentric(Vector2D p)
    {
        ArrayList<Double> coords = new ArrayList<>();

        double denom = (_vertices.get(0).getY() - _vertices.get(2).getY()) *
                            (_vertices.get(1).getX() - _vertices.get(2).getX()) +
                            (_vertices.get(1).getY() - _vertices.get(2).getY()) *
                            (_vertices.get(2).getX() - _vertices.get(0).getX());
        double b1 = (p.getY() - _vertices.get(2).getY()) * (_vertices.get(1).getX() - _vertices.get(2).getX()) +
                (_vertices.get(1).getY() - _vertices.get(2).getY()) * (_vertices.get(2).getX() - p.getX());
        b1 /= denom;

        double b2 = (p.getY() - _vertices.get(0).getY()) * (_vertices.get(2).getX() - _vertices.get(0).getX()) +
                (_vertices.get(2).getY() - _vertices.get(0).getY()) * (_vertices.get(0).getX() - p.getX());
        b2 /= denom;

        double b3 = (p.getY() - _vertices.get(1).getY()) * (_vertices.get(0).getX() - _vertices.get(1).getX()) +
                (_vertices.get(0).getY() - _vertices.get(1).getY()) * (_vertices.get(1).getX() - p.getX());
        b3 /= denom;

        coords.add(b1);
        coords.add(b2);
        coords.add(b3);

        return coords;
    }

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        return _vertices;
    }

    public double getHeight()
    {
        double maxY = Math.max(_vertices.get(0).getY(), _vertices.get(1).getY());
        maxY = Math.max(maxY, _vertices.get(2).getY());
        return maxY;
    }
}
