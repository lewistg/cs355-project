package cs355.view;

import cs355.model.Line;
import cs355.model.Shape;
import cs355.model.ShapeBuffer;

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
            drawableShapes.add(getDrawableShape(shape));

        return drawableShapes;
    }

    /**
     * Builds a drawable line shape
     * @param line
     * @return
     */
    private static DrawableShape getDrawableShape(cs355.model.Line line)
    {
        Point p0 = line.getStartPoint();
        Point p1 = line.getStartPoint();
        int x0 = (int) p0.getX();
        int y0 = (int) p0.getY();
        int x1 = (int) p1.getX();
        int y1 = (int) p1.getY();
        DrawableLine drawableLine = new DrawableLine(x0, y0, x1, y1, line.getColor());
        return drawableLine;
    }

    private static DrawableShape getDrawableShape(Shape shape)
    {
        assert(false);
        return null;
    }
}
