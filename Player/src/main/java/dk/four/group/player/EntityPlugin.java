package dk.four.group.player;


import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import static dk.four.group.common.data.EntityType.PLAYER;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IGamePluginService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Varun
 */
@ServiceProvider(service = IGamePluginService.class)
public class EntityPlugin implements IGamePluginService {
    
    private static final String PLAYER_IMG = "../../../Player/src/main/java/dk/four/group/player/data/player.png";
    
    private Map<String, Entity> world;
    private Entity player;
    
    @Override
    public void start(GameData gameData, Map<String, Entity> world) {
        
        ResourceManager.createAssest(PLAYER_IMG);
        this.world = world;
        // Add entities to the world
        player = createPlayer(gameData);
        
        world.put(player.getID(), player); 
        gameData.addPlayer(player);
    }

    private Entity createPlayer(GameData gameData) {
        
        Entity playerP = new Entity();
        playerP.setType(PLAYER);
        
        playerP.setEntityPosition(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);

        playerP.setMaxSpeed(300);
        playerP.setAcceleration(200);
        playerP.setDeacceleration(10);
        playerP.setSize(64);
        playerP.setRadius(64/2);
        
        playerP.setRadians(0);
        playerP.setRotationSpeed(5);
        playerP.setAsset(ResourceManager.getAsset(PLAYER_IMG));
        playerP.setLife(100);
        playerP.setEntityBody(new EntityBody( 64   , 64, EntityBody.CollisionShape.CIRCLE));
        
        return playerP;
    }

    @Override
    public void stop(GameData gamedata) {
        // Remove entities
        world.remove(player.getID());

    }

}
