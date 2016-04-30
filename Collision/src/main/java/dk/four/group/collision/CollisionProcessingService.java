/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.collision;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityPosition;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Collection;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class CollisionProcessingService implements IEntityProcessingService{

   @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        Collection<Entity> entities = world.values();
        
        //System.out.println("dk.four.group.collision.CollisionProcessingService.process()");
        for (Entity handled : entities)
        {
           
            for (Entity ent : entities)
            {
                if(isOverLappingCircleRect(handled, ent)){
                    
                    if(handled.getType() == EntityType.PLAYER){
                        
                        if(ent.getType() == EntityType.MAP){
                            //System.out.println("dk.four.group.collision.CollisionProcessingService.process()");
                            EntityPosition first = collisionCirRect(handled, ent);
                            EntityPosition handl = handled.getEntityPosition();
                            handl.setX(first.getX());
                            handl.setY(first.getY());
                        
                        }else if (ent.getType() == EntityType.ENEMY){
                        
                       
                        }
                    
                    }else if (handled.getType() == EntityType.MAP){
                        if(ent.getType() == EntityType.PLAYER){
                            //System.out.println("dk.four.group.collision.CollisionProcessingService.process()");
                            EntityPosition first = collisionCirRect(ent, handled);
                            EntityPosition handl = ent.getEntityPosition();
                            handl.setX(first.getX());
                            handl.setY(first.getY());
                        
                        }else if (ent.getType() == EntityType.ENEMY){
                        
                        
                        }
                    
                    
                    }
                }
               
            }
        }
        
    }
    
    private boolean isOverLappingCircleRect( Entity e1, Entity e2){
        
        
        float circleDistanceX = Math.abs((e2.getEntityPosition().getX() + (e2.getSize() /2)) -(e1.getEntityPosition().getX() + (e1.getSize()/2)) );
        float circleDistanceY = Math.abs((e2.getEntityPosition().getY() + e2.getSize() /2) -(e1.getEntityPosition().getY() + (e1.getSize() /2)) );
        
        if (circleDistanceY >= (e2.getSize() / 2 + e1.getSize()/2))
        {
            //System.out.println("dk.four.group.collision.CollisionProcessingService.isOverLappingCircleRect()");
            return false;
        }
        if (circleDistanceX >= (e2.getSize()/ 2 + e1.getSize()/2))
        {
            //System.out.println("dk.four.group.collision.CollisionProcessingService.isOverLappingCircleRect()");
            return false;
        }
        if (circleDistanceY < (e2.getSize() / 2))
        {
            
            return true;
        }
        if (circleDistanceX < (e2.getSize() / 2))
        {
            //System.out.println("dk.four.group.collision.CollisionProcessingService.isOverLappingCircleRect()");
            return true;
        }
        float cornerDistanceSq = (float) Math.sqrt(
                Math.pow((circleDistanceX - (e2.getSize() / 2)), 2) + Math.pow((circleDistanceY - (e2.getSize() / 2)), 2));

        return (cornerDistanceSq < e1.getRadius());
        
    }
    
    private EntityPosition collisionCirRect(Entity e1, Entity e2){
        
        float e1CenterX = e1.getEntityPosition().getX() + (e1.getSize() /2);
        float e1CenterY = e1.getEntityPosition().getY() + (e1.getSize() /2);
        
        float e2CenterX = e2.getEntityPosition().getX() + (e1.getSize() /2);
        float e2CenterY = e2.getEntityPosition().getY() + (e1.getSize() /2);
        float cornerX = e2.getEntityPosition().getX();
        float cornerY = e2.getEntityPosition().getY();
        
        if(e1CenterX > e2.getEntityPosition().getX() && e1CenterX < e2.getEntityPosition().getX() + e2.getSize()){
            
            if (e1CenterY < e2CenterY) 
            {
                return new EntityPosition(e1.getEntityPosition().getX(), e2.getEntityPosition().getY() - e1.getSize());
            }
            else 
            {
                return new EntityPosition(e1.getEntityPosition().getX(), e2.getEntityPosition().getY() + e2.getSize());
            }
        
        }
        else if (e1CenterY > e2.getEntityPosition().getY() && e1CenterY < e2.getEntityPosition().getY() + e2.getSize())
        {
            if (e1CenterX < e2CenterX) 
            {
                return new EntityPosition(e2.getEntityPosition().getX() - e1.getSize(), e1.getEntityPosition().getY());
            }
            else 
            {
                return new EntityPosition(e2.getEntityPosition().getX() + e2.getSize(), e1.getEntityPosition().getY());
            }
        }
        
        if (e1CenterX > e2.getEntityPosition().getX()) 
        {
            cornerX += e2.getSize(); 
            if (e1CenterY > e2.getEntityPosition().getY()) 
            {
                cornerY += e2.getSize();  
            }

        }
        else if (e1CenterX < e2.getEntityPosition().getX()) 
        {
            if (e1CenterY > e2.getEntityPosition().getY()) 
            {
                cornerY += e2.getSize(); // We know the corner is on the buttom.
            }
        }
        
        EntityPosition distt = new EntityPosition(e1CenterX - cornerX, e1CenterY - cornerY);
        
        return new EntityPosition(cornerX + distt.getX() - (e2.getSize() /2) , cornerY + distt.getY() - (e2.getSize() / 2));
        
    
    }
    
}
