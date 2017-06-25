package de.levin.engine2d.toolbox;

import de.levin.engine2d.toolbox.utils.TextureUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by levin on 01.11.2016.
 */
public class DisplayManager {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 120;
    private static Matrix4f pr_matrix;

    public static void createDisplay(){

        ContextAttribs attribs = new ContextAttribs(3,2)
        .withForwardCompatible(true)
        .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
            Display.create(new PixelFormat(), attribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }


        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        createPrMatrix(WIDTH,HEIGHT);

    }

    public static void updateDisplay(){

        Display.sync(FPS_CAP);
        Display.update();

    }

    public static void setUpRendering2D(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,1,1,1);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
    }

    public static void endRendering2D(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }

    public static void closeDisplay(){
        Display.destroy();
    }

    private static void createPrMatrix(int width, int height){
        float max = TextureUtils.max(width, height);
        float w = width / max;
        float h = height / max;

        float f = 10 * h / w;

        pr_matrix = MatrixMaths.orthographic(-10, 10, -f, f, -1, 1);
    }

    public static Matrix4f getPr_matrix(){
        return pr_matrix;
    }

}
