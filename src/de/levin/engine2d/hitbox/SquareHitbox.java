package de.levin.engine2d.hitbox;

import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.model.PositionedVAO;
import de.levin.engine2d.model.Vao;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.utils.TextureUtils;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by levin on 27.06.2017.
 */
public class SquareHitbox {

    private Vector2f a;
    private Vector2f b;
    private Vector2f d;

    //Nicht immer wichtig
    private PositionedVAO vao;
    private Vector2f middle;
    private float w;
    private float h;

    public SquareHitbox(Vector2f middle, float width, float height){
        this.w = width;
        this.h = height;

        this.a = new Vector2f(middle.x - (width/2f), middle.y + (height/2f));
        this.b = new Vector2f(middle.x - (width/2f), middle.y - (height/2f));
        this.d = new Vector2f(middle.x + (width/2f), middle.y + (height/2f));
    }


    public Vector2f getA() {
        return a;
    }

    public Vector2f getB() {
        return b;
    }

    public Vector2f getD() {
        return d;
    }


    /**
     * Nur zum Debuggen
     */

    public void createVao(){
        Vao vao = new Vao(TextureUtils.calcOriginalSizeVetices(w,h), Data.textureCoords, Data.indices);
        this.vao = new PositionedVAO(vao, middle, new Vector2f(1,1), 0);
        this.vao.setColor(new Vector4f(0,1,0,1));
    }

    public void draw(ModularShader colorShader){
        vao.draw(colorShader);
    }



}
