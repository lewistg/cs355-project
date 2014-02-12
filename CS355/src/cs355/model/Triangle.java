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
        super(color, new Vector2D(0,0));
        _vertices = new ArrayList<Vector2D>();
        _vertices.add(new Vector2D(p0));
        _vertices.add(new Vector2D(p1));
        _vertices.add(new Vector2D(p2));

        Vector2D centroidWC = new Vector2D(0, 0);
        for(Vector2D p : _vertices)
            centroidWC = Vector2D.add(centroidWC, p);
        centroidWC.scale(1.0 / 3.0);

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
        Vector2D objCoords = getObjToWorldTransform().getObjectCoords(worldCoord);
        ArrayList<Double> barryCoords = getBarrycentric(objCoords);
        for(Double coord : barryCoords)
        {
            if(coord < 0)
                return false;
        }

        return true;
    }

    public Vector2D getCentroid()
    {
        Vector2D centroid = new Vector2D(0, 0);
        for(Vector2D p : _vertices)
            centroid = Vector2D.add(centroid, p);
        centroid.scale(1.0 / 3.0);

        return centroid;
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

    public Vector2D getTopPoint()
    {
        Vector2D topPoint = (_vertices.get(0).getY() < _vertices.get(1).getY()) ? _vertices.get(0) : _vertices.get(1);
        if(topPoint.getY() > _vertices.get(2).getY())
            topPoint = _vertices.get(2);

        return new Vector2D(topPoint);
    }

    /**
     * Moving the bounding box corner edits the shape
     * @param boundingBoxCornerIndex
     * @param newCornerPosWC
     * @return
     */
    public int moveBoundingBoxCorner(int boundingBoxCornerIndex, Vector2D newCornerPosWC)
    {
        assert(boundingBoxCornerIndex >= 0 && boundingBoxCornerIndex < 3);
        ObjToWorldTransform t = getObjToWorldTransform();
        _vertices.set(boundingBoxCornerIndex, t.getObjectCoords(newCornerPosWC));

        Vector2D newCentroidWC = new Vector2D(0, 0);
        for(Vector2D p : _vertices)
            newCentroidWC.add(t.getWorldCoords(p));
        newCentroidWC.scale(1.0/3.0);
        t.setObjToWorldTrans(newCentroidWC);

        // recenter the object about the origin
        Vector2D newCentroid = new Vector2D(0, 0);
        for(Vector2D p : _vertices)
            newCentroid.add(p);
        newCentroid.scale(1.0/3.0);

        for(Vector2D p : _vertices)
                p.sub(newCentroid);

        return boundingBoxCornerIndex;
    }

    public static void main(String[] args)
    {
        Triangle tri = new Triangle(new Point(10, 20), new Point(30, 40), new Point(20, 50), Color.ORANGE);
        System.out.println("" + tri.pointInShape(new Vector2D(20, 80),0));
    }
}
