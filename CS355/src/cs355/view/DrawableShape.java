package cs355.view;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DrawableShape
{
    /**Color to use for drawing*/
    Color _color;

    /**
     * Constructor
     */
    DrawableShape(Color color)
    {
        _color = color;
    }

    /**
     * Getter for the color
     */
    Color getColor()
    {
        return _color;
    }

    /**
     * Draws the shape on the given context
     */
    public void draw(Graphics2D context)
    {
        assert(false);
    }
}
