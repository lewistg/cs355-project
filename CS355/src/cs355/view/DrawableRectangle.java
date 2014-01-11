package cs355.view;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableRectangle extends DrawableShape{
    /**Parameters for the drawable line*/
    private int _lowerLeftX;
    private int _lowerRightY;
    private int _w;
    private int _h;

    /**
     * Constructor
     */
    DrawableRectangle(int lowerLeftX, int lowerLeftY, int w, int h, Color color)
    {
        super(color);
        _lowerLeftX = lowerLeftX;
        _lowerRightY = lowerLeftY;
        _w = w;
        _h = h;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setColor(getColor());
        context.fillRect(_lowerLeftX, _lowerRightY, _w, _h);
    }
}