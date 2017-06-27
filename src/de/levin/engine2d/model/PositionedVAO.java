package de.levin.engine2d.model;

import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.DisplayManager;
import de.levin.engine2d.toolbox.MatrixMaths;
import de.levin.engine2d.toolbox.utils.TextureUtils;
import javafx.geometry.Pos;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by levin on 21.06.2017.
 */
public class PositionedVAO {

    private Vector2f position;
    private Vector2f scale;
    private float rotation;
    private Vao vao;
    private Vector4f color;

    public PositionedVAO(Vao vao, Vector2f position, Vector2f scale, float rotation){
        this.position = position;
        this.vao = vao;
        this.scale = scale;
        this.rotation = rotation;
        this.color = new Vector4f(0, 0, 1, 1);
    }

    public static PositionedVAO createQuad(int width, int height){
        float[] pos = TextureUtils.calculateVertices(width, height, 1);
        Vao vao = new Vao(pos, Data.textureCoords, Data.indices);
        return new PositionedVAO(vao, new Vector2f(0,0), new Vector2f(1,1), 0);
    }

    public void draw(ModularShader activeShader){
        vao.bind();
        Matrix4f m = MatrixMaths.createTransformationMatrix(position, scale, rotation);
        activeShader.setUniformMat4("transformation", m);
        activeShader.setUniformMat4("pr_matrix", DisplayManager.getPr_matrix());
        activeShader.setUniformVector4f("uni_color", color);
        vao.draw();
    }

    public void rotate(float rot){
        this.rotation += rot;
    }

    public void move(float dx, float dy){
        this.position.x += dx;
        this.position.y += dy;
    }

    public void setColor(Vector4f color){
        this.color = color;
    }

    public void unbind(){
        vao.unbind();
    }

    public void cleanUp(){
        vao.cleanUp();
    }

}
