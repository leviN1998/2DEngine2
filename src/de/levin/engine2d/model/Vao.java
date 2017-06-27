package de.levin.engine2d.model;

import de.levin.engine2d.toolbox.utils.VaoUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * Created by levin on 21.06.2017.
 */
public class Vao extends VaoTemplate{

    public Vao(float[] positions, float[] textureCoordinates, int[] indices){
        super();
        bindIndicesBuffer(indices);
        addAttrib(0,2, positions);
        addAttrib(1,2,textureCoordinates);
        vertexCount = indices.length;
    }
}
