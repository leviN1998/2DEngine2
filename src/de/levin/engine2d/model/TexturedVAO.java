package de.levin.engine2d.model;

import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.DisplayManager;
import de.levin.engine2d.toolbox.MatrixMaths;
import de.levin.engine2d.toolbox.Texture;
import de.levin.engine2d.toolbox.utils.TextureUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 27.06.2017.
 */
public class TexturedVAO {
    private Vector2f position;
    private Vector2f scale;
    private float rotation;
    private Texture texture;
    private Vao vao;

    public TexturedVAO(String texturePath, Vector2f position, Vector2f scale, float rotation){
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;

        texture = new Texture(texturePath);
        float[] positions = TextureUtils.calculateVertices(texture.getWidth(), texture.getHeight(), 10);
        vao = new Vao(positions, Data.textureCoords, Data.indices);
    }

    public void draw (ModularShader activeShader){
        vao.bind();
        texture.bind();
        Matrix4f m = MatrixMaths.createTransformationMatrix(position, scale, rotation);
        activeShader.setUniformMat4("transformation", m);
        activeShader.setUniformMat4("pr_matrix", DisplayManager.getPr_matrix());
        vao.draw();
        texture.unbind();
    }

    public void unbind(){
        vao.unbind();
    }

    public void cleanUp(){
        vao.cleanUp();
    }

}
