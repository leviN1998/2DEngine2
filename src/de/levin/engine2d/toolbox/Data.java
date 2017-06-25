package de.levin.engine2d.toolbox;

/**
 * Created by levin on 12.06.2017.
 */
public class Data {

    public static final float[] positions = {
            /*-10.0f, -10.0f * 9.0f / 16.0f,
            -10.0f,  10.0f * 9.0f / 16.0f,
            0.0f,  10.0f * 9.0f / 16.0f,
            0.0f, -10.0f * 9.0f / 16.0f*/
            -1, -1,
            -1, 1,
            1, 1,
            1, -1
    };

    public static final int[] indices = {
            0, 1, 3,
            3, 1, 2
    };

    public static final float[] textureCoords = {
            1,1,
            1,0,
            0,0,
            0,1
    };

}
