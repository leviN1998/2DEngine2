package de.levin.engine2d;


import de.levin.engine2d.Shader.ModularShader;
import de.levin.engine2d.model.PositionedVAO;
import de.levin.engine2d.model.Vao;
import de.levin.engine2d.toolbox.Data;
import de.levin.engine2d.toolbox.DisplayManager;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by levin on 21.06.2017.
 */
public class MainExample implements Runnable{

    //Tools

    //Render
    PositionedVAO vao;
    ModularShader red;
    Vao v;


    @Override
    public void run() {
        DisplayManager.createDisplay();
        init();
        while (!Display.isCloseRequested()){
            update();
            render();
            DisplayManager.updateDisplay();
        }
        cleanUp();
        DisplayManager.closeDisplay();
    }

    public void init(){
        v = new Vao(Data.positions, Data.textureCoords, Data.indices);
        vao = new PositionedVAO(v, new Vector2f(0,0));
        vao = PositionedVAO.createQuad(1,1);
        red = ModularShader.createBlackShader();
    }

    public void update(){

    }

    public void render(){
        DisplayManager.setUpRendering2D();
        red.enable();

        vao.draw(red);
        vao.unbind();

        red.disable();
        DisplayManager.endRendering2D();
    }

    public void cleanUp(){
        vao.cleanUp();
        red.cleanUp();
    }


    public static void main(String[] args){
        new MainExample().run();
    }
}
