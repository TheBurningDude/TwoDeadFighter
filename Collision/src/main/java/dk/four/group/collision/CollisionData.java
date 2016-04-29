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
public class CollisionData {
    
    private int collisionTag = CollisionTags.NONE ;
    public float x;
    public float y;
    public float centerX;
    public float centerY;
    public int height;
    public int width;
    public CollisionShape collisionShape;

    public int getCollisionTag()
    {
        return collisionTag;
    }

    public void setCollisionFlag(int collisionTag)
    {
        this.collisionTag = collisionTag;
    }
    
     public CollisionData(Entity entity){
        x = entity.getX();
        y = entity.getY();
        EntityBody eb = entity.getEntityBody();
        height = eb.getHeight();
        width = eb.getWidth();
        collisionShape = eb.getCollisionShape();
        centerX = x + (width / 2);
        centerY = y + (height / 2);;
    
    }
     
       private static boolean isMoving(int tag){
           //returns true is and onlly if the tag and collisiontag.movinng are the same
           return (tag & CollisionTags.MOVING) == CollisionTags.MOVING;
    }
       private static boolean isSolid(int tag){
           return (tag & CollisionTags.SOLID) == CollisionTags.SOLID;
    }
       
       private static boolean canWeCollide(int e1, int e2)
    {
        boolean collision1 = isMoving(e1);
        boolean collision2 = isMoving(e2);
        // true && false - Evaluates false because the second is false
        // false && true - Evaluates false because the first is false
        // true && true - Evaluates true because both are true
        // false && false - Evaluates false because both are false      
        return (collision1 && collision2) || (collision1 && isSolid(e2)) || (collision2 && isSolid(e1));
    }
       
       
    
}
