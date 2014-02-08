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
    /**Underneath it is represented as an ellipse*/
    private Ellipse _circleEllipse;

    /**
     * Constructor
     */
    public Circle(Vector2D centerWC, double radius, Color color)
    {
        //super(color, centerWC);
        super(color, new Vector2D(0, 0));
        _circleEllipse = new Ellipse(centerWC, radius * 2, radius * 2, color);
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

    public ArrayList<Vector2D> getObjBoundingBox()
    {
        return _circleEllipse.getObjBoundingBox();
    }

    @Override
    public Vector2D getCenter()
    {
        return _circleEllipse.getCenter();
    }

    @Override
    public void setObjToWorldTransform(ObjToWorldTransform objToWorld)
    {
        _circleEllipse.setObjToWorldTransform(objToWorld);
    }

    @Override
    public ObjToWorldTransform getObjToWorldTransform()
    {
        return _circleEllipse.getObjToWorldTransform();
    }

    @Override
    public boolean pointInShape(Vector2D worldCoord, double tolerance) {
        return _circleEllipse.pointInShape(worldCoord, tolerance);
    }

    public int moveBoundingBoxCorner(int boundingBoxCornerIndex, Vector2D newCornerPosWC)
    {
        assert(boundingBoxCornerIndex < 4);

        ObjToWorldTransform t = getObjToWorldTransform();
        Vector2D newCornerPos = t.getObjectCoords(newCornerPosWC);
        Vector2D oppCorner = _circleEllipse.getCorner((boundingBoxCornerIndex + 2) % 4);

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

        int selectedCorner = _circleEllipse.moveBoundingBoxCorner(boundingBoxCornerIndex, newCornerPosWC);
        _radius = _circleEllipse.getWidth() / 2.0;
        return selectedCorner;
    }
}
