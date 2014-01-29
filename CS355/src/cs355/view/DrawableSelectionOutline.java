package cs355.view;

import cs355.model.*;
import cs355.model.Shape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
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
    /**Flag for drawing the outline*/
    boolean _drawOutline;


    /**
     * Constructor
     */
    DrawableSelectionOutline(cs355.model.Shape selectedShape, ArrayList<Vector2D> corners, boolean drawOutline, Vector2D rotHandle, Color color)
    {
        super(color);
        _selectedShape = selectedShape;
        _corners = corners;
        _rotHandle = rotHandle;
        _drawOutline = drawOutline;
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
            if(_drawOutline)
            {
                int x1 = (int) _corners.get(i).getX();
                int y1 = (int) _corners.get(i).getY();
                context.drawLine(x0, y0, x1, y1);
            }
            context.draw(new Ellipse2D.Double(x0 - 3, y0 - 3, 7, 7));
        }

        // draw the selection handle
        if(_rotHandle != null)
            context.draw(new Ellipse2D.Double(_rotHandle.getX() - 3, _rotHandle.getY() - 3, 7, 7));
    }

    public boolean rotHandleSelected(Vector2D worldCoords)
    {
        if(_rotHandle == null)
            return false;

        // test whether or not the rotation handle was selected
        ObjToWorldTransform objToWorld = _selectedShape.getObjToWorldTransform();
        Vector2D objCoords = objToWorld.getObjectCoords(worldCoords);
        double xDelta = objCoords.getX() - _rotHandle.getX();
        double yDelta = objCoords.getY() - _rotHandle.getY();

        return ((xDelta * xDelta) + (yDelta * yDelta)) < (7 * 7);
    }
}
