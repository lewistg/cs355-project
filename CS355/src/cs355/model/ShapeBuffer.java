package cs355.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:18 PM
 *
 * Singleton class
 */
public class ShapeBuffer extends Observable
{
    /**Singleton instance of class*/
    private static ShapeBuffer _instance;
    /**Buffer of shapes drawn. They are ordered according to when they were added*/
    private static ArrayList<Shape> _shapes;

    /**
     * Private constructor
     * @return
     */
    private ShapeBuffer()
    {
        _shapes = new ArrayList<Shape>();
    }

    /**
     * Gets the singleton instance
     * @return
     */
    public static ShapeBuffer getInstance()
    {
        if(_instance == null)
            _instance = new ShapeBuffer();

        return _instance;
    }

    /**
     * Getter for the shape buffer
     */
    public static ArrayList<Shape> getAllShapes()
    {
        return _shapes;
    }

    /**
     * Appends a shape to the buffer
     */
    public static void addShape(Shape shape)
    {
        _shapes.add(shape);
        _instance.setChanged();
        _instance.notifyObservers();
    }

    /**
     * Sets the very top shape in the buffer
     */
    public static void setTopShape(Shape shape)
    {
        if(_shapes.isEmpty())
            addShape(shape);
        else
            _shapes.set(0, shape);

        _instance.setChanged();
        _instance.notifyObservers();
    }
}
