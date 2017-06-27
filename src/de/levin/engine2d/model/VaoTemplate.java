package de.levin.engine2d.model;

import de.levin.engine2d.toolbox.utils.VaoUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by levin on 27.06.2017.
 */
public abstract class VaoTemplate {

    protected int ID;
    //Unbeding setzen
    protected int vertexCount;
    protected List<Integer> attribs;

    /**
     * Draw-Methode implementieren
     * GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
     */

    public VaoTemplate(){
        ID = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(ID);
        attribs = new ArrayList<>();

        /**
         * Attribs binden
         * unbind
         * vertexCount
         */
    }


    public void addAttrib(int attribNumber, int coordinateSize, float[] data){
        VaoUtils.storeDataInAttributeList(attribNumber, coordinateSize, data);
        attribs.add(attribNumber);
    }

    public void bindIndicesBuffer(int[] indices){
        VaoUtils.bindIndicesBuffer(indices);
    }

    protected void draw(){
        bind();
        GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
    }

    public void bind(){
        GL30.glBindVertexArray(ID);
        for (int i : attribs){
            GL20.glEnableVertexAttribArray(i);
        }
    }

    public void unbind(){
        for (int i : attribs){
            GL20.glDisableVertexAttribArray(i);
        }
        GL30.glBindVertexArray(0);
    }

    public void cleanUp(){
        GL20.glDisableVertexAttribArray(ID);
        VaoUtils.cleanUp();
    }
}
