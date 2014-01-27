package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Shape
{
    /**The shape's color*/
    private Color _color;
    /**The shape's center*/
    private Vector2D _center;
    /**The shapes object to world transformation*/
    ObjToWorldTransform _objToWorld;

    /**
     * Constructor
     * @param color
     */
    public Shape(Color color)
    {
        _color = color;
        _objToWorld = new ObjToWorldTransform(new Vector2D(0, 0), 0.0);
    }

    /**
     * Going to be the main constructor
     */
    public Shape(Color color, Vector2D center)
    {
        _color = color;
        _center = center;
        _objToWorld = new ObjToWorldTransform(new Vector2D(0, 0), 0.0);
    }

    /**
     * Setter for the color
     */
    public void setColor(Color color)
    {
        _color = color;
    }

    /**
     * Getter for the color
     * @return
     */
    public Color getColor()
    {
        return _color;
    }

    /**
     * Getter for the center
     */
    public Vector2D getCenter()
    {
        return _center;
    }

    /**
     * Sets the center of the shape
     */
    public void setCenter(Vector2D center)
    {
        _center = center;
    }

    /**
     * Sets the object to world transformation
     */
    public void setObjToWorldTransform(ObjToWorldTransform objToWorld)
    {
        _objToWorld = objToWorld;
    }

    /**
     * Gets the object to world transformation
     */
    public ObjToWorldTransform getObjToWorldTransform()
    {
        return _objToWorld;
    }

    /**
     * Tests whether or not a particular worldCoord is within an object.
     * @param worldCoord
     * @param tolerance
     * @return
     */
    public abstract boolean pointInShape(Vector2D worldCoord, double tolerance);

    /**
     * Returns the slection outline
     */
    public SelectionOutline getSelectionOutline()
    {
        return null;
    }
}
