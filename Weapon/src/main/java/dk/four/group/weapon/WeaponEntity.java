/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.weapon;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider (service = IGamePluginService.class)
public class WeaponEntity implements IGamePluginService{
    
    private static final String FILE_PATH = "../../../Weapon/src/main/java/dk/four/group/weapon/data/Wep1_1.png";
    private Map<String, Entity> world;
    private Entity weapon;

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        ResourceManager.createAssest(FILE_PATH);
        this.world = world;
        // Add entities to the world
        weapon = createWeapon();
        world.put(weapon.getID(), weapon);
    }
    
    private Entity createWeapon() {
        
        Entity weaponEnt = new Entity();
        weaponEnt.setType(EntityType.WEAPON);
        weaponEnt.setAsset(ResourceManager.getAsset(FILE_PATH));
        weaponEnt.setEntityPosition(0, 0);
        weaponEnt.setRadians(0);
        weaponEnt.setEntityBody(new EntityBody( 32   , 14, EntityBody.CollisionShape.RECTANGLE));
        
        return weaponEnt;
    }

    @Override
    public void stop(GameData gamedata) {
        // Remove entities
        world.remove(weapon.getID());
    }

    
}
