package de.levin.engine2d.Shader;


import de.levin.engine2d.toolbox.DisplayManager;
import de.levin.engine2d.toolbox.utils.ShaderUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by levin on 19.06.2017.
 */
public class ModularShader {

    private boolean enabled = false;

    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<>();
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    private static String basicPath = "res/basic/shaders/";

    public ModularShader(String vert, String frag){
        ID = ShaderUtils.loadShader(vert,frag);
        setUniformMat4("pr_matrix", DisplayManager.getPr_matrix());
    }

    private ModularShader(int id){
        ID = id;
    }

    public static ModularShader createColorShader(){
        int id = ShaderUtils.loadShader(basicPath+"simple.vert", basicPath+"simple.frag");
        return new ModularShader(id);
    }



    public static ModularShader createPassThrough(){
        int id = ShaderUtils.loadShader(basicPath+"pass.vert", basicPath+"pass.frag");
        return new ModularShader(id);
    }

    public void cleanUp(){
        ShaderUtils.cleanUp();
    }

    public int getUniform(String name){
        if(locationCache.containsKey(name)){
            return locationCache.get(name);
        }
        int result = GL20.glGetUniformLocation(ID, name);
        if(result == -1){
            System.err.println("Could not find uniform " + name);
        }else {
            locationCache.put(name, result);
        }
        return result;
    }

    private void loadMatrix(int location, Matrix4f matrix){
        matrix.store(matrixBuffer);
        matrixBuffer.flip();
        GL20.glUniformMatrix4(location, false, matrixBuffer);
    }

    private void loadVector4f(int location, Vector4f vector){
        GL20.glUniform4f(location, vector.x, vector.y, vector.z, vector.w);
    }

    public void setUniformVector4f(String name, Vector4f vector){
        if (!enabled) enable();
        loadVector4f(getUniform(name), vector);
    }

    public void setUniformMat4(String name, Matrix4f matrix){
        if (!enabled) enable();
        loadMatrix(getUniform(name), matrix);
    }


    public void enable(){
        GL20.glUseProgram(ID);
        enabled = true;
    }

    public void disable(){
        GL20.glUseProgram(0);
        enabled = false;
    }

}
