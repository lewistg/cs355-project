package cs355.model;

import java.awt.*;
import java.util.ArrayList;

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

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        return _squareRect.getObjBoundingBox();
    }

    public int moveBoundingBoxCorner(int boundingBoxCornerIndex, Vector2D newCornerPosWC)
    {
        assert(boundingBoxCornerIndex < 4);

        ObjToWorldTransform t = getObjToWorldTransform();
        Vector2D newCornerPos = t.getObjectCoords(newCornerPosWC);
        Vector2D oppCorner = _squareRect.getCorner((boundingBoxCornerIndex + 2) % 4);

        Vector2D oppCornerWC = t.getWorldCoords(oppCorner);

        double xDelta = newCornerPos.getX() - oppCorner.getX();
        double yDelta = newCornerPos.getY() - oppCorner.getY();

        if(Math.abs(xDelta) > Math.abs(yDelta))
        {
            double xAdjustment = Math.abs(xDelta) - Math.abs(yDelta);
            if(newCornerPos.getX() < 0)
                newCornerPos.addToX(xAdjustment);
            else
                newCornerPos.addToX(-xAdjustment);
        }
        else
        {
            double yAdjustment = Math.abs(yDelta) - Math.abs(xDelta);
            if(newCornerPos.getY() < 0)
                newCornerPos.addToY(yAdjustment);
            else
                newCornerPos.addToY(-yAdjustment);
        }
        newCornerPosWC = t.getWorldCoords(newCornerPos);

        int selectedCorner = _squareRect.moveBoundingBoxCorner(boundingBoxCornerIndex, newCornerPosWC);
        _size = _squareRect.getWidth();
        return selectedCorner;
    }
}
