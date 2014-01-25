package cs355.solution;

import cs355.model.*;
import cs355.model.Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MouseEllipseBuilderStrategy extends MouseShapeBuilderStrategy
{
    /**Points defining the current rectangle*/
    Point _p0;
    Point _p1;


    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
         _p0 = mouseEvent.getPoint();
         _p1 = _p0;
        Ellipse ellipse = new Ellipse(new Vector2D(_p0), 0, 0, cs355.model.Context.getInstance().getCurrentColor());
        Canvas.getInstance().addShape(ellipse);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        _p1 = mouseEvent.getPoint();
        double centerX = ((double) _p0.x + (double) _p1.x) / 2.0;
        double centerY = ((double) _p0.y + (double) _p1.y) / 2.0;
        Vector2D center = new Vector2D(centerX, centerY);
        double width = Math.abs(_p1.x - _p0.x);
        double height = Math.abs(_p1.y - _p0.y);

        Ellipse ellipse = new Ellipse(center, width, height, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.Canvas.getInstance().setLastShapeAdded(ellipse);
    }
}
