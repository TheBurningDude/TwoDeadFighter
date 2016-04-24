/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.map;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;

/**
 *
 * @author nasib
 */
public class MapEntityPlugin implements IGamePluginService {
    private static final String GRASS_PATH = "../../../Map/src/main/java/dk/four/group/map/data/grass.png";
    private Map<String, Entity> world;
    private Entity map;

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        ResourceManager.createAssest(GRASS_PATH);
        this.world = world;
        // Add entities to the world
        map = createMap(gameData);
        world.put(map.getID(), map);
        
    }

    private Entity createMap(GameData gameData) {
        Entity mapEntity = new Entity();
        mapEntity.setType(EntityType.MAP);
        
        mapEntity.setAsset(ResourceManager.getAsset(GRASS_PATH));
        
        return mapEntity;
    }
    
    @Override
    public void stop(GameData gameData) {
        // Remove entity
        world.remove(map.getID());
    }

    
}
