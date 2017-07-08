package de.levin.engine2d.toolbox.utils;

import de.levin.engine2d.hitbox.SquareHitbox;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 08.07.2017.
 */
public class ButtonMaths {

    public static boolean checkCoords(SquareHitbox hitbox, Vector2f position){
        Vector2f x = new Vector2f(hitbox.getA().x, hitbox.getD().x);    //links, rechts
        Vector2f y = new Vector2f(hitbox.getB().y, hitbox.getA().y);    //unten, oben
        if (position.x > x.x && position.x < x.y){
            if (position.y > y.x && position.y < y.y){
                return true;
            }
        }

        return false;
    }

    public static Vector2f getNormalizedDeviceCoords(Vector2f displayCoords){
        float vecX = (2f*displayCoords.x) / Display.getWidth() - 1f;
        float vecY = (2f*displayCoords.y) / Display.getHeight() - 1f;
        return new Vector2f(vecX,vecY);
    }

    public static Vector2f getDisplayCoords(Vector2f ndc){
        float x = ((ndc.x+1) * Display.getWidth()) / 2;
        float y = ((ndc.y+1) * Display.getHeight()) / 2;
        return new Vector2f(x,y);
    }


}
