package cs355.model;

import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/26/14
 * Time: 8:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class DrawingFacade extends Observable
{
    /**Single instance of the facade*/
    private static DrawingFacade _instance;

    public static DrawingFacade getInstance()
    {
        if(_instance == null)
            _instance = new DrawingFacade();

        return _instance;
    }

    private DrawingFacade()
    {
        super();
    }

    /**
     * Translates the given shape by the given offset
     */
    public void translateShape(Shape shape, Vector2D worldOffset)
    {
        ObjToWorldTransform.translateShape(shape, worldOffset);
        _instance.setChanged();
        _instance.notifyObservers();
    }
}
