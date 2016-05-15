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
        /*
        *creating a collection of entities that a in world
        */
        Collection<Entity> entities = world.values();
        
        for (Entity handled : entities)
        { 
            for (Entity ent : entities)
            {
                /*
                * inner for loop for comparing different entities with each other
                */
                if(isOverLappingCircleRect(handled, ent)){

                    
                    if(null != handled.getType()) /*
                     *if stat checking if a circle collider is overlapping a rectangle collider
                     */ switch (handled.getType()) {
                        case PLAYER:
                            if(ent.getType() == EntityType.MAP){
                                /*
                                *handling collision for player with map
                                */
                                
                                EntityPosition first = collisionCirRect(handled, ent);
                                EntityPosition handl = handled.getEntityPosition();
                                handl.setX(first.getX());
                                handl.setY(first.getY());
                                
                            }else if (ent.getType() == EntityType.ENEMY){
                                
                                
                                //TO-DO
                                
                                //System.out.println("dk.four.group.collision.CollisionProcessingService.process()");
                                EntityPosition first = collisionCirRect(handled, ent);
                                EntityPosition handl = handled.getEntityPosition();
                                handl.setX(first.getX());
                                handl.setY(first.getY());
                                
                                
                            }   break;
                        case MAP:
                            if(ent.getType() == EntityType.PLAYER){
                                /*
                                *handling collision for map with player
                                */
                                EntityPosition first = collisionCirRect(ent, handled);
                                EntityPosition handl = ent.getEntityPosition();
                                handl.setX(first.getX());
                                handl.setY(first.getY());
                                
                            }else if (ent.getType() == EntityType.ENEMY){
                                
                                EntityPosition first = collisionCirRect(ent, handled);
                                EntityPosition handl = ent.getEntityPosition();
                                handl.setX(first.getX());
                                handl.setY(first.getY());
                            }   break;
                        case ENEMY:
                            if(ent.getType() == EntityType.MAP){
                                /*
                                *handling collision for map with player
                                */
                                //System.out.println("dk.four.group.collision.CollisionProcessingService.process()");
                                EntityPosition first = collisionCirRect(handled, ent);
                                EntityPosition handl = handled.getEntityPosition();
                                handl.setX(first.getX());
                                handl.setY(first.getY());
                                
                            }else if (ent.getType() == EntityType.PLAYER){
                                
                                EntityPosition first = collisionCirRect(ent, handled);
                                EntityPosition handl = ent.getEntityPosition();
                                handl.setX(first.getX());
                                handl.setY(first.getY());
                            }   break;
                        default:
                            break;
                    }
                }
               
            }
        }
        
    }
    //needs to be refactored
    private boolean isOverLappingCircleRect( Entity e1, Entity e2){
        
       //http://www.java-gaming.org/index.php?topic=31989.0
        
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
    //needs to be refactored
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
                //System.out.println(" circle y < rect y");
                return new EntityPosition(e1.getEntityPosition().getX(), e2.getEntityPosition().getY() - e1.getSize());
            }
            else 
            {
                //System.out.println(" circle y > rect y");
                return new EntityPosition(e1.getEntityPosition().getX(), e2.getEntityPosition().getY() + e2.getSize());
            }
        
        }
        else if (e1CenterY > e2.getEntityPosition().getY() && e1CenterY < e2.getEntityPosition().getY() + e2.getSize())
        {
            if (e1CenterX < e2CenterX) 
            {
                //System.out.println(" circle y > rect y");
                return new EntityPosition(e2.getEntityPosition().getX() - e1.getSize(), e1.getEntityPosition().getY());
            }
            else 
            {
                //System.out.println(" circle y > rect y");
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
                cornerY += e2.getSize(); 
            }
        }
        
        EntityPosition distt = new EntityPosition(e1CenterX - cornerX, e1CenterY - cornerY);
        float nx = distt.getX();
        float ny = distt.getX();
        double gm = (Math.pow(distt.getX(),2) + Math.pow(distt.getY(),2));
        if(!(distt.getX() == 0 && distt.getY() == 0)){
             nx  /= gm;
             ny /= gm;
        }
        
        nx *= e2.getSize()/2;
        ny *= e2.getSize()/2;
        
        distt.setX(nx);
        distt.setY(ny);
        
        return new EntityPosition(cornerX + distt.getX() - 32 , cornerY + distt.getY() - 32);
        
    
    }
    
}
