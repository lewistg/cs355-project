package cs355.solution;

import cs355.CS355Controller;
import cs355.GUIFunctions;
import cs355.model.*;
import cs355.model.Shape;
import cs355.view.LabOneViewRefresher;

import java.awt.*;
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
    /**
     * Controls how shapes are built from mouse-click events.
     */
    MouseShapeBuilderStrategy _shapeBuilderStrategy;

    /**
     * Constructor
     */
    public LabOneController()
    {
        _shapeBuilderStrategy = new MouseLineBuilderStrategy();
    }

    public void init()
    {
        GUIFunctions.setHScrollBarMax(2048);
        GUIFunctions.setVScrollBarMax(2048);

        WorldToScreen.getInstance().setViewportCenter(new Vector2D(1024, 1024));
        updateScrollBars();
    }

    private void updateScrollBars()
    {
        // the knob size is proportional to the viewport size
        int knobSize = (int) (512 * WorldToScreen.getInstance().getScaleFactor());

        Vector2D viewportCenterWC = WorldToScreen.getInstance().getViewportCenterWC();

        GUIFunctions.setVScrollBarKnob(knobSize);
        GUIFunctions.setHScrollBarKnob(knobSize);

        // recenter the scroll bar to be on the center
        double xOffset = -knobSize / 2.0;
        double yOffset = -knobSize / 2.0;

        Vector2D upperLeft = new Vector2D(viewportCenterWC.getX() + xOffset, viewportCenterWC.getY() + yOffset);
        GUIFunctions.setHScrollBarPosit((int) upperLeft.getX());
        GUIFunctions.setVScrollBarPosit((int) upperLeft.getY());

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

        System.out.println("Before: " + WorldToScreen.getInstance().getUpperLeftViewportCorner().toString());
        System.out.println("Before center: " + WorldToScreen.getInstance().getViewportCenterWC().toString());

        double scaleFactor = WorldToScreen.getInstance().getScaleFactor();
        if(scaleFactor > 0.25)
        {
            scaleFactor /= 2.0;
            WorldToScreen.getInstance().setScaleFactor(scaleFactor);
            System.out.println("After center: " + WorldToScreen.getInstance().getViewportCenterWC().toString());
            System.out.println("After center: " + WorldToScreen.getInstance().getViewportCenterWC().toString());
        }
        updateScrollBars();
        System.out.println("After center: " + WorldToScreen.getInstance().getViewportCenterWC().toString());
        System.out.println("After: " + WorldToScreen.getInstance().getUpperLeftViewportCorner().toString());
        System.out.println("After center: " + WorldToScreen.getInstance().getViewportCenterWC().toString());
        GUIFunctions.refresh();
    }

    @Override
    public void zoomOutButtonHit() {
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
    public void hScrollbarChanged(int value) {
        WorldToScreen.getInstance().setViewportUpperLeftX((double) value);
        GUIFunctions.refresh();
    }

    @Override
    public void vScrollbarChanged(int value) {
        WorldToScreen.getInstance().setViewportUpperLeftY((double) value);
        GUIFunctions.refresh();
    }

    @Override
    public void toggle3DModelDisplay() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(Iterator<Integer> iterator) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doEdgeDetection() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doSharpen() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doMedianBlur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doUniformBlur() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doChangeContrast(int contrastAmountNum) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doChangeBrightness(int brightnessAmountNum) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doLoadImage(BufferedImage openImage) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void toggleBackgroundDisplay() {
        //To change body of implemented methods use File | Settings | File Templates.
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
