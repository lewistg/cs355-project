package cs355.model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class Ellipse extends Shape
{
    /**Width*/
    double _width;
    /**Height*/
    double _height;

    /**
     * Constructor
     */
    public Ellipse(Vector2D centerWC, double width, double height, Color color)
    {
        super(color, new Vector2D(0, 0));

        ObjToWorldTransform objToWorldTransform = new ObjToWorldTransform(centerWC, 0.0);
        setObjToWorldTransform(objToWorldTransform);

        _width = width;
        _height = height;
    }

    /**
     * Getter for the width
     */
    public double getWidth()
    {
        return _width;
    }

    /**
     * Getter for the height
     */
    public double getHeight()
    {
        return _height;
    }

    /**
     * To string
     */
    public String toString()
    {
        String ellipseStr = "Center: " + getCenter().toString() + " width: " + _width + " height: " + _height;
        return ellipseStr;
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance)
    {
        ObjToWorldTransform objToWorld = getObjToWorldTransform();
        Vector2D objCoords = objToWorld.getObjectCoords(worldCoord);
        Vector2D center = getCenter();
        double xDelta = center._x - objCoords._x;
        double yDelta = center._y - objCoords._y;

        double xDeltaScaled = xDelta / (_width / 2.0);
        double yDeltaScaled = yDelta / (_height / 2.0);

        return (xDeltaScaled * xDeltaScaled) + (yDeltaScaled * yDeltaScaled) <= 1;
    }

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        Vector2D center = getCenter();
        double xOffset = (_width / 2);
        double yOffset = (_height / 2);

        ArrayList<Vector2D> corners = new ArrayList<>();
        corners.add(Vector2D.add(center, new Vector2D(-xOffset, yOffset)));
        corners.add(Vector2D.add(center, new Vector2D(xOffset, yOffset)));
        corners.add(Vector2D.add(center, new Vector2D(xOffset, -yOffset)));
        corners.add(Vector2D.add(center, new Vector2D(-xOffset, -yOffset)));

        return corners;
    }
}
