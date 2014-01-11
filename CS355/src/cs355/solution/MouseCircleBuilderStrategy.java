package cs355.solution;

import cs355.model.Circle;
import cs355.model.Point2D;
import cs355.model.Square;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MouseCircleBuilderStrategy extends MouseShapeBuilderStrategy
{
    /**Points defining the current rectangle*/
    Point _p0;
    Point _p1;


    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
         _p0 = mouseEvent.getPoint();
         _p1 = _p0;
        Circle square = new Circle(new Point2D(_p0), 0, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.ShapeBuffer.getInstance().addShape(square);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        _p1 = mouseEvent.getPoint();
        double width = _p1.getX() - _p0.getX();
        double height = _p1.getY() - _p0.getY();

        double radius = Math.min(Math.abs(width), Math.abs(height)) / 2.0;

        double centerX = (_p0.x + radius * Math.signum(width));
        double centerY = (_p0.y + radius * Math.signum(height));

        Circle circle = new Circle(new Point2D(centerX, centerY), radius, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.ShapeBuffer.getInstance().setLastShapeAdded(circle);
    }
}
