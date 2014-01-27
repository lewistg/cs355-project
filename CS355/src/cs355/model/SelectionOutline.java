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
    /**Handle locations*/
    private ArrayList<Vector2D> _handlePts;

    /**
     * Constructor
     */
    public SelectionOutline()
    {
        _outlinePts = new ArrayList<Vector2D>();
        _handlePts = new ArrayList<Vector2D>();
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
}
