package de.levin.engine2d.toolbox;

import de.levin.engine2d.toolbox.utils.BufferUtils;
import de.levin.engine2d.toolbox.utils.Matrix;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

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
}
