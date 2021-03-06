package cs355.view;

import cs355.GUIFunctions;
import cs355.ViewRefresher;
import cs355.model.*;
import cs355.model.Canvas;
import cs355.solution.LabOneController;
import cs355.solution.WorldToScreen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 3:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class LabOneViewRefresher implements ViewRefresher, Observer
{
    /**Instance*/
    private static LabOneViewRefresher _instance;
    /**The current selection*/
    DrawableSelectionOutline _selection;

    public static LabOneViewRefresher getInstance()
    {
        if(_instance == null)
            _instance = new LabOneViewRefresher();

        return _instance;
    }

    public void setSelection(DrawableSelectionOutline selection)
    {
        _selection = selection;
        GUIFunctions.refresh();
    }

    /**
     * Constructor
     */
    private LabOneViewRefresher()
    {
        Canvas shapeBuffer = Canvas.getInstance();
        shapeBuffer.addObserver(this);

        DrawingFacade drawingFacade = DrawingFacade.getInstance();
        drawingFacade.addObserver(this);
    }

    @Override
    public void refreshView(Graphics2D g2d)
    {
        if(LabOneController.getInstance().getDrawBackground())
        {
            BackgroundImage backgroundImage = cs355.model.DrawingFacade.getInstance().getBackgroundImage();

            AffineTransform objToWorld = new AffineTransform();
            objToWorld.translate((LabOneController.DRAWING_AREA_SIZE - backgroundImage.getWidth()) / 2,
                    (LabOneController.DRAWING_AREA_SIZE - backgroundImage.getHeight()) / 2);
            AffineTransform worldToScreen = WorldToScreen.getInstance().getWorldToScreenTrans();
            BufferedImage img = getBackgroundImg();

            worldToScreen.concatenate(objToWorld);
            if(img != null)
                g2d.drawImage(img, worldToScreen, null);
        }

        // update the canvas here
        Canvas shapeBuffer = cs355.model.Canvas.getInstance();
        List<DrawableShape> drawableShapeList = DrawableShapeFactory.getDrawableShapes(shapeBuffer.getAllShapes());
        for(DrawableShape drawableShape : drawableShapeList)
            drawableShape.draw(g2d);

        if(_selection != null)
            _selection.draw(g2d);

        if(LabOneController.getInstance().getRender3d())
        {
            Render3D render3D = new Render3D();
            render3D.renderHouseModel(g2d);
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        GUIFunctions.refresh();
    }

    private BufferedImage getBackgroundImg()
    {
        BackgroundImage backgroundImage = cs355.model.DrawingFacade.getInstance().getBackgroundImage();
        int width = backgroundImage.getWidth();
        int height = backgroundImage.getHeight();

        int[] clippedValues = backgroundImage.getClippedPixelValues();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        image.getRaster().setPixels(0, 0, width, height, clippedValues);
        return image;
    }
}
