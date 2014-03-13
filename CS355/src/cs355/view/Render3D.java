package cs355.view;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 3/12/14
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */

import cs355.HouseModel;
import cs355.Line3D;
import cs355.Point3D;
import cs355.solution.Camera3D;
import cs355.solution.LabOneController;

import java.util.Iterator;

/**
 * Performs the 3D rendering
 */
public class Render3D
{
    /**
     * Utility function for loading the identity into a matrix.
     */
    private void loadIdentity(double[][] mat)
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(i == j)
                    mat[i][j] = 1;
                else
                    mat[i][j] = 0;
            }
        }
    }

    /**
     * Utility function for multiplying matrices
     * @param mat0
     * @param mat1
     * @return
     */
    private double[][] multMats(double[][] mat0, double[][] mat1)
    {
        double[][] result = new double[4][4];
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                for(int k = 0; k < 4; k++)
                {
                    result[i][j] += mat0[i][k] * mat1[k][j];
                }
            }
        }

        return  result;
    }

    private double[] getHomog(Point3D point)
    {
        double[] homogPoint = new double[4];
        homogPoint[0] = point.x;
        homogPoint[1] = point.y;
        homogPoint[2] = point.z;
        homogPoint[3] = 1.0;

        return homogPoint;
    }

    /**
     * Gets the world to
     * @return
     */
    private double[][] getWorldToCameraMat()
    {
        LabOneController controller = LabOneController.getInstance();
        Camera3D camera = controller.getCamera();
        double[] cameraPos = camera.getPos();
        double cameraAngle = camera.getRotAngle();

        double[][] translate = new double[4][4];
        loadIdentity(translate);
        translate[0][3] = -cameraPos[0];
        translate[1][3] = -cameraPos[1];
        translate[2][3] = -cameraPos[2];

        double[][] rotate = new double[4][4];
        loadIdentity(rotate);
        rotate[0][0] = Math.cos(cameraAngle);
        rotate[0][2] = Math.sin(cameraAngle);
        rotate[2][0] = -Math.sin(cameraAngle);
        rotate[2][2] = Math.cos(cameraAngle);

        double[][] worldToCamera = multMats(rotate, translate);


        return worldToCamera;
    }

    /**
     * Gets the clip matrix
     */

    /**
     * Actually renders the house
     */
    void renderHouseModel()
    {
        HouseModel house = new HouseModel();
        Iterator<Line3D> lines = house.getLines();
        while(lines.hasNext())
        {
            Line3D line = lines.next();
            line.start
        }

    }
}
