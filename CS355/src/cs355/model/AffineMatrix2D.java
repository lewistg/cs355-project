package cs355.model;

import java.awt.geom.AffineTransform;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 2/20/14
 * Time: 6:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class AffineMatrix2D
{
    /**Matrix entries*/
    private double[][] _entries;

    /**
     * Constructor
     * @params Top six elements
     */
    public AffineMatrix2D()
    {
        _entries = new double[3][3];
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                if(i == j)
                    _entries[i][j] = 1;
                else
                    _entries[i][j] = 0;
            }
        }
    }

    /**
     * Constructor
     * @param topEntries
     */
    public AffineMatrix2D(double[][] topEntries)
    {
        _entries = new double[3][3];
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 3; j++)
                _entries[i][j] = topEntries[i][j];

        _entries[2][0] = 0;
        _entries[2][1] = 0;
        _entries[2][2] = 1;
    }

    /**
     * Transforms a given vector
     */
    public Vector2D transform(Vector2D v)
    {
        Vector2D vPrime = new Vector2D(0, 0);
        double[] homogV = {v.getX(), v.getY(), 1.0};
        double xPrime = _entries[0][0] * homogV[0] + _entries[0][1] * homogV[1] + _entries[0][2];
        double yPrime = _entries[1][0] * homogV[0] + _entries[1][1] * homogV[1] + _entries[1][2];
        vPrime.setX(xPrime);
        vPrime.setY(yPrime);

        return vPrime;
    }

    /**
     * Gets the affine transform version
     */
    public AffineTransform getAffineTransform()
    {
        AffineTransform t = new AffineTransform(_entries[0][0], _entries[1][0],
                _entries[0][1], _entries[1][1],
                _entries[0][2], _entries[1][2]);

        return t;
    }
}
