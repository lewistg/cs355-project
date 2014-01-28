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
public class DrawableTriangle extends DrawableShape{
    /**Parameters for the drawable line*/
    private int _xCoords[];
    private int _yCoords[];
    /**Affine transformation*/
    AffineTransform _affAffineTransform;

    /**
     * Constructor
     */
    DrawableTriangle(int[] xCoords, int[] yCoords, Color color, AffineTransform affineTransform)
    {
        super(color);
        _xCoords = xCoords;
        _yCoords = yCoords;
        _affAffineTransform = affineTransform;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setColor(getColor());
        context.setTransform(_affAffineTransform);
        Polygon triangle = new Polygon(_xCoords, _yCoords, 3);
        context.fillPolygon(triangle);
    }
}
