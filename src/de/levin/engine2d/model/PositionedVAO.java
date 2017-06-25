package de.levin.engine2d.model;

import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.DisplayManager;
import de.levin.engine2d.toolbox.MatrixMaths;
import de.levin.engine2d.toolbox.utils.TextureUtils;
import javafx.geometry.Pos;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 21.06.2017.
 */
public class PositionedVAO {

    private Vector2f position;
    private Vao vao;

    public PositionedVAO(Vao vao, Vector2f position){
        this.position = position;
        this.vao = vao;
    }

    public static PositionedVAO createQuad(int width, int height){
        float[] pos = TextureUtils.calculateVertices(width, height);
        Vao vao = new Vao(pos, Data.textureCoords, Data.indices);
        return new PositionedVAO(vao, new Vector2f(0,0));
    }

    public void draw(ModularShader activeShader){
        vao.bind();
        Matrix4f m = MatrixMaths.createTransformationMatrix(position, new Vector2f(1f,1f), 0);
        activeShader.setUniformMat4("transformation", m);
        activeShader.setUniformMat4("pr_matrix", DisplayManager.getPr_matrix());
        vao.draw();
    }

    public void unbind(){
        vao.unbind();
    }

    public void cleanUp(){
        vao.cleanUp();
    }

}
