package de.levin.engine2d.hitbox;

/**
 * Created by levin on 27.06.2017.
 */
public class Collision {

    public static boolean collideSquares(SquareHitbox a, SquareHitbox b){
        float aX = a.getA().x;
        float hX = b.getD().x;
        float eX = b.getA().x;
        float dX = a.getD().x;

        float eY = b.getA().y;
        float bY = a.getB().y;
        float fY = b.getB().y;
        float aY = a.getA().y;

        if(hX > aX && eX < dX){
            if(eY > bY && fY < aY){
                return true;
            }
        }
        return false;
    }

}
