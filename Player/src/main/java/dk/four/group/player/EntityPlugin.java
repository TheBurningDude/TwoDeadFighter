/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.player;

import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.PLAYER;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Varun
 */
public class EntityPlugin implements IGamePluginService {

    private Map<String, Entity> world;
    private Entity player;

    public EntityPlugin() {
    }

    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        this.world = world;
        // Add entities to the world
        player = createPlayerShip(gameData);
        world.put(player.getID(), player);
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Entity();
        playerShip.setType(PLAYER);

        playerShip.setPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        playerShip.setMaxSpeed(300);
        playerShip.setAcceleration(200);
        playerShip.setDeacceleration(10);

        playerShip.setShapeX(new float[4]);
        playerShip.setShapeY(new float[4]);

        playerShip.setRadians(3.1415f / 2);
        playerShip.setRotationSpeed(5);

        playerShip.setLife(1);

        playerShip.setRadius(4);

        return playerShip;
    }

    @Override
    public void stop(GameData gameData) {
        // Remove entities
        world.remove(player.getID());
    }

}


