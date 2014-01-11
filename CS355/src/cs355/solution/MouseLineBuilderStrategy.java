package cs355.solution;

import cs355.model.Line;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 9:06 AM
 * To change this template use File | Settings | File Templates.
 */
public class MouseLineBuilderStrategy extends MouseShapeBuilderStrategy {

    /**The line we are constructing*/
    Line _line;

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        Point p0 = mouseEvent.getPoint();
        Point p1 = p0;
        _line = new Line(cs355.model.Context.getInstance().getCurrentColor(), p0, p1);
        cs355.model.ShapeBuffer.getInstance().addShape(_line);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        Point p1 = mouseEvent.getPoint();
        _line.setEndPoint(p1);
        cs355.model.ShapeBuffer.getInstance().setTopShape(_line);
        //System.out.println(_line.toString());
        //System.out.println("New end point: " + p1.toString());
    }
}
