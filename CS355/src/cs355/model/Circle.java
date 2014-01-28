package cs355.model;

import java.awt.*;
import java.util.ArrayList;

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
    public Circle(Vector2D centerWC, double radius, Color color)
    {
        //super(color, centerWC);
        super(color, new Vector2D(0, 0));

        // create object to world
        ObjToWorldTransform objToWorldTransform = new ObjToWorldTransform(centerWC, 0.0);
        setObjToWorldTransform(objToWorldTransform);

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

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance)
    {
        ObjToWorldTransform objToWorld = getObjToWorldTransform();
        Vector2D objCoords = objToWorld.getObjectCoords(worldCoord);

        Vector2D delta = Vector2D.sub(objCoords, getCenter());
        return (delta.getX() * delta.getX() + delta.getY() * delta.getY()) < (_radius * _radius);
    }

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        Vector2D center = getCenter();

        ArrayList<Vector2D> corners = new ArrayList<>();
        corners.add(Vector2D.add(center, new Vector2D(-_radius, _radius)));
        corners.add(Vector2D.add(center, new Vector2D(_radius, _radius)));
        corners.add(Vector2D.add(center, new Vector2D(_radius, -_radius)));
        corners.add(Vector2D.add(center, new Vector2D(-_radius, -_radius)));

        return corners;
    }
}
