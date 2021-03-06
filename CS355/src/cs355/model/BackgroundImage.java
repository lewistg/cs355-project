package cs355.model;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 3/29/14
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackgroundImage {
    private int _width;
    private int _height;
    private int[] _pixelValues;
    private static BackgroundImage _instance;

    /**
     * Constructor
     */
    private BackgroundImage()
    {
        _width = 0;
        _height = 0;
    }

    public static void loadImage(BufferedImage img)
    {
        BackgroundImage instance = getInstance();
        instance._width = img.getWidth();
        instance._height = img.getHeight();

        int numComps = instance._width * instance._height * 3;
        int[] imgBuff = new int[numComps];
        img.getRaster().getPixels(0, 0, instance._width, instance._height, imgBuff);
        instance._pixelValues = new int[instance._width * instance._height];
        for(int i = 0; i < numComps; i += 3)
            instance._pixelValues[i / 3] = imgBuff[i];
    }

    public boolean imageLoaded()
    {
        return (_width != 0 && _height != 0);
    }

    public int getWidth()
    {
        return _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public static BackgroundImage getInstance()
    {
        if(_instance == null)
            _instance = new BackgroundImage();

        return _instance;
    }

    private int getPixel(int row, int col, int[] imgBuff)
    {
        assert(row < _height);
        assert(col < _width);

        int index = (row * _width + col);
        return imgBuff[index];
    }

    private void setPixel(int row, int col, int newValue, int[] imgBuff)
    {
        assert(row < _height);
        assert(col < _width);
        assert(newValue >= 0 && newValue <= 255);

        int index = (row * _width + col);
        imgBuff[index] = newValue;
    }

    public int[] getClippedPixelValues()
    {
        int[] clippedValues = new int[_width * _height];
        for(int i = 0; i < _width * _height; i++)
        {
            clippedValues[i] = _pixelValues[i];
            if(clippedValues[i] > 255)
                clippedValues[i] = 255;
            else if(clippedValues[i] < 0)
                clippedValues[i] = 0;
        }

        return clippedValues;
    }

    public void adjustBrightness(int b)
    {
        for(int i = 0; i < _width * _height; i++)
            _pixelValues[i] += b;
    }

    public void adjustContrast(int c)
    {
        for(int i = 0; i < _width * _height; i++)
            _pixelValues[i] = (int) (Math.pow((c + 100.0) / 100.0, 4) * (_pixelValues[i] - 128) + 128);
    }

    private double convolveWithPixel(int i, int j, double[][] kernel3x3)
    {
        assert(i >= 0 && i < _height);
        assert(j >= 0 && j < _width);

        double sum = 0.0;
        for(int k = -1; k < 2; k++)
        {
            for(int l = -1; l < 2; l++)
            {
                if(i + k >= 0 && i + k < _height && j + l >= 0 && j + l < _width)
                    sum += kernel3x3[k + 1][l + 1] * getPixel(i + k, j + l, _pixelValues);
            }
        }

        return sum;
    }

    private void convolve(double[][] kernel3x3)
    {
        int[] newImg = new int[_width * _height];
        for(int i = 0; i < _height; i++)
        {
            for(int j = 0; j < _width; j++)
            {
                double sum = convolveWithPixel(i, j, kernel3x3);
                setPixel(i, j, (int) sum, newImg);
            }
        }
        _pixelValues = newImg;
    }

    public void blur()
    {
        double[][] avgKernel = new double[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                avgKernel[i][j] =  1.0 / 9.0;

        convolve(avgKernel);
    }

    public void sharpen()
    {
        double[][] sharpenKernel = new double[3][3];
        sharpenKernel[0][1] = -1;
        sharpenKernel[1][0] = -1;
        sharpenKernel[1][2] = -1;
        sharpenKernel[2][1] = -1;
        sharpenKernel[1][1] = 6;

        convolve(sharpenKernel);
        for(int i = 0; i < _height; i++)
        {
            for(int j = 0; j < _width; j++)
            {
                int pixelValue = getPixel(i, j, _pixelValues);
                pixelValue /= 2;
                setPixel(i, j, pixelValue, _pixelValues);
            }
        }
    }

    public void median()
    {
        int[] newImg = new int[_width * _height];
        for(int i = 0; i < _height; i++)
        {
            for(int j = 0; j < _width; j++)
            {
                ArrayList<Integer> neighborhood = new ArrayList<>();
                for(int k = -1; k < 2; k++)
                {
                    for(int l = -1; l < 2; l++)
                    {
                        if(i + k >= 0 && i + k < _height && j + l >= 0 && j + l < _width)
                            neighborhood.add(getPixel(i + k, j + l, _pixelValues));
                    }
                }
                Collections.sort(neighborhood);
                int medianIndex = neighborhood.size() / 2;
                int median = 0;
                if(neighborhood.size() % 2 == 1)
                {
                    median = neighborhood.get(medianIndex);
                }
                else
                {
                    median = neighborhood.get(medianIndex) + neighborhood.get(medianIndex + 1);
                    median /= 2;
                }
                setPixel(i, j, median, newImg);
            }
        }
        _pixelValues = newImg;
    }

    public void detectEdges()
    {
        double[][] xSobel = new double[3][3];
        xSobel[0][0] = -1;
        xSobel[1][0] = -2;
        xSobel[2][0] = -1;
        xSobel[0][2] = 1;
        xSobel[1][2] = 2;
        xSobel[2][2] = 1;

        double[][] ySobel = new double[3][3];
        ySobel[0][0] = -1;
        ySobel[0][1] = -2;
        ySobel[0][2] = -1;
        ySobel[2][0] = 1;
        ySobel[2][1] = 2;
        ySobel[2][2] = 1;

        int [] newImg = new int[_width * _height];
        for(int i = 0; i < _height; i++)
        {
            for(int j = 0; j < _width; j++)
            {
                double xGrad = convolveWithPixel(i, j, xSobel) / 8.0;
                xGrad *= xGrad;

                double yGrad =  convolveWithPixel(i, j, ySobel) / 8.0;
                yGrad *= yGrad;

                double grad = Math.sqrt(xGrad + yGrad);
                setPixel(i, j, (int) grad, newImg);
            }
        }
        _pixelValues = newImg;
    }
}
