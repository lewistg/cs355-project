package cs355.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:18 PM
 *
 * Singleton class
 */
public class ShapeBuffer
{
    /**Singleton instance of class*/
    private static ShapeBuffer _instance;
    /**Buffer of shapes drawn. They are ordered according to when they were added*/
    private ArrayList<Shape> _shapes;

    /**
     * Private constructor
     * @return
     */
    private ShapeBuffer()
    {}

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
    public List<Shape> getShapes()
    {
        return Collections.unmodifiableList(_shapes);
    }

    /**
     * Appends a shape to the buffer
     */
    void addShape(Shape shape)
    {
        _shapes.add(shape);
    }
}
