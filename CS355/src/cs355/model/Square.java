package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 12:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Square extends Shape {
    /**Underneath the square is simply a rectangle*/
    private Rectangle _squareRect;
    /**The width of the rectangle*/
    private double _size;

    /**
     * Constructor
     */
    public Square(Point lowerLeftWC, double size, Color color)
    {
        super(color);
        System.out.println("Lower left square wc coords: " + lowerLeftWC);
        _squareRect = new Rectangle(lowerLeftWC, size, size, color);
        _size = size;
    }

    /**
     * Getter for lower left corner
     */
    public Vector2D getLowerLeft()
    {
        return _squareRect.getLowerLeft();
    }

    /**
     * Getter for the width
     */
    public double size()
    {
        return _size;
    }

    @Override
    public Vector2D getCenter()
    {
        return _squareRect.getCenter();
    }

    @Override
    public void setObjToWorldTransform(ObjToWorldTransform objToWorld)
    {
        _squareRect.setObjToWorldTransform(objToWorld);
    }

    @Override
    public ObjToWorldTransform getObjToWorldTransform()
    {
        return _squareRect.getObjToWorldTransform();
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance) {
        return _squareRect.pointInShape(worldCoord, tolerance);
    }
}
