/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.MAP;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity map;
    private TiledMap tiledMap;
    
    
    public EntityPlugin() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
       
        this.world = world;
        // Add entities to the world
        map = createMap(gameData);
        world.put(map.getID(), map);
        TmxMapLoader loader = new TmxMapLoader();
        tiledMap = loader.load("assets/testtiles.tmx");
     
        
    }

    private Entity createMap(GameData gameData) {
        Entity tiledMap = new Entity();
        tiledMap.setType(MAP);

        tiledMap.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        //playerShip.setSprite(new Texture(this.getClass().getClassLoader().getResource("assets/playertext.png").toExternalForm()));

        return tiledMap;
    }

    @Override
    public void stop(GameData gameData) {
        // Remove entities
        world.remove(map.getID());
        
        
    }
}
