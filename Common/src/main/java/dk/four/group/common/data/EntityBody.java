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
public class EntityBody {
    
    private int height;
    private int width;
    private CollisionShape collisionShape; 
       
    
    public EntityBody(int height, int width, CollisionShape collisionShape)
    {
        this.height = height;
        this.width = width;
        this.collisionShape = collisionShape;
    }
    public enum CollisionShape
    {
        RECTANGLE, CIRCLE
    }
      public CollisionShape getCollisionShape()
    {
        return collisionShape;
    }

    public void setGeometry(CollisionShape collisionShape)
    {
        this.collisionShape = collisionShape;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
}
