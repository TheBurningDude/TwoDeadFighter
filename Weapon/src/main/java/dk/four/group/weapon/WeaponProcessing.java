/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.weapon;


import dk.four.group.common.data.Entity;
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
    boolean canShoot = true;
    private static final String FILE_PATH = "../../../Weapon/src/main/java/dk/four/group/weapon/data/bulletBeige.png";
    //private static final String FILE_PATH2 = "../../../Weapon/src/main/java/dk/four/group/weapon/data/Wep_1.png";
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        for(Entity e1 : world.values()){
            for(Entity e2 : world.values())
            if(e1.getType().equals(EntityType.PLAYER) && e2.getType().equals(EntityType.WEAPON)){
                        e2.setEntityPosition(e1.getEntityPosition().getX(), e1.getEntityPosition().getY());
                        //e1.setRadians(i++);
                        e2.setRadians(e1.getRadians());
                        
                canShoot = (System.currentTimeMillis() - lastPress) > 100;
                
                if(gameData.getKeys().isDown(GameKeys.SPACE)){
                    //System.out.println("dk.four.group.weapon.WeaponProcessing.process()");
                    if(canShoot){
                        ResourceManager.createAssest(FILE_PATH);
                        Entity bullet = new Entity();      
                        bullet.setDx((float) Math.cos(Math.toRadians(e2.getRadians())));
                        bullet.setDy((float) Math.sin(Math.toRadians(e2.getRadians())));
                        bullet.setAsset(ResourceManager.getAsset(FILE_PATH));
                        bullet.setRadians(e1.getRadians());
                        bullet.setSize(12);
                        bullet.setType(EntityType.BULLET);
                        
                        float x = e1.getEntityPosition().getX();
                        float y = e1.getEntityPosition().getY();
                        float rad = (float) Math.toRadians(e1.getRadians());
                      
                        float tipx = (float) (x + 63 * Math.cos(rad) - 0 * Math.sin(rad));
                        float tipy = (float) (y + 63 * Math.sin(rad) + 0 * Math.cos(rad));
  
                        bullet.setEntityPosition(tipx, tipy);
                        //bullet.setEntityBody(new EntityBody(12, 12, EntityBody.CollisionShape.RECTANGLE));
                        bullet.setMaxSpeed(1);
                        world.put(bullet.getID(), bullet);
                    
                    lastPress = System.currentTimeMillis();
                    }
                }
            }
        
        
        }
         if(entity.getType().equals(EntityType.BULLET)){
             
                        float x = entity.getEntityPosition().getX() + (entity.getDx() * entity.getSize()) * (20 * gameData.getDelta());
                        float y = entity.getEntityPosition().getY() + (entity.getDy() * entity.getSize()) * ( 20 * gameData.getDelta());
                        //x = x + entity.getDx() * (200 * gameData.getDelta());
                        //y = y + entity.getDy() * ( 200 * gameData.getDelta());         
                        entity.getEntityPosition().setX(x);
                        entity.getEntityPosition().setY(y);
        
        }
        
    }
    
    
    private void shoot(Entity e){
    
    
    }
   
}
