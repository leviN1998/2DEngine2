package de.levin.engine2d.button;

import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.model.PositionedVAO;
import de.levin.engine2d.model.Vao;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.utils.TextureUtils;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 08.07.2017.
 */
public class ButtonExample extends ButtonTemplate{

    private Vector2f scaleBuffer;

    private PositionedVAO texture;
    private boolean rotationState;

    public ButtonExample(Vector2f position, Vector2f scale, float rotation, float width, float height){
        super(position, scale, rotation, width, height);
        scaleBuffer = new Vector2f(scale.x, scale.y);
    }

    @Override
    protected void init() {
        Vao vao = new Vao(TextureUtils.calcOriginalSizeVetices(width,height), Data.textureCoords, Data.indices);
        this.texture = new PositionedVAO(vao, position, scale, rotation);
    }

    @Override
    protected void onHover() {
        scale.x += 0.2f;
        scale.y += 0.2f;
    }

    @Override
    protected void redoHover(){
        scale.x = scaleBuffer.x;
        scale.y = scaleBuffer.y;
        rotation = 0;
    }

    @Override
    protected void onPress() {
        scale.x -= 0.5f;
        scale.y -= 0.5f;
    }

    @Override
    protected void onRelease() {
        scale.x = scaleBuffer.x;
        scale.y = scaleBuffer.y;
        rotationState = true;
        rotCount = 0;
        maxRot = 180;
    }

    @Override
    public void update(Vector2f displayCoords){
        super.update(displayCoords);
        roationStuff();

    }

    int rotCount = 0;
    int maxRot = 0;
    public void roationStuff(){
        if (rotationState){
            if (rotCount >= maxRot){
                rotationState = false;
                return;
            }
            texture.rotate(1);
            rotCount ++;
        }
    }

    public void draw(ModularShader activeShader){
        texture.draw(activeShader);
    }
}
