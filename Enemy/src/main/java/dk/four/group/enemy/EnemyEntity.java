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
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IGamePluginService.class)
public class EnemyEntity implements IGamePluginService {

    private static final String FILE_PATH = "../../../Enemy/src/main/java/dk/four/group/enemy/data/dead.png";
    private Map<String, Entity> world;
    private Entity enemy,entity2;
    
    public EnemyEntity() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        ResourceManager.createAssest(FILE_PATH);
        this.world = world;
        // Add entities to the world
        enemy = createEnemy(gameData);
        entity2 = createEnemy(gameData);
        entity2.setEntityPosition(200, 200);
        world.put(entity2.getID(), entity2);
        world.put(enemy.getID(), enemy);
    }
    
    private Entity createEnemy(GameData gameData) {
        Entity enemyEnt = new Entity();
        enemyEnt.setType(ENEMY);
        
        enemyEnt.setEntityPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 4);

        enemyEnt.setMaxSpeed(130);
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
        
        //playerP.setRadius(32/2);

        return enemyEnt;
    }

    @Override
    public void stop(GameData gamedata) {
        // Remove entities
        world.remove(enemy.getID());
        
    }
    
    
}
