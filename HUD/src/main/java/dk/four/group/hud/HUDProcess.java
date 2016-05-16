/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.hud;


import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider(service = IEntityProcessingService.class)
public class HUDProcess implements IEntityProcessingService{
    
    

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
        for(Entity pl : world.values()){
                //System.out.println("dk.four.group.hud.HUDProcess.process()");
                
                if(pl.getType().equals(EntityType.PLAYER)){
                    if(pl.getLife() <= 100 && pl.getIsHit()){
                        
                        pl.setLife(pl.getLife() - 20);
                        //System.out.println("dk.four.group.hud.HUDProcess.process()");
                        pl.setIsHit(false);
                    }
                    if(pl.getLife() == 0){
                        world.remove(pl.getID());
                    }
                    
                }
                
            }
    }

    
}
