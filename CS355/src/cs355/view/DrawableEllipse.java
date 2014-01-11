package cs355.view;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableEllipse extends DrawableShape
{
    /**
     * Parameters for drawing the ellipse
     */
    double _x0;
    double _y0;
    double _width;
    double _height;

    /**
     * Constructor
     */
    public DrawableEllipse(double x0, double y0, double width, double height, Color color)
    {
        super(color);
        assert(width >= 0);
        assert(height >= 0);
        _x0 = x0;
        _y0 = y0;
        _width = width;
        _height = height;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setColor(getColor());
        context.fill(new Ellipse2D.Double(_x0, _y0, _width, _height));
    }
}
