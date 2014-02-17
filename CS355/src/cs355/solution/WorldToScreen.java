package cs355.solution;

import cs355.model.ObjToWorldTransform;
import cs355.model.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 2/17/14
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class WorldToScreen
{
    /**The scale factor*/
    private double _scaleFactor;
    /**The upper left corner of the viewport*/
    private Vector2D _upperLeftViewportWC;
    /**Singleton instance*/
    private static WorldToScreen _instance;

    private WorldToScreen()
    {
        _scaleFactor = 1.0;
        _upperLeftViewportWC = new Vector2D(0, 0);
    }

    public static WorldToScreen getInstance()
    {
        if(_instance == null)
            _instance = new WorldToScreen();

        return _instance;
    }

    public void setUpperLeftViewportWC(Vector2D upperLeft)
    {
        _upperLeftViewportWC = upperLeft;
    }

    /**
     * Gets the upper left corner of the viewport
     */
    public Vector2D getUpperLeftViewportWC()
    {
        return _upperLeftViewportWC;
    }

    public void setScaleFactor(double scaleFactor)
    {
        _scaleFactor = scaleFactor;
    }

    /**
     * The scale factor
     * @return
     */
    public double getScaleFactor()
    {
        return _scaleFactor;
    }

    /**
     * Gets the screen to world transform
     */
    public AffineTransform getScreenToWorldTrans()
    {
        AffineTransform transform = new AffineTransform(1.0 / _scaleFactor, 0.0,
                                                        0.0, 1.0 / _scaleFactor,
                                                        _upperLeftViewportWC.getX(), _upperLeftViewportWC.getY());

        return transform;
    }

    /**
     * Gets the world to screen transformation
     * @return
     */
    public AffineTransform getWorldToScreenTrans()
    {
        AffineTransform transform = new AffineTransform(_scaleFactor, 0.0,
                0.0, _scaleFactor,
                -_upperLeftViewportWC.getX(), -_upperLeftViewportWC.getY());

        return transform;
    }

    /**
     * Gets the point in world coordinates
     */
    public Point getInWorldCoords(Point screenCoord)
    {
        AffineTransform affineTransform = getScreenToWorldTrans();
        Point transformedPt = new Point();
        affineTransform.transform(screenCoord, transformedPt);
        return transformedPt;
    }
}
