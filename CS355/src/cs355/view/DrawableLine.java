package cs355.view;

import cs355.model.Line;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableLine extends DrawableShape
{
    /**Parameters for the drawable line*/
    private int _x0;
    private int _y0;
    private int _x1;
    private int _y1;
    private AffineTransform _affineTransform;

    /**
     * Constructor
     */
    DrawableLine(int x0, int y0, int x1, int y1, Color color, AffineTransform affineTransform)
    {
        super(color);
        _x0 = x0;
        _y0 = y0;
        _x1 = x1;
        _y1 = y1;
        _affineTransform = affineTransform;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setColor(getColor());
        context.setTransform(_affineTransform);
        context.drawLine(_x0, _y0, _x1, _y1);
    }
}
