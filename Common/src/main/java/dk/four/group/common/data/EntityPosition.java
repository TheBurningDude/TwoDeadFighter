/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.common.data;

/**
 *
 * @author Sidon K. K
 */
public class EntityPosition {
    private float x;
    private float y;
    
    public EntityPosition(float x,float y){
        this.x = x;
        this.y = y;
    }
        
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
