package cs355.model;

import java.awt.*;

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
    public boolean pointInShape(Vector2D worldCoord, double tolerance) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
