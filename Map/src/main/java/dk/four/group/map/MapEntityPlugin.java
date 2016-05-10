/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.map;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IGamePluginService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider(service = IGamePluginService.class)
public class MapEntityPlugin implements IGamePluginService {
    
    private static final String GRASS_PATH = "../../../Map/src/main/java/dk/four/group/map/data/tile.png";
    private Map<String, Entity> world;
    private List<Entity> map = new ArrayList<>();

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        ResourceManager.createAssest(GRASS_PATH);
        this.world = world;
        // Add entities to the world
        map = createMap();
        for (Entity ob : map){
        world.put(ob.getID(), ob);
        }
        //world.put(map.getID(), map);
        
    }
    
    private List <Entity> createMap() {
        List <Entity> e = new ArrayList<>();
        Entity mapEntity1 = new Entity();
        mapEntity1.setAsset(ResourceManager.getAsset(GRASS_PATH));
        mapEntity1.setType(EntityType.MAP);
        mapEntity1.setEntityPosition(192 , 192 );
       
        mapEntity1.setSize(128);
        //mapEntity1.setRect(new Rectangle(mapEntity1.getX(), mapEntity1.getY(), mapEntity1.getSize(), mapEntity1.getSize()));
        //mapEntity1.setRadius(128);
        mapEntity1.setEntityBody(new EntityBody(128, 128, EntityBody.CollisionShape.RECTANGLE));
        
        //mapEntity1.setAsset(ResourceManager.getAsset(GRASS_PATH));
        
//        Entity mapEntity2 = new Entity();
//        
//        mapEntity2.setType(EntityType.MAP);
//        mapEntity2.setEntityPosition(800-228 , 640-228 );
//       
//        mapEntity2.setSize(128);
//        //mapEntity2.setRect(new Rectangle(mapEntity2.getX(), mapEntity2.getY(), mapEntity2.getSize(), mapEntity2.getSize()));
//        //mapEntity2.setRadius(128);
//        mapEntity2.setEntityBody(new EntityBody(128, 128, EntityBody.CollisionShape.RECTANGLE));
//       
//        mapEntity2.setAsset(ResourceManager.getAsset(GRASS_PATH));
//        
//        Entity mapEntity3 = new Entity();
//        
//        mapEntity3.setType(EntityType.MAP);
//        mapEntity3.setEntityPosition(100 , 640-228 );
//        
//        mapEntity3.setSize(128);
//        //mapEntity3.setRect(new Rectangle(mapEntity3.getX(), mapEntity3.getY(), mapEntity3.getSize(), mapEntity3.getSize()));
//        //mapEntity3.setRadius(128);
//       mapEntity3.setEntityBody(new EntityBody(128, 128, EntityBody.CollisionShape.RECTANGLE));
//       
//        mapEntity3.setAsset(ResourceManager.getAsset(GRASS_PATH));
//        
//        Entity mapEntity4 = new Entity();
//        
//        mapEntity4.setType(EntityType.MAP);
//        mapEntity4.setEntityPosition(800-228 , 100 );
//       
//        mapEntity4.setSize(128);
//        //mapEntity4.setRect(new Rectangle(mapEntity4.getX(), mapEntity4.getY(), mapEntity4.getSize(), mapEntity4.getSize()));
//        //mapEntity4.setRadius(128);
//        mapEntity4.setEntityBody(new EntityBody(128, 128, EntityBody.CollisionShape.RECTANGLE));
//       
//        mapEntity4.setAsset(ResourceManager.getAsset(GRASS_PATH));
        
        e.add(mapEntity1);
//        e.add(mapEntity2);
//        e.add(mapEntity3);
//        e.add(mapEntity4);
        
        return e;
    }
    
    @Override
    public void stop(GameData gamedata) {
        for(Entity e : map){
            world.remove(e.getID());
        }
    }
    
}
