package cs355.solution;

import cs355.model.ObjToWorldTransform;
import cs355.model.Rectangle;
import cs355.model.Vector2D;

/**
 * Created with IntelliJ IDEA.
 * User: ty
 * Date: 2/7/14
 * Time: 11:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ResizeShape
{
    public static void resizeRectangle(Rectangle rect, int selectedCorner, Vector2D p0WC, Vector2D p1WC)
    {
        // get the corner position in world coordinates
        ObjToWorldTransform t = rect.getObjToWorldTransform();
        Vector2D p0Obj = t.getObjectCoords(p0WC);
        Vector2D p1Obj = t.getObjectCoords(p1WC);
        Vector2D offset = Vector2D.sub(p1Obj, p0Obj);
    }
}
