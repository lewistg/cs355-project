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
        _pos[0] = 0.0;
        _pos[1] = 0.0;
        _pos[2] = 30.0;
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
     * Moves the camera forward one unit
     */
    void moveForward()
    {
        _pos[0] += Math.sin(_rot);
        _pos[2] += -Math.cos(_rot);
    }

    void moveBackward()
    {
        _pos[0] -= Math.sin(_rot);
        _pos[2] -= -Math.cos(_rot);
    }

    void moveRight()
    {
        _pos[0] += Math.cos(_rot);
        _pos[2] += Math.sin(_rot);
    }

    void moveLeft()
    {
        _pos[0] -= Math.cos(_rot);
        _pos[2] -= Math.sin(_rot);
    }

    void rotRight()
    {
        _rot += (Math.PI) / 180.0;
        if(_rot > (Math.PI * 2))
            _rot -= (Math.PI * 2);

    }

    void rotLeft()
    {
        _rot -= (Math.PI * 2) / 360;
        if(_rot < 0)
            _rot += (Math.PI * 2);
    }

    void moveUp()
    {
        _pos[1] += 1;
    }

    void moveDown()
    {
        _pos[1] -= 1;
    }

    void goHome()
    {
        _pos[0] = 0.0;
        _pos[1] = 0.0;
        _pos[2] = 30.0;
        _rot = 0.0;
    }

    /**
     * Gets the camera's rotation angle
     */
    public double getRotAngle()
    {
        return _rot;
    }
}
