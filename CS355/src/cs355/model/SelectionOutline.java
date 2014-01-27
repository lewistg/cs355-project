package cs355.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/27/14
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class SelectionOutline
{
    /**Outline pts in object space*/
    private ArrayList<Vector2D> _outlinePts;
    /**Scale handle locations*/
    private ArrayList<Vector2D> _handlePts;
    /**Rotation handle location*/
    private Vector2D _rotationHandle;
    /**Handle size in object space*/
    private double _handleSize;

    /**
     * Constructor
     */
    public SelectionOutline()
    {
        _outlinePts = new ArrayList<Vector2D>();
        _handlePts = new ArrayList<Vector2D>();
        _rotationHandle = new Vector2D(0, 0);
        _handleSize = 7.0;
    }

    /**
     * Adds a new outline pt where a handle is drawn
     */
    public void addOutlinePt(Vector2D pt)
    {
        _outlinePts.add(pt);
    }

    /**
     * Adds a new handle pt
     * @param pt
     */
    public void addHandlePt(Vector2D pt)
    {
        _handlePts.add(pt);
    }

    /**
     * Gets the outline pts
     * @return
     */
    public ArrayList<Vector2D> getOutlinePts()
    {
        return _outlinePts;
    }

    /**
     * Setter for the rotation handle
     */
    public void setRotationHandle(Vector2D rotHandle)
    {
        _rotationHandle = rotHandle;
    }

    /**
     * Gets the handle pts
     */
    public Vector2D getRotationHandle()
    {
        return _rotationHandle;
    }

    boolean rotHandleSelected(Vector2D objCoords)
    {
        // test whether or not the rotation handle was selected
        double xDelta = objCoords.getX() - _rotationHandle.getX();
        double yDelta = objCoords.getY() - _rotationHandle.getY();

        return ((xDelta * xDelta) + (yDelta * yDelta)) < (_handleSize * _handleSize);
    }
}
