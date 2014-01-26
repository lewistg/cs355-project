package cs355.view;

import cs355.model.*;
import cs355.model.Rectangle;
import cs355.model.Shape;

import java.awt.geom.AffineTransform;
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
                drawableShapes.add(getDrawableRectangle((Rectangle) shape));
            else if(shape instanceof Square)
                drawableShapes.add(getDrawableSquare((Square) shape));
            else if(shape instanceof Ellipse)
                drawableShapes.add(getDrawableShape((Ellipse) shape));
            else if (shape instanceof Circle)
                drawableShapes.add(getDrawableCircle((Circle) shape));
            else if(shape instanceof Triangle)
                drawableShapes.add(getDrawableTriangle((Triangle) shape));
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
        Vector2D p0 = line.getStartPoint();
        Vector2D p1 = line.getEndPoint();
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
    private static DrawableShape getDrawableRectangle(cs355.model.Rectangle rectangle)
    {
        Vector2D getLowerLeft = rectangle.getLowerLeft();
        int x = (int) getLowerLeft.getX();
        int y = (int) getLowerLeft.getY();
        int w = (int) rectangle.getWidth();
        int h = (int) rectangle.getHeight();
        AffineTransform affineTransform = rectangle.getObjToWorldTransform().getObjToWorldAffine();
        DrawableRectangle drawableRectangle = new DrawableRectangle(x, y, w, h, rectangle.getColor(), affineTransform);
        return drawableRectangle;
    }

    /**
     * Builds a drawable rectangle shape
     */
    private static DrawableShape getDrawableSquare(cs355.model.Square square)
    {
        Vector2D getLowerLeft = square.getLowerLeft();
        int x = (int) getLowerLeft.getX();
        int y = (int) getLowerLeft.getY();
        int size = (int) square.size();
        //DrawableSquare drawableSquare = new DrawableSquare(x, y, size, size, square.getColor());
        AffineTransform affineTransform = square.getObjToWorldTransform().getObjToWorldAffine();
        DrawableRectangle drawableSquare = new DrawableRectangle(x, y, size, size, square.getColor(), affineTransform);
        return drawableSquare;
    }

    /**
     * Builds a drawable ellipse shape
     */
    private static DrawableEllipse getDrawableShape(Ellipse ellipse)
    {
        Vector2D center = ellipse.getCenter();
        double x = center.getX() - (ellipse.getWidth() / 2.0);
        double y = center.getY() - (ellipse.getHeight() / 2.0);
        DrawableEllipse drawableEllipse = new DrawableEllipse(x, y, ellipse.getWidth(), ellipse.getHeight(), ellipse.getColor());
        return drawableEllipse;
    }

    /**
     * Builds a drawable circle shape
     */
    private static DrawableCircle getDrawableCircle(Circle circle)
    {
        Vector2D center = circle.getCenter();
        double x = center.getX() - circle.getRadius();
        double y = center.getY() - circle.getRadius();
        double diameter = circle.getRadius() * 2.0;
        AffineTransform affineTransform = circle.getObjToWorldTransform().getObjToWorldAffine();
        DrawableCircle drawableCircle = new DrawableCircle(x, y, diameter, circle.getColor(), affineTransform);
        return drawableCircle;
    }

    /**
     * Builds a drawable triangle
     */
    private static DrawableTriangle getDrawableTriangle(Triangle triangle)
    {
        int[] xCoords = new int[3];
        int[] yCoords = new int[3];

        ArrayList<Vector2D> vertices = triangle.getVertices();
        for(int i = 0; i < 3; i++)
        {
            xCoords[i] = (int) vertices.get(i).getX();
            yCoords[i] = (int) vertices.get(i).getY();
        }

        DrawableTriangle drawableTriangle = new DrawableTriangle(xCoords, yCoords, triangle.getColor());
        return drawableTriangle;
    }
}
