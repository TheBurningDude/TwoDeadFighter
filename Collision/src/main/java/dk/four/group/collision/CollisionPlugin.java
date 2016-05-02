/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.collision;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IEntityProcessingService;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider(service = IGamePluginService.class)
public class CollisionPlugin implements IGamePluginService{

    
    private IEntityProcessingService processor;
    private Map<String, Entity> world;
    
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        
        this.world = world;
        this.processor = new CollisionProcessingService();
         
    }

    @Override
    public void stop(GameData gameData) {
        
    }
    
    
    
}
