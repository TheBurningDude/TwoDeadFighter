/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.collision;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;

/**
 *
 * @author Sidon K. K
 */
public class CollisionManager implements IEntityProcessingService{

    float x;
    float y;
    EntityBody eb;
    DataCollision dc;
    
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        for(Entity entity_m : world.values()){
            x = entity_m.getX();
            y = entity_m.getY();
            eb = entity_m.getEntityBody();
            
            //check here if they arer empty
            
            for(Entity entity_s : world.values()){
                
            
            
            }
        }
        
    }
    
    
    
}
