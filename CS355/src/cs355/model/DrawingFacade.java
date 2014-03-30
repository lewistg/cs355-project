package cs355.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/26/14
 * Time: 8:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrawingFacade extends Observable
{
    /**Single instance of the facade*/
    private static DrawingFacade _instance;

    public static DrawingFacade getInstance()
    {
        if(_instance == null)
            _instance = new DrawingFacade();

        return _instance;
    }

    private DrawingFacade()
    {
        super();
    }

    /**
     * Translates the given shape by the given offset
     */
    public void translateShape(Shape shape, Vector2D worldOffset)
    {
        if(shape == null)
            return;

        ObjToWorldTransform.translateSelectedShape(shape, worldOffset);
        _instance.setChanged();
        _instance.notifyObservers();
    }

    /**
     * Rotates the given shape by the given offset
     */
    public void rotateSelectedShape(Shape shape, double theta)
    {
        if(shape == null)
            return;

        ObjToWorldTransform.rotateSelectedShape(shape, theta);
        _instance.setChanged();
        _instance.notifyObservers();
    }

    public void setShapeColor(Shape shape, Color c)
    {
        shape.setColor(c);
        _instance.setChanged();
        _instance.notifyObservers();
    }

    /**
     * Moves the corner of the rectangle
     * @param cornerIndex
     * @param newCornerPosWC
     */
    public int moveBoundingBoxCorner(Shape shape, int cornerIndex, Vector2D newCornerPosWC)
    {
        int newCornerIndex = shape.moveBoundingBoxCorner(cornerIndex, newCornerPosWC);
        _instance.setChanged();
        _instance.notifyObservers();
        return newCornerIndex;
    }

    /**
     * Gets the background image
     */
    public BufferedImage getBackgroundBufferedImage()
    {
        return BackgroundImage.getInstance().getBufferedImage();
    }

    public BackgroundImage getBackgroundImage()
    {
        return BackgroundImage.getInstance();
    }

    /**
     * Sets the background image
     */
    public void setBackgroundImage(BufferedImage image)
    {
        BackgroundImage.loadImage(image);
    }
}
