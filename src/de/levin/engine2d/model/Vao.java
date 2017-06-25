package de.levin.engine2d.model;

import de.levin.engine2d.toolbox.utils.VaoUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

/**
 * Created by levin on 21.06.2017.
 */
public class Vao {

    private int ID;
    private int vertexCount;

    public Vao(float[] positions, float[] textureCoordinates, int[] indices){
        ID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(ID);

        VaoUtils.bindIndicesBuffer(indices);
        VaoUtils.storeDataInAttributeList(0,2,positions);
        VaoUtils.storeDataInAttributeList(1,2,textureCoordinates);
        unbind();

        vertexCount = indices.length;
    }

    public void bind(){
        GL30.glBindVertexArray(ID);
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
    }

    public void draw(){
        bind();
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
    }

    public void unbind(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    public void cleanUp(){
        GL30.glDeleteVertexArrays(ID);
        VaoUtils.cleanUp();
    }


}
