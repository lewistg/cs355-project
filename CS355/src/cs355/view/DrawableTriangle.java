package cs355.view;

import java.awt.*;

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

    /**
     * Constructor
     */
    DrawableTriangle(int[] xCoords, int[] yCoords, Color color)
    {
        super(color);
        _xCoords = xCoords;
        _yCoords = yCoords;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setColor(getColor());
        Polygon triangle = new Polygon(_xCoords, _yCoords, 3);
        context.fillPolygon(triangle);
    }
}
