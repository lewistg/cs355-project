package cs355.view;

import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.model.Canvas;
import cs355.model.DrawingFacade;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class LabOneViewRefresher implements ViewRefresher, Observer
{
    /**
     * Constructor
     */
    public LabOneViewRefresher()
    {
        Canvas shapeBuffer = Canvas.getInstance();
        shapeBuffer.addObserver(this);

        DrawingFacade drawingFacade = DrawingFacade.getInstance();
        drawingFacade.addObserver(this);
    }

    @Override
    public void refreshView(Graphics2D g2d)
    {
        // update the canvas here
        Canvas shapeBuffer = cs355.model.Canvas.getInstance();
        List<DrawableShape> drawableShapeList = DrawableShapeFactory.getDrawableShapes(shapeBuffer.getAllShapes());
        for(DrawableShape drawableShape : drawableShapeList)
            drawableShape.draw(g2d);
    }

    @Override
    public void update(Observable observable, Object o) {
        GUIFunctions.refresh();
    }
}
