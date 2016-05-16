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
    
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        for(Entity e1 : world.values()){
            for(Entity e2 : world.values())
            if(e1.getType().equals(EntityType.PLAYER) && e2.getType().equals(EntityType.WEAPON)){
                        float playerCenterX = e1.getEntityPosition().getX();
                        float playerCenterY = e1.getEntityPosition().getY();

                        
                        e2.setEntityPosition(playerCenterX , playerCenterY);
                        //e1.setRadians(i++);
                        e2.setRadians(e1.getRadians());

                canShoot = (System.currentTimeMillis() - lastPress) > 500;

                
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
                        
                        float x = e2.getEntityPosition().getX() + e2.getEntityBody().getWidth()/2;
                        float y = e2.getEntityPosition().getY() + e2.getEntityBody().getWidth()/2 ;
                        
                        float rad = (float) Math.toRadians(e1.getRadians());
                      
                        float tipx = (float) (x + (35 * Math.cos(rad)) - (-20 * Math.sin(rad)));
                        float tipy = (float) (y + (35 * Math.sin(rad)) + (-20 * Math.cos(rad)));
  
                        bullet.setEntityPosition(tipx,tipy);
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
                        destroyOnLeaveScreen(gameData, world, entity);
                        
                        
        
        }
        
    }
    
     private void destroyOnLeaveScreen(GameData gameData, Map<String, Entity> world, Entity e) {
        float x = e.getEntityPosition().getX();
        float y = e.getEntityPosition().getY();
        float dx = e.getDx();
        float dy = e.getDy();
        float dt = gameData.getDelta();
        // Screen wrap
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            world.remove(e.getID());
        } else if (x < 0) {
            world.remove(e.getID());
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            world.remove(e.getID());
        } else if (y < 0) {
            world.remove(e.getID());
        }
    }
    
     
}
