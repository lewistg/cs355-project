package cs355.view;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableRectangle extends DrawableShape{
    /**Obj to world transform*/
    AffineTransform _affineTransform;
    /**Parameters for the drawable line*/
    private int _lowerLeftX;
    private int _lowerRightY;
    private int _w;
    private int _h;

    /**
     * Constructor
     */
    DrawableRectangle(int lowerLeftX, int lowerLeftY, int w, int h, Color color, AffineTransform affineTransform)
    {
        super(new Color(250));
        _lowerLeftX = lowerLeftX;
        _lowerRightY = lowerLeftY;
        _w = w;
        _h = h;
        _affineTransform = affineTransform;
    }

    @Override
    public void draw(Graphics2D context)
    {
        Point lowerLeftWC = new Point();
        _affineTransform.transform(new Point(_lowerLeftX, _lowerRightY), lowerLeftWC);
        context.setTransform(_affineTransform);
        context.setColor(getColor());
        context.fillRect(_lowerLeftX, _lowerRightY, _w, _h);
    }
}
