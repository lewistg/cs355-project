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
import cs355.solution.WorldToScreen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

/**
 * Performs the 3D rendering
 */
public class Render3D
{
    /**The screen size*/
    static final double SCREEN_SIZE = 2048;

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

    /**
     * Multiplies a vector by a matrix on the left
     * @return
     */
    double[] multVecByMat(double[][] mat, double[] vec)
    {
        double[] transformedVec = new double[4];
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                transformedVec[i] += mat[i][j] * vec[j];
            }
        }

        return transformedVec;
    }

    boolean clipLine(double[] startClipCoords, double[] endClipCoords)
    {
        // left
        if(startClipCoords[0] < -startClipCoords[3] &&
                endClipCoords[0] < -endClipCoords[3])
        {
            return true;
        }

        // right
        if(startClipCoords[0] > startClipCoords[3] &&
                endClipCoords[0] > endClipCoords[3])
        {
            return true;
        }

        // bottom
        if(startClipCoords[1] < -startClipCoords[3] &&
                endClipCoords[1] < -endClipCoords[3])
        {
            return true;
        }

        // top
        if(startClipCoords[1] > startClipCoords[3] &&
                endClipCoords[1] > endClipCoords[3])
        {
            return true;
        }

        if(startClipCoords[2] > startClipCoords[3] ||
                endClipCoords[2] > endClipCoords[3])
        {
            return true;
        }

        return false;

        /*for(int i = 0; i < 4; i++)
        {
            if(Math.abs(startClipCoords[i]) > Math.abs(startClipCoords[3]) &&
                    Math.abs(endClipCoords[i]) > Math.abs(endClipCoords[3]))
                return true;
        }

        return false;*/
    }

    /**
     * Utility function for converting a point to a homogneous point
     * @param point
     * @return
     */
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
     * Utility function for converting a clip coordinate to a
     * NDC.
     * @param clipCoord
     * @return
     */
    private double[] getNdc(double[] clipCoord)
    {
        double[] ndc = new double[4];
        for(int i = 0; i < 4; i++)
            ndc[i] = clipCoord[i] / clipCoord[3];

        return ndc;
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
    private double[][] getClipMat()
    {
        double near = -1;
        double far = -1000;
        double zoomx = 1 / Math.tan(Math.PI / 6);
        double zoomy = zoomx;

        double[][] clipMat = new double[4][4];
        loadIdentity(clipMat);
        clipMat[0][0] = zoomx;
        clipMat[1][1] = zoomy;
        clipMat[2][2] = (far + near) / (far - near);
        clipMat[2][3] = (-2 * near * far) / (far - near);
        clipMat[3][2] = -1;
        clipMat[3][3] = 0;

        return clipMat;
    }

    /**
     * Gets the matrix that maps to screen coordinates
     */
    double[][] getNdcToScreenMat()
    {
        double[][] toScreenMat = new double[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                toScreenMat[i][j] = 0;

        toScreenMat[0][0] = SCREEN_SIZE / 2.0;
        toScreenMat[0][2] = SCREEN_SIZE / 2.0;
        toScreenMat[1][1] = -SCREEN_SIZE / 2.0;
        toScreenMat[1][2] = SCREEN_SIZE / 2.0;
        toScreenMat[2][2] = 1;

        return toScreenMat;
    }

    /**
     * Actually renders the house
     */
    public void renderHouseModel(Graphics2D context)
    {
        HouseModel house = new HouseModel();
        Iterator<Line3D> lines = house.getLines();

        double[][] worldToCamera = getWorldToCameraMat();
        double[][] cameraToClip = getClipMat();
        while(lines.hasNext())
        {
            Line3D line = lines.next();
            double[] startHomog = getHomog(line.start);
            double[] endHomog = getHomog(line.end);

            double[] startCamCoord = multVecByMat(worldToCamera, startHomog);
            double[] endCamCoord = multVecByMat(worldToCamera, endHomog);
            System.out.println("Camera start: " + startCamCoord[0] + ", " + startCamCoord[1] + ", " + startCamCoord[2] + ", " + startCamCoord[3]);

            double[] startClipCoords = multVecByMat(cameraToClip, startCamCoord);
            double[] endClipCoords= multVecByMat(cameraToClip, endCamCoord);
            System.out.println("Clip start: " + startClipCoords[0] + ", " + startClipCoords[1] + ", " + startClipCoords[2] + ", " + startClipCoords[3]);

            if(clipLine(startClipCoords, endClipCoords))
                continue;

            double[] startNdc = getNdc(startClipCoords);
            double[] endNdc = getNdc(endClipCoords);
            System.out.println("NDC start: " + startNdc[0] + ", " + startNdc[1] + ", " + startNdc[2] + ", " + startNdc[3]);

            double[][] toScreenMat = getNdcToScreenMat();

            double startX = toScreenMat[0][0] * startNdc[0] + toScreenMat[0][1] * startNdc[1] + toScreenMat[0][2];
            double startY = toScreenMat[1][0] * startNdc[0] + toScreenMat[1][1] * startNdc[1] + toScreenMat[1][2];

            double endX = toScreenMat[0][0] * endNdc[0] + toScreenMat[0][1] * endNdc[1] + toScreenMat[0][2];
            double endY = toScreenMat[1][0] * endNdc[0] + toScreenMat[1][1] * endNdc[1] + toScreenMat[1][2];

            AffineTransform affineTransform = WorldToScreen.getInstance().getWorldToScreenTrans();
            context.setColor(Color.ORANGE);

            double oldWidth = ((BasicStroke) context.getStroke()).getLineWidth();
            double scaledStroke = Math.min(oldWidth / WorldToScreen.getInstance().getScaleFactor(), 1.0);
            context.setStroke(new BasicStroke((float) scaledStroke));

            context.setTransform(affineTransform);
            context.drawLine((int) startX, (int) startY, (int) endX, (int) endY);
        }

    }
}
