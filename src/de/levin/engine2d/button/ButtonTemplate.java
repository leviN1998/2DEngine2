package de.levin.engine2d.button;

import de.levin.engine2d.hitbox.SquareHitbox;
import de.levin.engine2d.toolbox.DisplayManager;
import de.levin.engine2d.toolbox.MatrixMaths;
import de.levin.engine2d.toolbox.utils.ButtonMaths;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by levin on 08.07.2017.
 */
public abstract class ButtonTemplate {

    private SquareHitbox hitbox;

    protected Vector2f position;
    protected Vector2f scale;
    protected float rotation;

    protected float width;
    protected float height;

    protected boolean pressed;
    protected boolean hovered;


    public ButtonTemplate(Vector2f position, Vector2f scale, float rotation, float width, float height){

        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        this.width = width;
        this.height = height;
        preInit();
        init();
    }

    private void preInit(){
        this.hitbox = new SquareHitbox(position, width, height);
        this.pressed = false;
        this.hovered = false;
    }

    protected abstract void init();

    public void update(Vector2f displayCoords){

        //Mouse Buttons:
        // 0 = left
        // 1 = right

        if (ButtonMaths.checkCoords(hitbox, getCursorCoords(displayCoords))){
            if (!hovered) {
                hovered = true;
                onHover();
            }
            if (Mouse.isButtonDown(0) && !pressed){
                onPress();
                pressed = true;
            }

            if (pressed && !Mouse.isButtonDown(0)){
                pressed = false;
                onRelease();
            }
        }else if (hovered){
            hovered = false;
            redoHover();
        }

    }

    protected abstract void onHover();

    protected abstract void redoHover();

    protected abstract void onPress();

    protected abstract void onRelease();

    private Vector2f getCursorCoords(Vector2f displayCoords){
        Vector2f ndcs = ButtonMaths.getNormalizedDeviceCoords(displayCoords);
        Vector4f v = new Vector4f(ndcs.x, ndcs.y, 0, 0);
        Matrix4f invPrMatrix = Matrix4f.invert(DisplayManager.getPr_matrix(), null);
        float[] coords = MatrixMaths.multiplyM4fWithV4f(MatrixMaths.matrix4fToArray(invPrMatrix), MatrixMaths.vector4fToArray(v));
        Vector2f cursor = new Vector2f(coords[0], coords[1]);
        return cursor;
    }

}
