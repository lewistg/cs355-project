package cs355.view;

import cs355.model.*;
import cs355.model.Shape;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/27/14
 * Time: 9:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableSelectionOutline extends DrawableShape
{
    /**Selected shape*/
    Shape _selectedShape;
    /**Corners of outline*/
    ArrayList<Vector2D> _corners;
    /**Rotation handle*/
    Vector2D _rotHandle;


    /**
     * Constructor
     */
    DrawableSelectionOutline(cs355.model.Shape selectedShape, Color color)
    {
        super(color);
        _selectedShape = selectedShape;
    }

    /**
     * Sets the rotation handle
     */
    void setRotHandle(Vector2D rotHandle)
    {
        _rotHandle = rotHandle;
    }

    void setCorners(ArrayList<Vector2D> corners)
    {
        _corners = corners;
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setTransform(_selectedShape.getObjToWorldTransform().getObjToWorldAffine());
        context.setColor(Color.ORANGE);

        // draw outline
        for(int i = 0; i < _corners.size(); i++)
        {
            int x0 = (int) _corners.get((i + 1) % _corners.size()).getX();
            int y0 = (int) _corners.get((i + 1) % _corners.size()).getY();
            int x1 = (int) _corners.get(i).getX();
            int y1 = (int) _corners.get(i).getY();
            context.drawLine(x0, y0, x1, y1);
        }

        // draw the selection handle
        /*int x0 = (int) _selectionOutline.getRotationHandle().getX();
        int y0 = (int) _selectionOutline.getRotationHandle().getY();
        context.draw(new Ellipse2D.Double(x0, y0, 7, 7));*/
    }
}
