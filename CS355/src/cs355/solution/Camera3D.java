package cs355.solution;

import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 3/12/14
 * Time: 7:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Camera3D
{
    /**Camera position*/
    private double[] _pos;
    /**Camera rotation angle in the x-z plane*/
    private double _rot;

    /**
     * Constructor
     */
    Camera3D()
    {
        _pos = new double[3];
        _rot = 0.0;
    }

    /**
     * Gets the camera position
     */
    public double[] getPos()
    {
        return _pos;
    }

    /**
     * Gets the camera's rotation angle
     */
    public double getRotAngle()
    {
        return _rot;
    }
}
