package cs355.model;

import java.awt.*;

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
    public boolean pointInShape(Vector2D worldCoord, double tolerance) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
