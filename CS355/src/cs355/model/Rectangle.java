package cs355.model;

import sun.font.TrueTypeFont;

import java.awt.*;
import java.util.ArrayList;

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
        return getCorner(0);
    }

    /**
     * Gets the corner for the indexed corner in object coordinates. Lower left is 0,
     * lower
     */
    public Vector2D getCorner(int cornerIndex)
    {
        assert(cornerIndex >= 0 && cornerIndex < 4);

        double xOffset = 0;
        double yOffset = 0;
        if(cornerIndex == 0 || cornerIndex == 3)
            xOffset = -_width / 2.0;
        else
            xOffset = _width / 2.0;

        if(cornerIndex == 0 || cornerIndex == 1)
            yOffset = -_height / 2.0;
        else
            yOffset = _height / 2.0;

        Vector2D offset = new Vector2D(xOffset, yOffset);
        return Vector2D.add(getCenter(), offset);
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

    /**
     * Gets an array of the corners in world coordinates
     * @return
     */
    public ArrayList<Vector2D> getObjBoundingBox()
    {
        ArrayList<Vector2D> corners = new ArrayList<>();
        corners.add(getCorner(0));
        corners.add(getCorner(1));
        corners.add(getCorner(2));
        corners.add(getCorner(3));

        return corners;
    }

    /**
     * Moves the indexed corner
     * @return The new corner index
     */
    public int moveBoundingBoxCorner(int boundingBoxCornerIndex, Vector2D newCornerPosWC)
    {
        assert(boundingBoxCornerIndex < 4);

        ObjToWorldTransform t = getObjToWorldTransform();
        Vector2D newCornerPos = t.getObjectCoords(newCornerPosWC);
        Vector2D oppCorner = getCorner((boundingBoxCornerIndex + 2) % 4);

        // find the new center in WC
        Vector2D oppCornerWC = t.getWorldCoords(oppCorner);
        Vector2D newCenterWC = Vector2D.add(oppCornerWC, newCornerPosWC);
        newCenterWC.scale(0.5);
        t.setObjToWorldTrans(newCenterWC);

        // calculate the new width and height
        _width = newCornerPos.getX() - oppCorner.getX();
        _height = newCornerPos.getY() - oppCorner.getY();

        if(_width < 0)
        {
            if(boundingBoxCornerIndex == 1)
                boundingBoxCornerIndex = 0;
            else if(boundingBoxCornerIndex == 2)
                boundingBoxCornerIndex = 3;
        }
        else if(_width > 0)
        {
            if(boundingBoxCornerIndex == 0)
                boundingBoxCornerIndex = 1;
            else if(boundingBoxCornerIndex == 3)
                boundingBoxCornerIndex = 2;
        }

        if(_height < 0)
        {
            if(boundingBoxCornerIndex == 2)
                boundingBoxCornerIndex = 1;
            else if(boundingBoxCornerIndex == 3)
                boundingBoxCornerIndex = 0;
        }
        else if(_height > 0)
        {
            if(boundingBoxCornerIndex == 1)
                boundingBoxCornerIndex = 2;
            else if(boundingBoxCornerIndex == 0)
                boundingBoxCornerIndex = 3;
        }

        _width = Math.abs(_width);
        _height = Math.abs(_height);

        return boundingBoxCornerIndex;
    }
}
