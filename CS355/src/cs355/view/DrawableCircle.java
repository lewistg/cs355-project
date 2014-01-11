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
public class DrawableCircle extends DrawableShape
{
    /**
     * Parameters for drawing the ellipse
     */
    double _x0;
    double _y0;
    double _radius;

    /**
     * Constructor
     */
    public DrawableCircle(double x0, double y0, double radius, Color color)
    {
        super(color);
        assert(radius >= 0);
        _x0 = x0;
        _y0 = y0;
        _radius = radius;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setColor(getColor());
        context.fill(new Ellipse2D.Double(_x0, _y0, _radius, _radius));
    }
}
