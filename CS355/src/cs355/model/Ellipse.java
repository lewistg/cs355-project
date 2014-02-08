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

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        ArrayList<Vector2D> corners = new ArrayList<>();
        corners.add(getCorner(0));
        corners.add(getCorner(1));
        corners.add(getCorner(2));
        corners.add(getCorner(3));

        return corners;
    }

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
