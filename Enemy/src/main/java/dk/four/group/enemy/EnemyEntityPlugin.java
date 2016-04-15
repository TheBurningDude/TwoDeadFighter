/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.enemy;

import java.util.Map;
import static dk.four.group.common.data.EntityType.ENEMY;
import dk.four.group.common.data.Entity;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IGamePluginService;

/**
 *
 * @author Varun
 */
public class EnemyEntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity enemy;

    public EnemyEntityPlugin() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        // Add entities to the world
        enemy = createEnemySoldier(gameData);
        world.put(enemy.getID(), enemy);
    }
    
    private Entity createEnemySoldier(GameData gameData) {
        Entity enemySoldier = new Entity();
        enemySoldier.setType(ENEMY);

        return enemySoldier;
    }

    @Override
    public void stop(GameData gameData) {
        world.remove(enemy.getID());
    }

}
