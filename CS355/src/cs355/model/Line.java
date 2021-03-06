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

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        ArrayList<Vector2D> endPoints = new ArrayList<>();
        endPoints.add(_p0);
        endPoints.add(_p1);
        return endPoints;
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance) {
        worldCoord = getObjToWorldTransform().getObjectCoords(worldCoord);

        Vector2D dir = Vector2D.sub(_p1, _p0);
        double mag = dir.getMag();
        dir.normalize();
        Vector2D normal = dir.perpVector();

        Vector2D toPoint = Vector2D.sub(worldCoord, _p0);
        double d = -Vector2D.dot(normal, _p0);

        double distToLine = Vector2D.dot(worldCoord, normal) + d;
        if(Math.abs(distToLine) > tolerance)
            return false;

        double projDist = Vector2D.dot(dir, toPoint);
        if(projDist < 0 || projDist > mag)
            return false;

        return true;
    }

    public int moveBoundingBoxCorner(int boundingBoxCornerIndex, Vector2D newCornerPosWC)
    {
        assert(boundingBoxCornerIndex >= 0 && boundingBoxCornerIndex < 2);

        Vector2D newEndPoint = getObjToWorldTransform().getObjectCoords(newCornerPosWC);

        if(boundingBoxCornerIndex == 0)
            _p0 = newEndPoint;
        else
            _p1 = newEndPoint;

        return boundingBoxCornerIndex;
    }
}
