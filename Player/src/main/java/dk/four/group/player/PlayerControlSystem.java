package dk.four.group.player;

import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.PLAYER;
import dk.four.group.common.data.GameData;
import static dk.four.group.common.data.GameKeys.DOWN;
import static dk.four.group.common.data.GameKeys.LEFT;
import static dk.four.group.common.data.GameKeys.RIGHT;
import static dk.four.group.common.data.GameKeys.SPACE;
import static dk.four.group.common.data.GameKeys.UP;
import dk.four.group.common.events.Event;
import dk.four.group.common.events.EventType;
import dk.four.group.common.services.IEntityProcessingService;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;


@ServiceProvider(service = IEntityProcessingService.class)
public class PlayerControlSystem implements IEntityProcessingService {
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();

        
        if (entity.getType().equals(PLAYER)) {
            // turning
            if (gameData.getKeys().isDown(LEFT)) {
                x-=2;
            }
            
            if (gameData.getKeys().isDown(RIGHT)) {
                x-=2;

            }
            
            //Shoot
            if(gameData.getKeys().isDown(SPACE)){
                gameData.addEvent(new Event(EventType.PLAYER_SHOOT));
            }

            // accelerating            
            if (gameData.getKeys().isDown(UP)) {
                y-=2;
            }
            
            if (gameData.getKeys().isDown(DOWN)) {
                y+=2;
            }


            // set position
            if(x > gameData.getDisplayWidth()){
                x = gameData.getDisplayWidth();
            }else if(x < 0){
                x = 0;
            }
            
            if(y > gameData.getDisplayHeight()){
                y = gameData.getDisplayHeight();
            }else if(y < 0){
                y = 0;
            }
            
            // Update entity
            entity.setPosition(x, y);
            updateShape(entity);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float x = entity.getX();
        float y = entity.getY();
        float radians = entity.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 8);
        shapey[0] = (float) (y + Math.sin(radians) * 8);

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * 8);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 5);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 5);

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * 8);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * 8);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
