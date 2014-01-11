package cs355.view;

import cs355.model.*;
import cs355.model.Rectangle;
import cs355.model.Shape;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DrawableShapeFactory
{
    /**
     * Turns the shape buffer into a list of drawable shapes
     * @return
     */
    public static ArrayList<DrawableShape> getDrawableShapes(ArrayList<Shape> shapeList)
    {
        ArrayList<DrawableShape> drawableShapes = new ArrayList<DrawableShape>();
        for(Shape shape : shapeList)
        {
            if(shape instanceof Line)
                drawableShapes.add(getDrawableShape((Line) shape));
            else if(shape instanceof Rectangle)
                drawableShapes.add(getDrawableShape((Rectangle) shape));
            else
                assert(false);
        }

        return drawableShapes;
    }

    /**
     * Builds a drawable line shape
     * @param line
     * @return
     */
    private static DrawableShape getDrawableShape(cs355.model.Line line)
    {
        Point2D p0 = line.getStartPoint();
        Point2D p1 = line.getEndPoint();
        int x0 = (int) p0.getX();
        int y0 = (int) p0.getY();
        int x1 = (int) p1.getX();
        int y1 = (int) p1.getY();
        DrawableLine drawableLine = new DrawableLine(x0, y0, x1, y1, line.getColor());
        return drawableLine;
    }

    /**
     * Builds a drawable rectangle shape
     */
    private static DrawableShape getDrawableShape(cs355.model.Rectangle rectangle)
    {
        Point2D getLowerLeft = rectangle.getLowerLeft();
        int x = (int) getLowerLeft.getX();
        int y = (int) getLowerLeft.getY();
        int w = (int) rectangle.getWidth();
        int h = (int) rectangle.getHeight();
        DrawableRectangle drawableRectangle = new DrawableRectangle(x, y, w, h, rectangle.getColor());
        return drawableRectangle;
    }
}
