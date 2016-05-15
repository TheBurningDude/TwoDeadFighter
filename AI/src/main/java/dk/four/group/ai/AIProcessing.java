/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.ai;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityBody;
import dk.four.group.common.data.EntityPosition;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.NodeType;
import dk.four.group.common.services.IEntityProcessingService;
import java.util.ArrayList;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Sidon K. K
 */
@ServiceProvider (service = IEntityProcessingService.class)
public class AIProcessing implements IEntityProcessingService{
    
    PathFinder pF;
    
    Entity player;
    EntityPosition playerPos;
    EntityPosition playerCenter;
    EntityBody playerBody;
    
    Entity enemy;
    EntityPosition enemyPos;
    EntityPosition enemyCenter;
    EntityBody enemyBody;
    
    float tempX;
    float tempY;
    
    float lastPathCal = 0;
    int i = 0;

    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        
            if(pF == null){
                pF = new PathFinder(gameData.getDisplayWidth() + 128,gameData.getDisplayHeight() + 128,64,64);
                //System.out.println("dk.four.group.ai.AIProcessing.process()");
                }   

                player = gameData.getPlayer();
                playerPos = player.getEntityPosition();
                playerBody = player.getEntityBody();
                
                if (playerPos == null || playerBody == null)
                {
                return;
                }
                playerCenter = new EntityPosition(playerPos.getX() + playerBody.getWidth()/2, playerPos.getY() + playerBody.getHeight()/2);
              long currentTime = System.currentTimeMillis();
        for(Entity e : world.values()){
            
 
            if (e.getType() != EntityType.ENEMY)
            {
                continue;
            }
                
                enemy = e;
                enemyPos = enemy.getEntityPosition();
                enemyBody = enemy.getEntityBody();
                
                enemyCenter = new EntityPosition(enemyPos.getX() + enemyBody.getWidth()/2, enemyPos.getY() + enemyBody.getHeight()/2);
                
                EntityPosition velocity = new EntityPosition(playerCenter.getX() - enemyCenter.getX(), playerCenter.getY() - enemyCenter.getY());
                float magnitude = (float) Math.sqrt(Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2));
                
                            pF.SetGridNode((int) playerCenter.getX(), (int) playerCenter.getY(), NodeType.END);
                            pF.SetGridNode((int) enemyCenter.getX(), (int) enemyCenter.getY(), NodeType.START);
                //System.out.println(magnitude);
                if(magnitude < 96){
                    if(magnitude <= 64 + (enemyBody.getWidth()/2) + (enemyBody.getHeight()/2)){
                        //attack
                    }
                    tempX = velocity.getX();
                    tempY = velocity.getY();
                    
                    if(!(tempX == 0 && tempY ==0)){
                                tempX /= magnitude;
                                tempY /= magnitude;
                            }  
                    
                    tempX *= enemy.getMaxSpeed();
                    tempY *= enemy.getMaxSpeed();
                    
                    velocity.setX(0);
                    velocity.setY(0);
                    
                            
                    
                }else if(magnitude < 800 && pF.findPath() == 1){
                    System.out.println((System.currentTimeMillis() - lastPathCal));
                    //System.out.println(lastPathCal);
                    ArrayList<Node> path = pF.GetPath();
                    if(pF.GetPath() == null || (System.currentTimeMillis() - lastPathCal) > 1500 ){
                        try{
                            //i = 0;
                            MapAI();
                            
                            path = pF.GetPath();
                            System.out.println("hey");
                            i = 0;
                            velocity = new EntityPosition(path.get(i).GetCenterX() - enemyCenter.getX(), path.get(i).GetCenterY() - enemyCenter.getY());
                            lastPathCal = System.currentTimeMillis();
                            
                            
                        }catch(Exception exception){
                            tempX = velocity.getX();
                            tempY = velocity.getY();
                            if(!(tempX == 0 && tempY ==0)){
                                tempX /= magnitude;
                                tempY /= magnitude;
                            }    
                            
                            tempX *= enemy.getMaxSpeed();
                            tempY *= enemy.getMaxSpeed();

                            velocity.setX(tempX);
                            velocity.setY(tempY);
                        }
                        
                        
                    }
                    if(pF.GetPath() != null){
                        i = 0;
                        //lastPathCal = System.currentTimeMillis();
                        EntityPosition currentPos = new EntityPosition(path.get(i).GetCenterX() - enemyCenter.getX(), path.get(i).GetCenterY() - enemyCenter.getY());
                        float currentMagnitude = (float) Math.sqrt(Math.pow(currentPos.getX(), 2) + Math.pow(currentPos.getY(), 2));
                        if(currentMagnitude < 64){
                            if(path.size() > 1){
                                i++;
                            }
                        }
                        velocity = new EntityPosition(path.get(i).GetCenterX() - enemyCenter.getX(), path.get(i).GetCenterY() - enemyCenter.getY());
                        tempX = velocity.getX();
                        tempY = velocity.getY();
                    
                         if(!(tempX == 0 && tempY ==0)){
                                tempX /= magnitude;
                                tempY /= magnitude;
                            }  
                    
                        tempX *= enemy.getMaxSpeed();
                        tempY *= enemy.getMaxSpeed();
                    
                        velocity.setX(tempX);
                        velocity.setY(tempY);
                    }
                    
                    
                }else{
                        tempX = velocity.getX();
                        tempY = velocity.getY();
                    
                         if(!(tempX == 0 && tempY ==0)){
                                tempX /= magnitude;
                                tempY /= magnitude;
                            }  
                    
                        tempX *= 0;
                        tempY *= 0;
                    
                        velocity.setX(tempX);
                        velocity.setY(tempY);
                }
                
                enemyPos.setX(enemyPos.getX() + velocity.getX() * gameData.getDelta());
                enemyPos.setY(enemyPos.getY() + velocity.getY() * gameData.getDelta());
                //System.out.println(enemyPos.getX() + enemyPos.getY());
                //set postion here
            }
            
        
        
        
    }
    
    private void MapAI(){
        pF.SetGridNode(192,192 , NodeType.UNPASSABLE);
        pF.SetGridNode(192,256 , NodeType.UNPASSABLE);
        pF.SetGridNode(256 ,192 , NodeType.UNPASSABLE);
        pF.SetGridNode(256 ,256  , NodeType.UNPASSABLE);
        
        pF.SetGridNode(192,704 , NodeType.UNPASSABLE);
        pF.SetGridNode(256,704 , NodeType.UNPASSABLE);
        pF.SetGridNode(192 ,768 , NodeType.UNPASSABLE);
        pF.SetGridNode(256 ,768  , NodeType.UNPASSABLE);
        
        pF.SetGridNode(704,704 , NodeType.UNPASSABLE);
        pF.SetGridNode(704,768 , NodeType.UNPASSABLE);
        pF.SetGridNode(768 ,704 , NodeType.UNPASSABLE);
        pF.SetGridNode(768 ,768  , NodeType.UNPASSABLE);
        
        pF.SetGridNode(704,192 , NodeType.UNPASSABLE);
        pF.SetGridNode(704,256 , NodeType.UNPASSABLE);
        pF.SetGridNode(768 ,192 , NodeType.UNPASSABLE);
        pF.SetGridNode(768 ,256  , NodeType.UNPASSABLE);
   
    }
}
