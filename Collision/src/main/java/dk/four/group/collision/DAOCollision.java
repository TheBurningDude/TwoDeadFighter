/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.collision;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import dk.four.group.common.data.EntityBody.CollisionShape;

/**
 *
 * @author Sidon K. K
 */
public class DAOCollision {
    
    public float x;
    public float y;
    public float centerX;
    public float centerY;
    public int height;
    public int width;
    public CollisionShape collisionShape;
    
    public DAOCollision(Entity entity){
        x = entity.getX();
        y = entity.getY();
        EntityBody eb = entity.getEntityBody();
        height = eb.getHeight();
        width = eb.getWidth();
        collisionShape = eb.getCollisionShape();
        centerX = x + (width / 2);
        centerY = y + (height / 2);;
    
    }
    
}
