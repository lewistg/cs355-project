package cs355.solution;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.model.*;
import cs355.model.Shape;
import cs355.view.LabOneViewRefresher;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LabOneController implements CS355Controller, MouseListener, MouseMotionListener
{
    public static final int DRAWING_AREA_SIZE = 2048;

    /**Singleton instance*/
    private static LabOneController _instance;

    /** Controls how shapes are built from mouse-click events. */
    private MouseShapeBuilderStrategy _shapeBuilderStrategy;
    /**Locks scroll bars*/
    private boolean _lockScrollBars;
    /**3D Camera*/
    Camera3D _camera;
    boolean _render3d;
    /**Controls drawing the background*/
    boolean _drawBackground;

    /**
     * Gets the singleton instance
     * @return
     */
    public static LabOneController getInstance()
    {
        if(_instance == null)
            _instance = new LabOneController();

        return _instance;
    }

    /**
     * Constructor
     */
    private LabOneController()
    {
        _shapeBuilderStrategy = new MouseLineBuilderStrategy();
        _lockScrollBars = false;
        _camera = new Camera3D();
        _render3d = false;
        _drawBackground = false;
    }

    public Camera3D getCamera()
    {
        return _camera;
    }

    public void init()
    {
        GUIFunctions.setHScrollBarMax(DRAWING_AREA_SIZE - 1);
        GUIFunctions.setVScrollBarMax(DRAWING_AREA_SIZE - 1);

        WorldToScreen.getInstance().setViewportCenter(new Vector2D(DRAWING_AREA_SIZE / 2, DRAWING_AREA_SIZE / 2));
        updateScrollBars();
    }

    private void updateScrollBars()
    {
        _lockScrollBars = true;
        // the knob size is proportional to the viewport size
        int knobSize = (int) (512 / WorldToScreen.getInstance().getScaleFactor());
        System.out.println("Knob size: " + Integer.toString(knobSize));

        Vector2D viewportCenterWC = WorldToScreen.getInstance().getViewportCenterWC();

        // resize the scroll bar
        GUIFunctions.setHScrollBarPosit(0);
        GUIFunctions.setVScrollBarPosit(0);
        GUIFunctions.setVScrollBarKnob(knobSize);
        GUIFunctions.setHScrollBarKnob(knobSize);

        // recenter the scroll bar to be on the center
        double xOffset = -knobSize / 2.0;
        double yOffset = -knobSize / 2.0;

        Vector2D upperLeft = new Vector2D(viewportCenterWC.getX() + xOffset, viewportCenterWC.getY() + yOffset);
        if(upperLeft.getX() < 0)
        {
            upperLeft.setX(0);
        }
        else if(upperLeft.getX() - (2 * xOffset) > DRAWING_AREA_SIZE)
        {
             double offset = ((upperLeft.getX() - (2 * xOffset)) - DRAWING_AREA_SIZE);
            upperLeft.addToX(-offset);
        }

        if(upperLeft.getY() < 0)
        {
            upperLeft.setY(0);
        }
        else if(upperLeft.getY() - (2 * yOffset) > DRAWING_AREA_SIZE)
        {
            double offset = ((upperLeft.getY() - (2 * yOffset)) - DRAWING_AREA_SIZE);
            upperLeft.addToY(-offset);
        }

        GUIFunctions.setHScrollBarPosit((int) upperLeft.getX());
        GUIFunctions.setVScrollBarPosit((int) upperLeft.getY());

        WorldToScreen.getInstance().setViewportUpperLeftX((int) upperLeft.getX());
        WorldToScreen.getInstance().setViewportUpperLeftY((int) upperLeft.getY());

        _lockScrollBars = false;
    }

    @Override
    public void colorButtonHit(Color c) {
        GUIFunctions.changeSelectedColor(c);
        cs355.model.Context.getInstance().setCurrentColor(c);
        if(_shapeBuilderStrategy instanceof MouseSelectionBehavior)
        {
            Shape selectedShape = ((MouseSelectionBehavior) _shapeBuilderStrategy).getSelectedShape();
            DrawingFacade.getInstance().setShapeColor(selectedShape, c);
        }
    }

    @Override
    public void triangleButtonHit() {
        _shapeBuilderStrategy = new MouseTriangleBuilderStrategy();
        LabOneViewRefresher.getInstance().setSelection(null);
    }

    @Override
    public void squareButtonHit() {
        _shapeBuilderStrategy = new MouseSquareBuilderStrategy();
        LabOneViewRefresher.getInstance().setSelection(null);
    }

    @Override
    public void rectangleButtonHit() {
        _shapeBuilderStrategy = new MouseRectangleBuilderStrategy();
        LabOneViewRefresher.getInstance().setSelection(null);
    }

    @Override
    public void circleButtonHit() {
        _shapeBuilderStrategy = new MouseCircleBuilderStrategy();
        LabOneViewRefresher.getInstance().setSelection(null);
    }

    @Override
    public void ellipseButtonHit() {
        _shapeBuilderStrategy = new MouseEllipseBuilderStrategy();
        LabOneViewRefresher.getInstance().setSelection(null);
    }

    @Override
    public void lineButtonHit() {
        _shapeBuilderStrategy = new MouseLineBuilderStrategy();
        LabOneViewRefresher.getInstance().setSelection(null);
    }

    @Override
    public void selectButtonHit() {
        _shapeBuilderStrategy = new MouseSelectionBehavior();
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void zoomInButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.

        double scaleFactor = WorldToScreen.getInstance().getScaleFactor();
        if(scaleFactor < 4.0)
        {
            scaleFactor *= 2;
            WorldToScreen.getInstance().setScaleFactor(scaleFactor);
        }
        updateScrollBars();
        GUIFunctions.refresh();
    }

    @Override
    public void zoomOutButtonHit() {
        double scaleFactor = WorldToScreen.getInstance().getScaleFactor();
        if(scaleFactor > 0.25)
        {
            scaleFactor /= 2.0;
            WorldToScreen.getInstance().setScaleFactor(scaleFactor);
        }
        updateScrollBars();
        GUIFunctions.refresh();
    }

    @Override
    public void hScrollbarChanged(int value) {
        if(_lockScrollBars)
            return;

        WorldToScreen.getInstance().setViewportUpperLeftX((double) value);
        GUIFunctions.refresh();
    }
    @Override
    public void vScrollbarChanged(int value) {
        if(_lockScrollBars)
            return;

        WorldToScreen.getInstance().setViewportUpperLeftY((double) value);
        System.out.println("Scroll change: " + Double.toString(value));
        GUIFunctions.refresh();
    }

    @Override
    public void toggle3DModelDisplay() {
        //To change body of implemented methods use File | Settings | File Templates.
        _render3d = !_render3d;
        GUIFunctions.refresh();
    }

    public boolean getRender3d()
    {
        return _render3d;
    }

    public boolean getDrawBackground()
    {
        return _drawBackground;
    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {
        if(!_render3d)
            return;

        while(iterator.hasNext())
        {
            Integer key = iterator.next();
            if(key == KeyEvent.VK_W)
            {
                _camera.moveForward();
            }
            else if(key == KeyEvent.VK_S)
            {
                _camera.moveBackward();
            }
            else if(key == KeyEvent.VK_D)
            {
                _camera.moveRight();
            }
            else if(key == KeyEvent.VK_A)
            {
                _camera.moveLeft();
            }
            else if(key == KeyEvent.VK_E)
            {
               _camera.rotRight();
            }
            else if(key == KeyEvent.VK_Q)
            {
                _camera.rotLeft();
            }
            else if(key == KeyEvent.VK_R)
            {
                _camera.moveUp();
            }
            else if(key == KeyEvent.VK_F)
            {
                _camera.moveDown();
            }
            else if(key == KeyEvent.VK_H)
            {
                _camera.goHome();
            }
        }

        GUIFunctions.refresh();
    }

    @Override
    public void doEdgeDetection() {
        if(DrawingFacade.getInstance().backgroundImageLoaded() && _drawBackground) {
            DrawingFacade.getInstance().getBackgroundImage().detectEdges();
            GUIFunctions.refresh();
        }
    }

    @Override
    public void doSharpen() {
        if(DrawingFacade.getInstance().backgroundImageLoaded() && _drawBackground) {
            DrawingFacade.getInstance().getBackgroundImage().sharpen();
            GUIFunctions.refresh();
        }
    }

    @Override
    public void doMedianBlur() {
        if(DrawingFacade.getInstance().backgroundImageLoaded() && _drawBackground) {
            DrawingFacade.getInstance().getBackgroundImage().median();
            GUIFunctions.refresh();
        }
    }

    @Override
    public void doUniformBlur() {
        if(DrawingFacade.getInstance().backgroundImageLoaded() && _drawBackground) {
            DrawingFacade.getInstance().getBackgroundImage().blur();
            GUIFunctions.refresh();
        }
    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {
        if(DrawingFacade.getInstance().backgroundImageLoaded() && _drawBackground) {
            DrawingFacade.getInstance().getBackgroundImage().adjustContrast(contrastAmountNum);
            GUIFunctions.refresh();
        }
    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {
        if(DrawingFacade.getInstance().backgroundImageLoaded() && _drawBackground) {
            DrawingFacade.getInstance().getBackgroundImage().adjustBrightness(brightnessAmountNum);
            GUIFunctions.refresh();
        }
    }

    @Override
    public void doLoadImage(BufferedImage openImage) {
        DrawingFacade.getInstance().setBackgroundImage(openImage);
        GUIFunctions.refresh();
    }

    @Override
    public void toggleBackgroundDisplay() {
        _drawBackground = !_drawBackground;
        GUIFunctions.refresh();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mousePressed(mouseEvent);
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mouseReleased(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mouseEntered(mouseEvent);
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mouseExited(mouseEvent);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mouseDragged(mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        _shapeBuilderStrategy.mouseMoved(mouseEvent);
    }
}
