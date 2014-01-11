package cs355.solution;

import cs355.model.Rectangle;
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
public class MouseSquareBuilderStrategy extends MouseShapeBuilderStrategy
{
    /**Points defining the current rectangle*/
    Point _p0;
    Point _p1;


    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
         _p0 = mouseEvent.getPoint();
         _p1 = _p0;
        Square square = new Square(_p0, 0, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.ShapeBuffer.getInstance().addShape(square);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        _p1 = mouseEvent.getPoint();
        double width = _p1.getX() - _p0.getX();
        double height = _p1.getY() - _p0.getY();

        double widthSign = Math.signum(width);
        double heightSign = Math.signum(height);
        double size = Math.min(Math.abs(width), Math.abs(height));

        Point lowerLeft = new Point(_p0);
        if(width < 0)
            lowerLeft.x = (int) (_p0.x - size);
        if(height < 0)
            lowerLeft.y = (int) (_p0.y - size);

        Square square = new Square(lowerLeft, size, cs355.model.Context.getInstance().getCurrentColor());
        cs355.model.ShapeBuffer.getInstance().setLastShapeAdded(square);
    }
}
