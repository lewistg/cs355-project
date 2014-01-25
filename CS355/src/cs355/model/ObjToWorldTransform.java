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
    private double _translateOToW;
    /**Rotation to get to world space*/
    private double _thetaOToW;

    /**
     * Constructor
     */
    public ObjToWorldTransform(double translateOToW, double thetaOToW)
    {
        _translateOToW = translateOToW;
        _thetaOToW = thetaOToW;
    }
}
