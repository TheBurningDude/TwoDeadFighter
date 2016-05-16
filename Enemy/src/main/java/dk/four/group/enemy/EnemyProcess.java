/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.enemy;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import static dk.four.group.common.data.EntityType.ENEMY;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.GameKeys;
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider (service = IEntityProcessingService.class)
public class EnemyProcess implements IEntityProcessingService{
    private static final String FILE_PATH = "../../../Enemy/src/main/java/dk/four/group/enemy/data/dead.png";

    long lastPress = 0;
    boolean canShoot = true;
    
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        //System.out.println(world.values().size());
        canShoot = (System.currentTimeMillis() - lastPress) > 1500;
        
        if(gameData.getKeys().isDown(GameKeys.SHIFT)){
            if(canShoot){
            
            ResourceManager.createAssest(FILE_PATH);
            Entity enemyEnt = new Entity();
            enemyEnt.setType(ENEMY);

            //enemyEnt.setEntityPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 4);

            enemyEnt.setMaxSpeed(100);
            enemyEnt.setAcceleration(200);
            enemyEnt.setDeacceleration(10);
            enemyEnt.setSize(64);
            enemyEnt.setRadius(64/2);
            enemyEnt.setEntityPosition(0, 0);
            //playerShip.setSprite(new Texture(this.getClass().getClassLoader().getResource("data/playertext.png").toExternalForm()));

            enemyEnt.setRadians(3.1415f / 2);
            enemyEnt.setRotationSpeed(5);
            enemyEnt.setAsset(ResourceManager.getAsset(FILE_PATH));
            enemyEnt.setLife(1);
            enemyEnt.setEntityBody(new EntityBody( 64   , 64, EntityBody.CollisionShape.CIRCLE));
            world.put(enemyEnt.getID(), enemyEnt);
            lastPress = System.currentTimeMillis();
            }
        }
    }   
    
}
