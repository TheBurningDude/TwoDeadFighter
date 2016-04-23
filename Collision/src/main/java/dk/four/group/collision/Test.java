/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.collision;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class Test implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        //System.out.println(gameData);
        //System.out.println(world.size());
        //System.out.println(entity);
        for (Map.Entry<String, Entity> entry : world.entrySet()) {
            String key = entry.getKey();
            Entity value = entry.getValue();
            
            if(entity.equals(value)){
                continue;
            }
            float radius = value.getRadius();
            float xUpperLimit = value.getX() + radius;
            float xLowerLimit = value.getX() - radius;
            float yUppwerLimit = value.getY() + radius;
            float yLowerLimit = value.getY() - radius;
            
            System.out.println(xLowerLimit + "," + xUpperLimit + "," + yLowerLimit + "," + yUppwerLimit );
            
            float entityX = entity.getX();
            float entityY = entity.getY();
            if(entityX > xLowerLimit && entityX < xUpperLimit && entityY > yLowerLimit && entityY < xUpperLimit){
                System.out.println("COL");
            }
            
        }
    }
    
}
