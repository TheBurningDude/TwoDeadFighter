/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.hud;


import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider (service = IGamePluginService.class)
public class HUDEntity implements IGamePluginService{
    private Map<String, Entity> world;
    private Entity e;
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        e = new Entity();
        e.setType(EntityType.HEALTH);
        world.put(e.getID(), e);
    }

    @Override
    public void stop(GameData gamedata) {
        world.remove(e.getID());
    }

    
    
}
