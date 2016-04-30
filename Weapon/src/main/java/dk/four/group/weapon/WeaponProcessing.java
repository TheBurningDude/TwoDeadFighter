/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.weapon;

import com.badlogic.gdx.math.MathUtils;
import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.PLAYER;
import static dk.four.group.common.data.EntityType.WEAPON;
import dk.four.group.common.data.GameData;
import static dk.four.group.common.data.GameKeys.SPACE;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class WeaponProcessing implements IEntityProcessingService{
    
    //Declarity services
    private float lifeTime;
    private float lifeTimer;
    private boolean remove;

    int i = 0;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        float x = entity.getEntityPosition().getX();
        float y = entity.getEntityPosition().getY();
        float radians = entity.getRadians();
        float width = entity.getSize();
        float height = entity.getSize();
        float dx = entity.getDx();
        float dy = entity.getDy();
        float speed = 350;
        int i = 0;

        if (entity.getType().equals(PLAYER) && gameData.getKeys().isDown(SPACE)) {

            Entity bullet = new Entity();
            bullet.setType(WEAPON);
            bullet.getEntityPosition().setX(entity.getEntityPosition().getX());  
            bullet.getEntityPosition().setY(entity.getEntityPosition().getY());
            bullet.setMaxSpeed(speed);
            //width = height = 2;
            bullet(gameData, bullet);
            wrap(gameData, bullet);
            
            
            setShape(bullet);
            world.put(bullet.getID(), bullet);
        }
    }

    private void bullet(GameData gameData, Entity entity) {
        System.out.println("hey iiiiiiiiiiiiii");

    }

    private void setShape(Entity entity) {
        float x = entity.getEntityPosition().getX();
        float y = entity.getEntityPosition().getY();
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        float radians = entity.getRadians();

        shapex[0] = x + MathUtils.cos(radians) * 8;
        shapey[0] = y + MathUtils.sin(radians) * 8;

        shapex[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapey[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 8;

        shapex[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapey[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapex[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapey[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private void wrap(GameData gameData, Entity entity) {
        float x = entity.getEntityPosition().getX();
        float y = entity.getEntityPosition().getY();
        float dt = gameData.getDelta();
        float dx = entity.getDx();
        float dy = entity.getDy();

        // Screen wrap
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            x = 0;
        } else if (x < 0) {
            x = gameData.getDisplayWidth();
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            y = 0;
        } else if (y < 0) {
            y = gameData.getDisplayHeight();
        }
        entity.setDx(dx);
        entity.setDy(dy);
        entity.setEntityPosition(x, y);

    }

    private boolean shouldRemove() {
        return remove;
    }
}
