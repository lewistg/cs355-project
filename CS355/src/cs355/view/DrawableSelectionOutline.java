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
    /**The radius of the handle*/
    private final int HANDLE_SIZE = 7;


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
        AffineTransform objToScreen = WorldToScreen.getInstance().getWorldToScreenTrans();
        objToScreen.concatenate(_selectedShape.getObjToWorldTransform().getObjToWorldAffine());
        context.setTransform(objToScreen);
        context.setColor(Color.ORANGE);

        // draw outline and corner handles
        ObjToWorldTransform objToWorld = _selectedShape.getObjToWorldTransform();
        _corners = _selectedShape.getObjBoundingBox();
        double minY = Double.MAX_VALUE;
        for(int i = 0; i < _corners.size(); i++)
        {
            context.setTransform(objToScreen);
            int x0 = (int) _corners.get((i + 1) % _corners.size()).getX();
            int y0 = (int) _corners.get((i + 1) % _corners.size()).getY();
            if(y0 < minY)
                minY = y0;
            if(_drawOutline)
            {
                int x1 = (int) _corners.get(i).getX();
                int y1 = (int) _corners.get(i).getY();
                double oldWidth = ((BasicStroke) context.getStroke()).getLineWidth();
                double scaledStroke = Math.min(oldWidth / WorldToScreen.getInstance().getScaleFactor(), 1.0);
                context.setStroke(new BasicStroke((float) scaledStroke));
                context.drawLine(x0, y0, x1, y1);
            }

            // scale the handles to be a constant size on the screen
            context.setTransform(new AffineTransform());
            Vector2D resizeHandleWC = objToWorld.getWorldCoords(_corners.get(i));
            Vector2D resizeHandleScrn = WorldToScreen.getInstance().getInScreenCoords(resizeHandleWC);
            context.draw(new Ellipse2D.Double(resizeHandleScrn.getX() - 3, resizeHandleScrn.getY() - 3, 7, 7));
        }

        // draw the selection handle
        // get the screen coordinates of the handle

        if(_rotHandle != null)
        {
            double xRot = _selectedShape.getCenter().getX();
            double yRot = minY - 5;
            _rotHandle = new Vector2D(xRot, yRot);

            Vector2D rotHandleWC = objToWorld.getWorldCoords(_rotHandle);
            Vector2D rotHandleScrn = WorldToScreen.getInstance().getInScreenCoords(rotHandleWC);

            context.setTransform(new AffineTransform());
            context.draw(new Ellipse2D.Double(rotHandleScrn.getX() - 3, rotHandleScrn.getY() - 3, 7, 7));
        }
    }

    public boolean rotHandleSelected(Vector2D worldCoords)
    {
        if(_rotHandle == null)
            return false;

        // test whether or not the rotation handle was selected
        ObjToWorldTransform objToWorld = _selectedShape.getObjToWorldTransform();
        Vector2D selectedPtSC = WorldToScreen.getInstance().getInScreenCoords(worldCoords);

        Vector2D rotHandleWC = objToWorld.getWorldCoords(_rotHandle);
        Vector2D rotHandleSC = WorldToScreen.getInstance().getInScreenCoords(rotHandleWC);

        double xDelta = selectedPtSC.getX() - rotHandleSC.getX();
        double yDelta = selectedPtSC.getY() - rotHandleSC.getY();

        return ((xDelta * xDelta) + (yDelta * yDelta)) < (HANDLE_SIZE * HANDLE_SIZE);
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
        Vector2D selectedPtSC = WorldToScreen.getInstance().getInScreenCoords(worldCoords);

        for(int i = 0; i < _corners.size(); i++)
        {
            Vector2D cornerWC = objToWorld.getWorldCoords(_corners.get(i));
            Vector2D cornerSC = WorldToScreen.getInstance().getInScreenCoords(cornerWC);


            double xDelta = selectedPtSC.getX() - cornerSC.getX();
            double yDelta = selectedPtSC.getY() - cornerSC.getY();
            if(((xDelta * xDelta) + (yDelta * yDelta)) < (HANDLE_SIZE * HANDLE_SIZE))
                return i;
        }

        return -1;
    }

    public int resizeShape(int cornerIndex, Vector2D newCornerPosWC)
    {
        return DrawingFacade.getInstance().moveBoundingBoxCorner(_selectedShape, cornerIndex, newCornerPosWC);
    }
}
