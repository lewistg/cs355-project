package cs355.model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 5:35 PM
 * Singleton class
 */
public class Context
{
    /**The instance of the context*/
    private static Context _instance;
    /**The current drawing*/
    private static Color _currentColor;

    /**
     * Constructor
     */
    private Context()
    {}

    /**
     * Getter for the single instance
     */
    Context getInstance()
    {
        if(_instance == null)
            _instance = new Context();

        return _instance;
    }

    /**
     * Setter for the current color
     */
    void setCurrentColor(Color color)
    {
        _currentColor = color;
    }

    /**
     * Getter for the current color
     */
    Color getCurrentColor()
    {
        return _currentColor;
    }
}
