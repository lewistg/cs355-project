package cs355.model;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/25/14
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class ObjToWorldTransform
{
    /**Translation to get to world space*/
    private Vector2D _oToWTrans;
    /**Rotation to get to world space*/
    private double _oToWRot;

    /**
     * Constructor
     */
    public ObjToWorldTransform(Vector2D oToWTrans, double oToWRot)
    {
        _oToWTrans = oToWTrans;
        _oToWRot = oToWRot;
    }

    /**
     * Transforms a world coordinate to object space
     */
    public Vector2D getObjectCoords(Vector2D worldCoords)
    {
        // do inverse transformation
        Vector2D objCoords = Vector2D.sub(worldCoords, _oToWTrans);
        objCoords = Vector2D.rot(objCoords, -_oToWRot);
        return objCoords;
    }
}
