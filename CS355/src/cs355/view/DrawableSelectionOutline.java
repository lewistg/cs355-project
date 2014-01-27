package cs355.view;

import cs355.model.*;

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
    /**The outline to be drawn*/
    SelectionOutline _selectionOutline;
    AffineTransform _affineTransform;

    /**
     * Constructor
     */
    DrawableSelectionOutline(cs355.model.Shape selectedShape, Color color)
    {
        super(color);
        _selectionOutline = selectedShape.getSelectionOutline();
        _affineTransform = selectedShape.getObjToWorldTransform().getObjToWorldAffine();
    }

    @Override
    public void draw(Graphics2D context)
    {
        context.setTransform(_affineTransform);
        context.setColor(getColor());

        // draw outline
        ArrayList<Vector2D> outlinePts = _selectionOutline.getOutlinePts();
        for(int i = 0; i < outlinePts.size(); i++)
        {
            int x0 = (int) outlinePts.get((i + 1) % outlinePts.size()).getX();
            int y0 = (int) outlinePts.get((i + 1) % outlinePts.size()).getY();
            int x1 = (int) outlinePts.get(i).getX();
            int y1 = (int) outlinePts.get(i).getY();
            context.drawLine(x0, y0, x1, y1);
        }

        // draw the selection handle
        int x0 = (int) _selectionOutline.getRotationHandle().getX();
        int y0 = (int) _selectionOutline.getRotationHandle().getY();
        context.draw(new Ellipse2D.Double(x0, y0, 7, 7));
    }
}
