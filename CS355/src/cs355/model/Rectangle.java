package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Rectangle extends Shape
{
    /**The width of the rectangle*/
    double _width;
    /**The height of the rectangle*/
    double _height;

    /**
     * Constructor
     */
    public Rectangle(Point lowerLeftWC, double width, double height, Color color)
    {
        //super(color, new Vector2D(lowerLeftWC.getX() + (width/2.0), lowerLeftWC.getY() + (height/2.0)));
        super(color, new Vector2D(0, 0));

        // create the object to world transform
        int centerXWC = (int) (lowerLeftWC.getX() + (width / 2));
        int centerYWC = (int) (lowerLeftWC.getY() + (height / 2));
        //Vector2D centerWC = new Vector2D(lowerLeftWC.getX() + (width / 2), lowerLeftWC.getY() + (height / 2));
        Vector2D centerWC = new Vector2D(centerXWC, centerYWC);
        Vector2D objToWorldTrans = new Vector2D(centerWC.getX(), centerWC.getY());
        ObjToWorldTransform objToWorldTransform = new ObjToWorldTransform(objToWorldTrans, 0.0);
        setObjToWorldTransform(objToWorldTransform);

        _width = width;
        _height = height;
    }

    /**
     * Getter for the upper left
     */
    public Vector2D getLowerLeft()
    {
        Vector2D center = getCenter();
        Vector2D cornerOffset = new Vector2D(-_width / 2.0, -_height / 2.0);
        Vector2D calculatedLowerLeft = Vector2D.add(center, cornerOffset);

        ObjToWorldTransform objToWorld = getObjToWorldTransform();
        Vector2D lowerLeftWC = objToWorld.getWorldCoords(calculatedLowerLeft);
        System.out.println(lowerLeftWC);
        return calculatedLowerLeft;
    }

    /**
     * Getter for the width
     */
    public double getWidth()
    {
        return _width;
    }

    /**
     * Width setter
     */
    public void setWidth(double width)
    {
        assert(width >= 0);
        _width = width;
    }

    /**
     * Getter for the height
     */
    public double getHeight()
    {
        return _height;
    }

    /**
     * Height setter
     * @return
     */
    public void setHeight(double height)
    {
        assert(height >= 0);
        _height = height;
    }

    public String toString()
    {
        String rect = "Lower lower left: " + getLowerLeft().toString() + ", width = " + _width + ", height = "  + _height;
        return rect;
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance)
    {
        // transform the world coordinates to object space
        ObjToWorldTransform objToWorld = getObjToWorldTransform();
        Vector2D objCoords = objToWorld.getObjectCoords(worldCoord);
        double xDist = Math.abs(objCoords.getX() - getCenter().getX());
        double yDist = Math.abs(objCoords.getY() - getCenter().getY());
        return (xDist < _width / 2.0) && (yDist < _height / 2.0);
    }

    @Override
    public SelectionOutline getSelectionOutline()
    {
        SelectionOutline selectionOutline = new SelectionOutline();
        Vector2D center = getCenter();
        double xOffset = (_width / 2) + 4;
        double yOffset = (_height / 2) + 4;
        selectionOutline.addOutlinePt(Vector2D.add(center, new Vector2D(-xOffset, yOffset)));
        selectionOutline.addOutlinePt(Vector2D.add(center, new Vector2D(xOffset, yOffset)));
        selectionOutline.addOutlinePt(Vector2D.add(center, new Vector2D(xOffset, -yOffset)));
        selectionOutline.addOutlinePt(Vector2D.add(center, new Vector2D(-xOffset, -yOffset)));

        selectionOutline.setRotationHandle(new Vector2D(center.getX(), center.getY() - (_height / 2) - 40));
        return selectionOutline;
    }
}
