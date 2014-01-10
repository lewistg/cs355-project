package cs355.solution;

import cs355.CS355Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/10/14
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class LabOneController implements CS355Controller
{
    @Override
    public void colorButtonHit(Color c) {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println("Hi from the color button");
    }

    @Override
    public void triangleButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void squareButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void rectangleButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void circleButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void ellipseButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void lineButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void selectButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void zoomInButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void zoomOutButtonHit() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void hScrollbarChanged(int value) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void vScrollbarChanged(int value) {
        //To change body of implemented methods use File | Settings | File Templates.
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
}
