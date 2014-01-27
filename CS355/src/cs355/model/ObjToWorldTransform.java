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
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(_objToWorldTrans.getX(), _objToWorldTrans.getY());
        affineTransform.rotate(_objToWorldRot);
        System.out.println("Mine: " + _objToWorldTrans);
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
     * Rotates the selected shape by updating its object
     */
    public static void rotateSelectedShape(Shape shape, double theta)
    {
        ObjToWorldTransform objToWorldTransform = shape.getObjToWorldTransform();
        objToWorldTransform._objToWorldRot = theta;
        objToWorldTransform._objToWorldRot %= 2 * Math.PI;
        System.out.println("Rotation: " + objToWorldTransform._objToWorldRot);
        shape.setObjToWorldTransform(objToWorldTransform);
    }
}
