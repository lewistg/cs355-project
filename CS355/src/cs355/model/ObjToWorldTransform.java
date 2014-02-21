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
        AffineMatrix2D m = getWorldToObjMatrix();
        Vector2D objCoords = m.transform(worldCoords);
        return objCoords;
    }

    public Vector2D getWorldCoords(Vector2D objCoords)
    {
        AffineMatrix2D m = getObjToWorldMatrix();
        return m.transform(objCoords);
    }

    /**
     * Constructs the object to world matrix
     * @return
     */
    private AffineMatrix2D getObjToWorldMatrix()
    {
        double[][] entries = {{Math.cos(_objToWorldRot), Math.sin(_objToWorldRot),  _objToWorldTrans.getX()},
                {-Math.sin(_objToWorldRot), Math.cos(_objToWorldRot), _objToWorldTrans.getY()}};

        AffineMatrix2D t = new AffineMatrix2D(entries);
        return t;
    }

    /**
     * Constructs the world object  matrix
     */
    private AffineMatrix2D getWorldToObjMatrix()
    {
        double c = Math.cos(-_objToWorldRot);
        double s = Math.sin(-_objToWorldRot);
        double tx = -_objToWorldTrans.getX();
        double ty = -_objToWorldTrans.getY();
        double[][] entries = {{c, s,  (c * tx) + (s * ty)},
                {-s, c, (-s * tx) + (c * ty)}};
        AffineMatrix2D t = new AffineMatrix2D(entries);
        return t;
    }

    /**
     * Gets the affine transformation
     */
    public AffineTransform getObjToWorldAffine()
    {
        return getObjToWorldMatrix().getAffineTransform();
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
