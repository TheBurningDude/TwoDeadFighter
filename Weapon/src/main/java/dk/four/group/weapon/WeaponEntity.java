/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.weapon;

import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.WEAPON;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider (service = IGamePluginService.class)
public class WeaponEntity implements IGamePluginService{
    
    private Map<String, Entity> world;
    private Entity weapon;

    public WeaponEntity() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        // Add entities to the world
        weapon = createWeapon(gameData);
        world.put(weapon.getID(), weapon);
    }
    
    private Entity createWeapon(GameData gameData) {
        Entity weaponEnt = new Entity();
        weaponEnt.setType(WEAPON);

        weaponEnt.setShapeX(new float[4]);
        weaponEnt.setShapeY(new float[4]);
        
        return weaponEnt;
    }

    @Override
    public void stop(GameData gamedata) {
        // Remove entities
        world.remove(weapon.getID());
    }

    
}
