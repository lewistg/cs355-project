package cs355.model;

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

        _entries[3][0] = 0;
        _entries[3][1] = 0;
        _entries[3][2] = 1;
    }

    /**
     * Transforms a given vector
     */
    public void transform(Vector2D v)
    {
        double[] homogV = {v.getX(), v.getY(), 1.0};
        double xPrime = _entries[0][0] * homogV[0] + _entries[0][1] * homogV[1];
        double yPrime = _entries[1][0] * homogV[0] + _entries[1][1] * homogV[1];
        v.setX(xPrime);
        v.setY(yPrime);
    }
}
