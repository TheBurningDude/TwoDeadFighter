/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.enemy;

import dk.four.group.common.data.Entity;
import static dk.four.group.common.data.EntityType.ENEMY;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author nasib
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        if(entity.getType().equals(ENEMY)){
            wrap(gameData, entity);
        }

    }

    private void movement(GameData gameData, Entity entity) {

    }

    private void wrap(GameData gameData, Entity entity) {
        float x = entity.getEntityPosition().getX();
        float y = entity.getEntityPosition().getY();
        float dt = gameData.getDelta();
        float dx = entity.getDx();
        float dy = entity.getDy();
        // set position
        x += dx * dt;
        if (x > gameData.getDisplayWidth()) {
            x = gameData.getDisplayWidth() - 32;
        } else if (x < 0) {
            x = 0;
        }

        y += dy * dt;
        if (y > gameData.getDisplayHeight()) {
            y = gameData.getDisplayHeight() - 32;
        } else if (y < 0) {
            y = 0;
        }
        entity.setDx(dx);
        entity.setDy(dy);
        entity.setEntityPosition(x, y);
    }

}
