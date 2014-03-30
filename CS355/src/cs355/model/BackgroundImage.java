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

    private int[] getPixel(int row, int col)
    {
        assert(row < _height);
        assert(col < _width);

        int[] pixel = new int[3];
        int offset = (row * _width + col) * 3;

        pixel[0] = _pixelValues[offset + 0];
        pixel[1] = _pixelValues[offset + 1];
        pixel[2] = _pixelValues[offset + 2];

        return pixel;
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
}
