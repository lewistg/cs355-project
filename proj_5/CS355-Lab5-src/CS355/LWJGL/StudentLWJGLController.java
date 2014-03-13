package CS355.LWJGL;


//You might notice a lot of imports here.
//You are probably wondering why I didn't just import org.lwjgl.opengl.GL11.*
//Well, I did it as a hint to you.
//OpenGL has a lot of commands, and it can be kind of intimidating.
//This is a list of all the commands I used when I implemented my project.
//Therefore, if a command appears in this list, you probably need it.
//If it doesn't appear in this list, you probably don't.
//Of course, your milage may vary. Don't feel restricted by this list of imports.
import org.lwjgl.input.Keyboard;

import java.util.Iterator;
import java.lang.Math;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 *
 * @author Brennan Smith
 */
public class StudentLWJGLController implements CS355LWJGLController
{
    /** My position */
    private Point3D myPos = new Point3D(0.0, 0.0, 30.0);
    /**My angle */
    private float myAngle = 0.0f;
    /**Forward vector*/
    private Point3D myDir = new Point3D(0.0, 0.0, -1.0);
    /**Right vector*/
    private Point3D myRight = new Point3D(1.0, 0.0, 0.0);
    /**Projection mode*/
    private boolean projMode = false;

  //This is a model of a house.
  //It has a single method that returns an iterator full of Line3Ds.
  //A "Line3D" is a wrapper class around two Point2Ds.
  //It should all be fairly intuitive if you look at those classes.
  //If not, I apologize.
  private WireFrame model = new HouseModel();

  //This method is called to "resize" the viewport to match the screen.
  //When you first start, have it be in perspective mode.
  @Override
  public void resizeGL() 
  {
      glViewport(0, 0, LWJGLSandbox.DISPLAY_WIDTH, LWJGLSandbox.DISPLAY_HEIGHT);
  }

    @Override
    public void update() 
    {
        
    }

    //This is called every frame, and should be responsible for keyboard updates.
    //An example keyboard event is captured below.
    //The "Keyboard" static class should contain everything you need to finish
    // this up.
    @Override
    public void updateKeyboard() 
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W)) 
        {
            System.out.println("My dir dir: " + myDir);
            myPos = new Point3D(myPos.x + myDir.x, myPos.y + myDir.y, myPos.z + myDir.z);
            //myPos.z -= 1;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            myPos = new Point3D(myPos.x - myDir.x, myPos.y - myDir.y, myPos.z - myDir.z);
            //myPos.z += 1;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            myPos = new Point3D(myPos.x - myRight.x, myPos.y - myRight.y, myPos.z - myRight.z);
            //myPos.x -= 1;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            myPos = new Point3D(myPos.x + myRight.x, myPos.y + myRight.y, myPos.z + myRight.z);
            //myPos.x += 1;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {
            myPos.y += 1;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_F))
        {
            myPos.y -= 1;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_Q))
        {
            myAngle -= 1;
            double oneRad = (1.0 / 180.0) * Math.PI;
            double newDirX = Math.cos(-oneRad) * myDir.x - Math.sin(-oneRad) * myDir.z;
            double newDirZ = Math.sin(-oneRad) * myDir.x + Math.cos(-oneRad) * myDir.z;

            myDir.x = newDirX;
            myDir.z = newDirZ;

            double newRightX = Math.cos(-oneRad) * myRight.x - Math.sin(-oneRad) * myRight.z;
            double newRightZ = Math.sin(-oneRad) * myRight.x + Math.cos(-oneRad) * myRight.z;

            myRight.x = newRightX;
            myRight.z = newRightZ;
            System.out.println("New dir: " + myDir);
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_E))
        {
            myAngle += 1;
            double oneRad = (1.0 / 180.0) * Math.PI;
            double newDirX = Math.cos(oneRad) * myDir.x - Math.sin(oneRad) * myDir.z;
            double newDirZ = Math.sin(oneRad) * myDir.x + Math.cos(oneRad) * myDir.z;

            myDir.x = newDirX;
            myDir.z = newDirZ;

            double newRightX = Math.cos(oneRad) * myRight.x - Math.sin(oneRad) * myRight.z;
            double newRightZ = Math.sin(oneRad) * myRight.x + Math.cos(oneRad) * myRight.z;

            myRight.x = newRightX;
            myRight.z = newRightZ;

            System.out.println("New dir: " + myDir);
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_O))
        {
            projMode = false;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_P))
        {
            projMode = true;
        }
    }

    //This method is the one that actually draws to the screen.
    @Override
    public void render() 
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        //System.out.println("Proj mode: " + projMode);

        if(projMode)
        {
            float aspectRatio = (float) LWJGLSandbox.DISPLAY_WIDTH / (float) LWJGLSandbox.DISPLAY_HEIGHT;
            gluPerspective(60, aspectRatio, 1, 1000);
        }
        else
        {
            //glOrtho(-20, 20, -20, 20, -20, 20);
            glOrtho(-20, 20, -20, 20, 0, 1000);
        }

        //glOrtho(-20, 20, -20, 20, -20, 20);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glRotatef(myAngle, 0.0f, 1.0f, 0.0f);
        glTranslatef((float) -myPos.x, (float) -myPos.y, (float) -myPos.z);

        glClear(GL_COLOR_BUFFER_BIT);
        
        //Do your drawing here.
        glColor3f(1.0f, 1.0f, 1.0f);
        Iterator<Line3D> lineItr = model.getLines();
        while(lineItr.hasNext())
        {
            Line3D line = lineItr.next();
            glBegin(GL_LINES);
                glVertex3d(line.start.x, line.start.y, line.start.z);
                glVertex3d(line.end.x, line.end.y, line.end.z);
            glEnd();
        }

        glFlush();
    }
    
}
