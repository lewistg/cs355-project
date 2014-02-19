package cs355.model;

import java.awt.geom.AffineTransform;

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
    private Vector2D _objToWorldTrans;
    /**Rotation to get to world space*/
    private double _objToWorldRot;

    /**
     * Constructor
     */
    public ObjToWorldTransform(Vector2D oToWTrans, double oToWRot)
    {
        _objToWorldTrans = oToWTrans;
        _objToWorldRot = oToWRot;
    }

    /**
     * Sets the object to world translation
     */
    public void setObjToWorldTrans(Vector2D objToWorldTrans)
    {
        _objToWorldTrans = objToWorldTrans;
    }

    /**
     * Transforms a world coordinate to object space
     */
    public Vector2D getObjectCoords(Vector2D worldCoords)
    {
        // do inverse transformation
        Vector2D objCoords = Vector2D.sub(worldCoords, _objToWorldTrans);
        objCoords = Vector2D.rot(objCoords, -_objToWorldRot);
        return objCoords;
    }

    public Vector2D getWorldCoords(Vector2D objCoords)
    {
        Vector2D worldCoords = Vector2D.rot(objCoords, _objToWorldRot);
        worldCoords = Vector2D.add(worldCoords, _objToWorldTrans);
        return worldCoords;
    }

    /**
     * Gets the affine transformation
     */
    public AffineTransform getObjToWorldAffine()
    {
        double m00 = Math.cos(_objToWorldRot);
        double m10 = Math.sin(_objToWorldRot);
        double m01 = -Math.sin(_objToWorldRot);
        double m11 = Math.cos(_objToWorldRot);
        double m02 = _objToWorldTrans.getX();
        double m12 = _objToWorldTrans.getY();
        AffineTransform affineTransform = new AffineTransform(m00, m10, m01, m11, m02, m12);
        return affineTransform;
    }

    /**
     * Moves shape by updating its object to world transformation
     */
    public static void translateSelectedShape(Shape shape, Vector2D worldOffset)
    {
        ObjToWorldTransform objToWorldTransform = shape.getObjToWorldTransform();
        objToWorldTransform._objToWorldTrans = Vector2D.add(objToWorldTransform._objToWorldTrans, worldOffset);
        shape.setObjToWorldTransform(objToWorldTransform);
    }

    /**
     * Gets the object to world rotation
     * @return
     */
    public double getObjToWorldRot()
    {
        return _objToWorldRot;
    }

    /**
     * Rotates the selected shape by updating its object
     */
    public static void rotateSelectedShape(Shape shape, double theta)
    {
        ObjToWorldTransform objToWorldTransform = shape.getObjToWorldTransform();
        objToWorldTransform._objToWorldRot = theta;
        objToWorldTransform._objToWorldRot %= 2 * Math.PI;
        shape.setObjToWorldTransform(objToWorldTransform);
    }
}
