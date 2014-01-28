package cs355.solution;

import cs355.model.*;
import cs355.model.Canvas;
import cs355.model.Shape;
import cs355.view.DrawableSelectionOutline;
import cs355.view.DrawableShapeFactory;
import cs355.view.LabOneViewRefresher;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 1/25/14
 * Time: 4:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MouseSelectionBehavior extends MouseShapeBuilderStrategy
{
    enum SelectionMode
    {
        NONE,
        TRANSLATE,
        ROTATE,
        SCALE
    }

    /**The currently selected shape*/
    private Shape _selectedShape;
    /**The current selection */
    DrawableSelectionOutline _drawableSelection;
    /**The first click point*/
    Point _p0;
    /**The initial angle*/
    double _initAngle;
    /**The selection mode*/
    SelectionMode _selectionMode;


    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        _p0 = mouseEvent.getPoint();

        if(_selectedShape != null)
        {
            assert(_drawableSelection != null);
            if(_drawableSelection.rotHandleSelected(new Vector2D(mouseEvent.getPoint())))
            {
                _selectionMode = SelectionMode.ROTATE;
                ObjToWorldTransform objToWorld = _selectedShape.getObjToWorldTransform();
                _initAngle = objToWorld.getObjToWorldRot();
                return;
            }
        }

        Canvas canvas = Canvas.getInstance();
        _selectedShape = canvas.selectShape(new Vector2D(mouseEvent.getPoint()), 4);
        _drawableSelection = null;
        if(_selectedShape != null)
            _drawableSelection = DrawableShapeFactory.getDrawableSelection(_selectedShape);
        LabOneViewRefresher.getInstance().setSelection(_drawableSelection);

        if(_selectedShape != null)
            _selectionMode = SelectionMode.TRANSLATE;
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        if(_selectedShape == null)
            return;

        switch (_selectionMode)
        {
            case TRANSLATE:
                translateSelected(mouseEvent);
                break;

            case ROTATE:
                rotatingSelected(mouseEvent);
                break;

            default:
                assert(false);
                break;
        }
    }

    private void translateSelected(MouseEvent mouseEvent)
    {
        Point p1 = mouseEvent.getPoint();
        double xOffset = p1.getX() - _p0.getX();
        double yOffset = p1.getY() - _p0.getY();
        _p0 = p1;
        DrawingFacade.getInstance().translateShape(new Vector2D(xOffset, yOffset));
    }

    private void rotatingSelected(MouseEvent mouseEvent)
    {
        Point p1 = mouseEvent.getPoint();
        Vector2D center = _selectedShape.getCenter();
        ObjToWorldTransform objToWorldTransform = _selectedShape.getObjToWorldTransform();
        Vector2D centerWC = objToWorldTransform.getWorldCoords(center);
        double xOffset = p1.getX() - centerWC.getX();
        double yOffset = p1.getY() - centerWC.getY();

        Vector2D v0 = Vector2D.sub(new Vector2D(_p0), centerWC);
        v0.normalize();
        Vector2D v1 = new Vector2D(xOffset, yOffset);
        v1.normalize();

        double alpha = Math.atan2(v0.getY(), v0.getX());
        double beta = Math.atan2(v1.getY(), v1.getX());
        double theta =  (beta - alpha) + _initAngle;
        DrawingFacade.getInstance().rotateSelectedShape(theta);
    }
}
