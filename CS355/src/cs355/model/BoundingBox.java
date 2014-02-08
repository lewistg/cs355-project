package cs355.model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 2/7/14
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class BoundingBox
{
    //**Vertices for the bounding box*/
    private ArrayList<Vector2D> _corners;

    /**
     * Constructor
     */
    public BoundingBox(ArrayList<Vector2D> corners)
    {
        _corners = new ArrayList<>(corners);
    }

    /**
     * Gets the indexed corner
     * @param cornerIndex
     * @return
     */
    public Vector2D getCorner(int cornerIndex) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    /**
     * Moves the indexed corner
     * @param boundingBoxCornerIndex
     * @param newCornerPosWC
     * @return
     */
    public abstract int moveCorner(int boundingBoxCornerIndex, Vector2D newCornerPosWC);
}
