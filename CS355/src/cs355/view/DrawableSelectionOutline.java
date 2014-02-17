package cs355.view;

import cs355.model.*;
import cs355.model.Shape;
import cs355.solution.WorldToScreen;

import java.awt.*;
import java.awt.geom.AffineTransform;
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
    private Shape _selectedShape;
    /**Corners of outline*/
    private ArrayList<Vector2D> _corners;
    /**Rotation handle*/
    private Vector2D _rotHandle;
    /**Flag for drawing the outline*/
    private boolean _drawOutline;


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

    /**
     * Getter for the selected shape
     */
    Shape getSelectedShape()
    {
        return _selectedShape;
    }

    @Override
    public void draw(Graphics2D context)
    {
        AffineTransform affineTransform = WorldToScreen.getInstance().getWorldToScreenTrans();
        affineTransform.concatenate(_selectedShape.getObjToWorldTransform().getObjToWorldAffine());
        context.setTransform(affineTransform);
        context.setColor(Color.ORANGE);

        // draw outline
        _corners = _selectedShape.getObjBoundingBox();
        double minY = Double.MAX_VALUE;
        for(int i = 0; i < _corners.size(); i++)
        {
            int x0 = (int) _corners.get((i + 1) % _corners.size()).getX();
            int y0 = (int) _corners.get((i + 1) % _corners.size()).getY();
            if(y0 < minY)
                minY = y0;
            if(_drawOutline)
            {
                int x1 = (int) _corners.get(i).getX();
                int y1 = (int) _corners.get(i).getY();
                context.drawLine(x0, y0, x1, y1);
            }
            context.draw(new Ellipse2D.Double(x0 - 3, y0 - 3, 7, 7));
        }

        // draw the selection handle
        //_rotHandle = _selectedShape.get
        if(_rotHandle != null)
        {
            double xRot = _selectedShape.getCenter().getX();
            double yRot = minY - 5;
            _rotHandle = new Vector2D(xRot, yRot);

            context.draw(new Ellipse2D.Double(_rotHandle.getX() - 3, _rotHandle.getY() - 3, 7, 7));
            //context.draw(new Ellipse2D.Double(xRot - 3, yRot - 3, 7, 7));
        }
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

    /**
     * Checks to see if a selection handle was selected.
     * Returns the index of the selected handle.
     * @param worldCoords
     * @return
     */
    public int resizeHandleSelected(Vector2D worldCoords)
    {
        ObjToWorldTransform objToWorld = _selectedShape.getObjToWorldTransform();
        for(int i = 0; i < _corners.size(); i++)
        {
            Vector2D objCoords = objToWorld.getObjectCoords(worldCoords);
            double xDelta = objCoords.getX() - _corners.get(i).getX();
            double yDelta = objCoords.getY() - _corners.get(i).getY();
            if(((xDelta * xDelta) + (yDelta * yDelta)) < (7 * 7))
                return i;
        }

        return -1;
    }

    public int resizeShape(int cornerIndex, Vector2D newCornerPosWC)
    {
        return DrawingFacade.getInstance().moveBoundingBoxCorner(_selectedShape, cornerIndex, newCornerPosWC);
    }
}
