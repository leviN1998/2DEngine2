package de.levin.engine2d;


import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.model.PositionedVAO;
import de.levin.engine2d.model.TexturedVAO;
import de.levin.engine2d.model.Vao;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.DisplayManager;;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by levin on 21.06.2017.
 */
public class MainExample implements Runnable{

    //Tools

    //Render
    TexturedVAO vao;
    ModularShader pass;
    PositionedVAO colorVao;
    ModularShader color;


    @Override
    public void run() {
        DisplayManager.createDisplay();

        init();
        while (!org.lwjgl.opengl.Display.isCloseRequested()){
            update();
            render();

            DisplayManager.updateDisplay();
        }
        cleanUp();
        DisplayManager.closeDisplay();
    }

    public void init(){
        vao = new TexturedVAO("src/de/levin/engine2d/toolbox/testres/Collegeblockteil.jpg", new Vector2f(0,0),
                new Vector2f(1,1), 0);
        pass = ModularShader.createPassThrough();

        colorVao = new PositionedVAO(new Vao(Data.positions,Data.textureCoords, Data.indices), new Vector2f(3,0),
                new Vector2f(1,1), 0);
        colorVao.setColor(new Vector4f(0,1,1,1));
        color = ModularShader.createColorShader();
    }

    public void update(){

    }

    public void render(){
        DisplayManager.setUpRendering2D();
        pass.enable();

        vao.draw(pass);
        vao.unbind();

        pass.disable();

        color.enable();
        colorVao.draw(color);
        colorVao.unbind();
        color.disable();

        DisplayManager.endRendering2D();
    }

    public void cleanUp(){
        vao.cleanUp();
        pass.cleanUp();
    }


    public static void main(String[] args){
        new MainExample().run();
    }
}
