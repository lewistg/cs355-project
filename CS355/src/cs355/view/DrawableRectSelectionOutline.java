package cs355.view;

import cs355.model.*;
import cs355.model.Shape;

import java.awt.*;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 2/7/14
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableRectSelectionOutline extends DrawableSelectionOutline
{
    /**
     * Constructor
     */
    DrawableRectSelectionOutline(Shape selectedShape, ArrayList<Vector2D> corners, boolean drawOutline, Vector2D rotHandle, Color color) {
        super(selectedShape, corners, drawOutline, rotHandle, color);
    }

    /**
     * Resizes the underlying shape
     */
    public int resizeShape(int cornerIndex, Vector2D newCornerPosWC)
    {
        cs355.model.Rectangle selectedRect = (cs355.model.Rectangle) getSelectedShape();
        return DrawingFacade.getInstance().moveRectCorner(selectedRect, cornerIndex, newCornerPosWC);
    }
}
