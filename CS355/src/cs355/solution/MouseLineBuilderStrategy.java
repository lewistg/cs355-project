package cs355.solution;

import cs355.model.*;
import cs355.model.Canvas;

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
        Point p0 = WorldToScreen.getInstance().getInWorldCoords(mouseEvent.getPoint());
        System.out.println(p0.toString());
        Point p1 = p0;
        _line = new Line(p1, p0, cs355.model.Context.getInstance().getCurrentColor());
        Canvas.getInstance().addShape(_line);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        Point p1 = WorldToScreen.getInstance().getInWorldCoords(mouseEvent.getPoint());
        System.out.println(p1.toString());
        _line.setEndPoint(p1);
        cs355.model.Canvas.getInstance().setLastShapeAdded(_line);
    }
}
