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
        //instance._pixelValues = new int[instance._width * instance._height];
        instance._pixelValues = new int[instance._width * instance._height * 3];
        img.getRaster().getPixels(0, 0, instance._width, instance._height, instance._pixelValues);
        instance._origImg = img;
    }

    public static BackgroundImage getInstance()
    {
        if(_instance == null)
            _instance = new BackgroundImage();

        return _instance;
    }

    public BufferedImage getBufferedImage()
    {
        /*BufferedImage image = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setPixels(0, 0, _width, _height, _pixelValues);

        return image;*/
        return _origImg;
    }
}
