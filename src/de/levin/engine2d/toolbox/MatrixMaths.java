package de.levin.engine2d.toolbox;

import de.levin.engine2d.toolbox.utils.BufferUtils;
import de.levin.engine2d.toolbox.utils.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.FloatBuffer;

/**
 * Created by levin on 10.06.2017.
 */
public class MatrixMaths {

    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale, float rotation){
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(translation, matrix, matrix);
        Matrix4f.scale(new Vector3f(scale.x, scale.y, 1f), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rotation), new Vector3f(0,0,1), matrix, matrix);
        return matrix;
    }

    public static Matrix4f convert(Matrix m){
        Matrix4f out = new Matrix4f();
        out.load(BufferUtils.createFloatBuffer(m.elements));
        return out;
    }

    public static Matrix4f identity(){
        return convert(Matrix.identity());
    }

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far){
        return convert(Matrix.orthographic(left, right, bottom, top, near, far));
    }

    public static Matrix4f translate(Vector3f vector){
        return convert(Matrix.translate(vector));
    }

    public static Matrix4f rotate(float angle){
        return convert(Matrix.rotate(angle));
    }

    public static float getBetrag(float f){
        if (f < 0){
            return -f;
        }
        return f;
    }

    //*****************Matrices für Buttons********************************

    //Ist meistens null:
    private static float[] matrixBuffer,vectorBuffer;
    public static float[] multiplyM4fWithV4f(float[] matrix4f, float[] vector4f){
        matrixBuffer = matrix4f;
        vectorBuffer = vector4f;

        float[] out = new float[4];
        for (int i = 0; i<out.length;i++){
            out[i] = doRow(i);
        }

        matrixBuffer = null;
        vectorBuffer = null;
        return out;
    }

    //Gehört zur Methode multiply.....
    private static float doRow(int row){
        return (doSingle(row, 0) + doSingle(row, 1) +
                doSingle(row, 2) + doSingle(row, 3));
    }

    private static float doSingle(int row, int column){
        return matrixBuffer[row * 4 + column] * vectorBuffer[column];
    }

    public static Matrix4f arrayToMatrix4f(float[] array){
        FloatBuffer buffer = BufferUtils.createFloatBuffer(array);
        Matrix4f out = new Matrix4f();
        out.load(buffer);
        return out;
    }


    public static float[] matrix4fToArray(Matrix4f matrix4f){
        float[] array = new float[4*4];

        array [0 *4 + 0] = matrix4f.m00;
        array [0 *4 + 1] = matrix4f.m01;
        array [0 *4 + 2] = matrix4f.m02;
        array [0 *4 + 3] = matrix4f.m03;

        array [1 *4 + 0] = matrix4f.m10;
        array [1 *4 + 1] = matrix4f.m11;
        array [1 *4 + 2] = matrix4f.m12;
        array [1 *4 + 3] = matrix4f.m13;

        array [2 *4 + 0] = matrix4f.m20;
        array [2 *4 + 1] = matrix4f.m21;
        array [2 *4 + 2] = matrix4f.m22;
        array [2 *4 + 3] = matrix4f.m23;

        array [3 *4 + 0] = matrix4f.m30;
        array [3 *4 + 1] = matrix4f.m31;
        array [3 *4 + 2] = matrix4f.m32;
        array [3 *4 + 3] = matrix4f.m33;

        return array;
    }

    public static float[] vector4fToArray(Vector4f vector4f){
        float[] array = new float[4];

        array[0] = vector4f.x;
        array[1] = vector4f.y;
        array[2] = vector4f.z;
        array[3] = vector4f.w;

        return array;
    }
}
