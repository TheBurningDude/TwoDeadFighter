/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.map;

import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.MAP;
import dk.four.group.common.data.GameData;
import dk.four.group.common.events.Event;
import dk.four.group.common.events.EventType;
import dk.four.group.common.services.IEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class Map implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, java.util.Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float dt = gameData.getDelta();
        float dx = entity.getDx();
        float dy = entity.getDx();

//        float acceleration = entity.getAcceleration();
//        float deceleration = entity.getDeacceleration();
//        float maxSpeed = entity.getMaxSpeed();
        float radians = entity.getRadians();
//        float rotationSpeed = entity.getRotationSpeed();

        if (entity.getType().equals(MAP)) {
            // set position
            x += dx * dt;
            if(x > gameData.getDisplayWidth()){
                x = gameData.getDisplayWidth();
            }else if(x < 0){
                x = 0;
            }
            
            y += dy * dt;
            if(y > gameData.getDisplayHeight()){
                y = gameData.getDisplayHeight();
            }else if(y < 0){
                y = 0;
            }
            
            // Update entity
            entity.setPosition(x, y);
            entity.setDx(dx);
            entity.setDy(dy);
            entity.setRadians(radians);

            updateShape(entity);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1415f/4) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3 * 3.1415f/2) * 8);
        shapey[2] = (float) (y + Math.sin(radians) * 8);

        shapex[3] = (float) (x + Math.cos(radians) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 3 * 3.1415f/2) * 8);
        
        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
   
    
}
