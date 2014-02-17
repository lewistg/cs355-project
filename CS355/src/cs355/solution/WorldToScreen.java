package cs355.solution;

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
    private Vector2D _viewportCenterWC;
    /**Width and height of viewport in woorld coords*/
    double _viewportW;
    double _viewportH;
    /**Singleton instance*/
    private static WorldToScreen _instance = null;

    private WorldToScreen()
    {
        _scaleFactor = 1.0;
        _viewportCenterWC = new Vector2D(0, 0);
        _viewportW = 512;
        _viewportH = 512;
    }

    public static WorldToScreen getInstance()
    {
        if(_instance == null)
            _instance = new WorldToScreen();

        return _instance;
    }

    public void setViewportCenter(Vector2D upperLeft)
    {
        _viewportCenterWC = new Vector2D(upperLeft);
    }

    public void setViewportUpperLeftX(double x)
    {
        _viewportCenterWC.setX(x + getScaledViewportW());
    }

    public void setViewportUpperLeftY(double y)
    {
        _viewportCenterWC.setY(y + getScaledViewportH());
    }

    /**
     * Gets the upper left corner of the viewport
     */
    public Vector2D getViewportCenterWC()
    {
        return new Vector2D(_viewportCenterWC);
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

    public Vector2D getUpperLeftViewportCorner()
    {
        return new Vector2D(_viewportCenterWC.getX() - getScaledViewportW(),
                _viewportCenterWC.getY() - getScaledViewportH());
    }

    private double getScaledViewportW()
    {
        return (_viewportW / (2.0 * _scaleFactor));
    }

    private double getScaledViewportH()
    {
        return (_viewportH / (_scaleFactor * 2.0));
    }

    /**
     * Gets the screen to world transform
     */
    public AffineTransform getScreenToWorldTrans()
    {
        Vector2D upperLeft = getUpperLeftViewportCorner();
        AffineTransform transform = new AffineTransform(1.0 / _scaleFactor, 0.0,
                                                        0.0, 1.0 / _scaleFactor,
                                                        upperLeft.getX(),
                                                        upperLeft.getY());

        return transform;
    }

    /**
     * Gets the world to screen transformation
     * @return
     */
    public AffineTransform getWorldToScreenTrans()
    {
        Vector2D upperLeft = getUpperLeftViewportCorner();
        AffineTransform transform = new AffineTransform(_scaleFactor, 0.0,
                0.0, _scaleFactor,
                -upperLeft.getX() * _scaleFactor, -upperLeft.getY() * _scaleFactor);

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
