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
import dk.four.group.common.data.GameKeys;
import dk.four.group.common.data.ResourceManager;

import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class WeaponProcessing implements IEntityProcessingService{
    
    long lastPress = 0;
    boolean green = true;

    private static final String FILE_PATH = "../../../Weapon/src/main/java/dk/four/group/weapon/data/bulletBeige.png";
    private static final String FILE_PATH2 = "../../../Weapon/src/main/java/dk/four/group/weapon/data/Wep_1.png";
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        for(Entity e1 : world.values()){
            for(Entity e2 : world.values())
            if(e1.getType().equals(EntityType.PLAYER) && e2.getType().equals(EntityType.WEAPON)){
                ResourceManager.createAssest(FILE_PATH2);
                e1.setAsset(ResourceManager.getAsset(FILE_PATH2));
                e2.setEntityPosition(e1.getEntityPosition().getX(), e1.getEntityPosition().getY());
                e2.setRadians(e1.getRadians());
                green = (System.currentTimeMillis() - lastPress) > 1500;
                
                if(gameData.getKeys().isDown(GameKeys.SPACE)){
                    //System.out.println("dk.four.group.weapon.WeaponProcessing.process()");
                    if(green){
                        ResourceManager.createAssest(FILE_PATH);
                        Entity bullet = new Entity();
                        bullet.setEntityPosition(e2.getEntityPosition().getX(), e2.getEntityPosition().getY());
                        bullet.setDx((float) Math.cos(Math.toRadians(e1.getRadians())));
                        bullet.setDy((float) Math.sin(Math.toRadians(e1.getRadians())));
                        bullet.setAsset(ResourceManager.getAsset(FILE_PATH));
                        bullet.setRadians(e2.getRadians());
                        bullet.setSize(12);
                        bullet.setType(EntityType.BULLET);
                        //bullet.setEntityBody(new EntityBody(12, 12, EntityBody.CollisionShape.RECTANGLE));
                        bullet.setMaxSpeed(1);
                        world.put(bullet.getID(), bullet);
                    
                    lastPress = System.currentTimeMillis();
                    }
                }
            }
        
        
        }
        
    }
   
}
