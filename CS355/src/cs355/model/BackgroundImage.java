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
        _instance._width = img.getWidth();
        _instance._height = img.getHeight();
        _instance._pixelValues = new int[_instance._width * _instance._height];
        img.getRaster().getPixels(0, 0, _instance._width, _instance._height, _instance._pixelValues);
    }

    public BackgroundImage getInstance()
    {
        if(_instance == null)
            _instance = new BackgroundImage();

        return _instance;
    }

    public BufferedImage getBufferedImage()
    {
        BufferedImage image = new BufferedImage(_width, _height, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, _width, _height, _pixelValues);

        return image;
    }
}
