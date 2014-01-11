package cs355.solution;

import cs355.model.Rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MouseRectangleBuilderStrategy extends MouseShapeBuilderStrategy
{
    /**Points defining the current rectangle*/
    Point _p0;
    Point _p1;


    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
         _p0 = mouseEvent.getPoint();
         _p1 = _p0;
        Rectangle rectangle = new Rectangle(_p1, _p0, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.ShapeBuffer.getInstance().addShape(rectangle);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        Point p1 = mouseEvent.getPoint();
        Rectangle rectangle = new Rectangle(_p1, _p0, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.ShapeBuffer.getInstance().setLastShapeAdded(rectangle);
    }
}
