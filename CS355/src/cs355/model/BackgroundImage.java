package cs355.model;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

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
    private BufferedImage _origImg;

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
        instance._pixelValues = new int[instance._width * instance._height * 3];
        img.getRaster().getPixels(0, 0, instance._width, instance._height, instance._pixelValues);
    }

    public static BackgroundImage getInstance()
    {
        if(_instance == null)
            _instance = new BackgroundImage();

        return _instance;
    }

    private int getPixel(int row, int col)
    {
        assert(row < _height);
        assert(col < _width);

        int[] pixel = new int[3];
        int offset = (row * _width + col) * 3;

        pixel[0] = _pixelValues[offset + 0];
        pixel[1] = _pixelValues[offset + 1];
        pixel[2] = _pixelValues[offset + 2];

        return pixel[0];
    }

    private void setPixel(int row, int col, int newValue)
    {
        assert(row < _height);
        assert(col < _width);
        assert(newValue >= 0 && newValue <= 255);

        int offset = (row * _width + col) * 3;

        _pixelValues[offset + 0] = newValue;
        _pixelValues[offset + 1] = newValue;
        _pixelValues[offset + 2] = newValue;
    }

    public BufferedImage getBufferedImage()
    {
        if(_width == 0 || _height == 0)
            return null;

        int[] clippedValues = new int[_width * _height * 3];
        for(int i = 0; i < _width * _height * 3; i++)
        {
            clippedValues[i] = _pixelValues[i];
            if(clippedValues[i] > 255)
                clippedValues[i] = 255;
            else if(clippedValues[i] < 0)
                clippedValues[i] = 0;
        }

        BufferedImage image = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setPixels(0, 0, _width, _height, clippedValues);
        return image;
    }

    public void adjustBrightness(int b)
    {
        for(int i = 0; i < _width * _height * 3; i++)
            _pixelValues[i] += b;
    }

    public void adjustContrast(int c)
    {
        for(int i = 0; i < _width * _height * 3; i++)
            _pixelValues[i] = (int) (Math.pow((c + 100.0) / 100.0, 4) * (_pixelValues[i] - 128) + 128);
    }

    private void convolve(double[][] kernel3x3)
    {
        for(int i = 0; i < _height; i++)
        {
            for(int j = 0; j < _width; j++)
            {
                double sum = 0.0;
                for(int k = -1; k < 2; k++)
                {
                    for(int l = -1; l < 2; l++)
                    {
                        if(i + k >= 0 && i + k < _height && j + l >= 0 && j + l < _width)
                            sum += kernel3x3[k + 1][l + 1] * getPixel(i + k, j + l);
                    }
                }
                setPixel(i, j, (int) sum);
            }
        }
    }

    public void blur()
    {
        double[][] avgKernel = new double[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                avgKernel[i][j] =  1.0 / 9.0;

        convolve(avgKernel);
    }
}
