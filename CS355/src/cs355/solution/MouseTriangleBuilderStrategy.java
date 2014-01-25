package cs355.solution;

import cs355.model.*;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/11/14
 * Time: 10:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class MouseTriangleBuilderStrategy extends MouseShapeBuilderStrategy
{
    /**Points defining the current rectangle*/
    int _i;
    Point[] _vertices;

    /**
     * Constructor
     */
    public MouseTriangleBuilderStrategy()
    {
        _i = 0;
        _vertices = new Point[3];
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
         _vertices[_i] = mouseEvent.getPoint();
        if(_i == 2)
        {
            Triangle triangle = new Triangle(_vertices[0], _vertices[1], _vertices[2], cs355.model.Context.getInstance().getCurrentColor());
            cs355.model.Canvas.getInstance().addShape(triangle);
        }
        _i = (_i + 1) % 3;
    }
}
