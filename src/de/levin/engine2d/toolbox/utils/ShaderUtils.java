package de.levin.engine2d.toolbox.utils;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by levin on 21.06.2017.
 */
public class ShaderUtils {

    private static Map<Integer, int[]> shaderCache = new HashMap<>();

    public static void cleanUp(){
        GL20.glUseProgram(0);
        for(Integer i : shaderCache.keySet()){
            GL20.glDetachShader(i, shaderCache.get(i)[0]);
            GL20.glDetachShader(i, shaderCache.get(i)[1]);
            GL20.glDeleteShader(shaderCache.get(i)[0]);
            GL20.glDeleteShader(shaderCache.get(i)[1]);
            GL20.glDeleteProgram(i);
        }
    }

    public static int loadShader(String vertPath, String fragPath){
        String vert = loadAsString(vertPath, false);
        String frag = loadAsString(fragPath, false);
        return createShader(vert,frag);
    }

    public static int loadInsideJAR(String vertPath, String fragPath){
        String vert = loadAsString(vertPath, true);
        String frag = loadAsString(fragPath, true);
        return createShader(vert,frag);
    }

    private static int createShader(String vert, String frag){
        int fragID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        int vertID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        doShader(vertID, vert);
        doShader(fragID, frag);

        int programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertID);
        GL20.glAttachShader(programID, fragID);
        GL20.glBindAttribLocation(programID, 0, "position");
        GL20.glBindAttribLocation(programID, 1, "textureCoordinate");
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);


        int[] shaders = new int[2];
        shaders[0] = vertID;
        shaders[1] = fragID;
        shaderCache.put(programID, shaders);
        return programID;
    }

    private static void doShader(int shaderID, String source){
        GL20.glShaderSource(shaderID, source);
        GL20.glCompileShader(shaderID);
        if( GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
    }

    public static String loadAsString(String file, boolean byClass) {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            if(byClass){
                InputStream in = Class.class.getResourceAsStream(file);
                reader = new BufferedReader(new InputStreamReader(in));
            }
            String buffer = "";
            while ((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Konnte den Shader nicht finden");
            e.printStackTrace();
        }
        return result.toString();
    }

}
