package cs355.model;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 4:18 PM
 *
 * Singleton class
 */
public class Canvas extends Observable
{
    /**Singleton instance of class*/
    private static Canvas _instance;
    /**Buffer of shapes drawn. They are ordered according to when they were added*/
    private static ArrayList<Shape> _shapes;
    /**The currently selected shape*/
    private Shape _selectedShape;

    /**
     * Private constructor
     * @return
     */
    private Canvas()
    {
        _shapes = new ArrayList<Shape>();
    }

    /**
     * Gets the singleton instance
     * @return
     */
    public static Canvas getInstance()
    {
        if(_instance == null)
            _instance = new Canvas();

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
    public static void setLastShapeAdded(Shape shape)
    {
        if(_shapes.isEmpty())
            addShape(shape);
        else
            _shapes.set(_shapes.size() - 1, shape);

        _instance.setChanged();
        _instance.notifyObservers();
    }

    /**
     * Finds the first shape that contains the given world coordinates. The
     * shapes are searched back to front (which corresponds to front to back
     * on the screen).
     */
    public Shape selectShape(Vector2D worldCoord, double tolerance)
    {
        _selectedShape = null;
        for(int i = _shapes.size() - 1; i >= 0; i--)
        {
            if(_shapes.get(i).pointInShape(worldCoord, tolerance))
            {
                _selectedShape = _shapes.get(i);
                break;
            }
        }

        return _selectedShape;
    }

    public Shape getSelectedShape()
    {
        return _selectedShape;
    }
}
