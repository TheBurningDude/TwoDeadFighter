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
        float dt = gameData.getDelta();
        float dx = entity.getDx();
        float dy = entity.getDy();
        
//        float acceleration = entity.getAcceleration();
//        float deceleration = entity.getDeacceleration();
//        float maxSpeed = entity.getMaxSpeed();
        float radians = entity.getRadians();
//        float rotationSpeed = entity.getRotationSpeed();
        
        if (entity.getType().equals(PLAYER)) {
            // turning
            if (gameData.getKeys().isDown(LEFT)) {
                x -= 2;
                radians = 180;
//radians = (float) (Math.PI);
            }
            
            if (gameData.getKeys().isDown(RIGHT)) {
                x += 2;
                radians = 0;
            }
            if (gameData.getKeys().isDown(UP)) {
                y += 2;
                radians = 90;
    //radians = (float) (Math.PI/2);
            }
            if (gameData.getKeys().isDown(DOWN)) {
                y -= 2;
                radians = 270;
///radians = (float) ((3*Math.PI)/2);
            }
            //http://math.rice.edu/~pcmi/sphere/degrad.gif
            if (gameData.getKeys().isDown(UP) && gameData.getKeys().isDown(LEFT)) {
                    radians = 135;
//radians = (float) ((3*Math.PI)/4);
            }
            if (gameData.getKeys().isDown(UP) && gameData.getKeys().isDown(RIGHT)) {
                    radians = 45;
//radians = (float) (Math.PI/4);
            }
            if (gameData.getKeys().isDown(DOWN) && gameData.getKeys().isDown(LEFT)) {
                    radians = 225;
//radians = (float) ((5*Math.PI)/4);
            }
            if (gameData.getKeys().isDown(DOWN) && gameData.getKeys().isDown(RIGHT)) {
                    radians = 315;
//radians = (float) ((7*Math.PI)/4);
            }
            //Shoot
            if(gameData.getKeys().isDown(SPACE)){
                gameData.addEvent(new Event(EventType.PLAYER_SHOOT));
            }

            // set position
            x += dx * dt;
            if(x > gameData.getDisplayWidth()){
                x = gameData.getDisplayWidth();
            }else if(x < 0){
                x = 0;
            }
            
            y += dy * dt;
            if(y > gameData.getDisplayHeight()){
                y = gameData.getDisplayHeight();
            }else if(y < 0){
                y = 0;
            }
            
            // Update entity
            entity.setPosition(x, y);
            entity.setDx(dx);
            entity.setDy(dy);
            entity.setRadians(radians);

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
