package de.levin.engine2d.toolbox.utils;

import de.levin.engine2d.toolbox.Texture;
import org.lwjgl.util.vector.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 11.06.2017.
 */
public class TextureUtils {

    public static float max(float a, float b){
        if(a > b){
            return a;
        }
        return b;
    }

    public static Vector2f getDimension(Texture texture, Vector2f scale){
        int width = texture.getWidth();
        int height = texture.getHeight();
        float max = max(width,height);

        float pre_x = (width/max) * scale.x;
        float pre_y = (height/max) * scale.y;

        return new Vector2f(pre_x,pre_y);
    }

    public static float[] calculateVertices(int width, int height, float factor){
        float max = max(width,height);
        float w = ((float) width / 2f) / max;
        float h = ((float) height / 2f) / max;
        w = w * factor;
        h = h * factor;

        Vector2f a = new Vector2f(-w, h);
        Vector2f b = new Vector2f(-w, -h);
        Vector2f c = new Vector2f(w, -h);
        Vector2f d = new Vector2f(w, h);

        return calcTriangles(a,b,c,d);
    }

    public static float[] calcOriginalSizeVetices(float width, float height){
        float w = width / 2f;
        float h = height / 2f;

        Vector2f a = new Vector2f(-w, h);
        Vector2f b = new Vector2f(-w, -h);
        Vector2f c = new Vector2f(w, -h);
        Vector2f d = new Vector2f(w, h);

        return calcTriangles(a,b,c,d);
    }

    public static float[] calcTriangles(Vector2f a, Vector2f b, Vector2f c, Vector2f d){
        ArrayList<Float> list = new ArrayList<>();
        vectorToList(a,list);
        vectorToList(b,list);
        vectorToList(c,list);
        vectorToList(d,list);
        float[] out = new float[2*3*2];
        for (int i = 0;i<list.size();i++){
            out[i] = list.get(i);
        }
        return out;
    }

    private static void vectorToList(Vector2f v, List<Float> l){
        l.add(v.x);
        l.add(v.y);
    }

    public static BufferedImage spiegeln(BufferedImage img){
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(w,h,img.getType());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(img,0,0,w,h,w,0,0,h,null);
        g.dispose();
        return dimg;
    }

}
