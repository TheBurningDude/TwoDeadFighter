/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.ai;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.EntityPosition;
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
    int pathFound = 0;
    float playerCenterX;
    float playerCenterY;
    float enemyCenterX;
    float enemyCenterY;
    long lastPress = 0;
    float x;
    float y;
    float X;
    float Y;
    float nx;
    float ny;
    boolean gotpath = true;
    @Override
    public void process(GameData gameData, Map<String, Entity> world, Entity entity) {
        long currentTime = System.currentTimeMillis();
        //System.out.println("dk.four.group.ai.AIProcessing.process()");
        if(pF == null){
            pF = new PathFinder(gameData.getDisplayWidth(),gameData.getDisplayHeight(),64,64);
            //System.out.println("dk.four.group.ai.AIProcessing.process()");
        }
        if(entity.getType().equals(EntityType.MAP)){
                        pF.SetGridNode(192,192 , NodeType.UNPASSABLE);
                        pF.SetGridNode(192,256 , NodeType.UNPASSABLE);
                        pF.SetGridNode(256 ,192 , NodeType.UNPASSABLE);
                        pF.SetGridNode(256 ,256  , NodeType.UNPASSABLE);
        }
        
        for(Entity player : world.values()){
            for(Entity enemy : world.values()){
                
                if(enemy.getType().equals(EntityType.ENEMY) && player.getType().equals(EntityType.PLAYER) ){
                    enemyCenterX = enemy.getEntityPosition().getX() + enemy.getEntityBody().getWidth()/2;
                    enemyCenterY = enemy.getEntityPosition().getY() + enemy.getEntityBody().getHeight()/2;
                    EntityPosition dist = new  EntityPosition((player.getEntityPosition().getX() + player.getEntityBody().getWidth()/2) - enemyCenterX, (player.getEntityPosition().getY() + player.getEntityBody().getHeight()/2) - enemyCenterY);
                    
                    float gm = (float) Math.sqrt(Math.pow(dist.getX(), 2) + Math.pow(dist.getY(), 2));
                        pF.SetGridNode((int)enemyCenterX,(int) enemyCenterY, NodeType.START);
                        pF.SetGridNode((int)player.getEntityPosition().getX() + 32, (int)player.getEntityPosition().getY() + 32, NodeType.END);
                    //gotpath = (System.currentTimeMillis() - lastPress) > gameData.getDelta();
                    gotpath = (System.currentTimeMillis() - lastPress) > 1000;
                    if(gotpath){
   
                        if(pF.findPath() == 1){
                        ArrayList<Node> path = pF.GetPath();
                    //enemy.setEntityPosition(300, 300);
                    
                    //System.out.println(path.size());
                    if(path != null ){
                        int i = 0;
                        //System.out.println(enemy.getEntityPosition().getX());
                        //playerCenterX = (int) path.get(i).GetCenterX();
                        //playerCenterrY = (int) path.get(i).GetCenterX();

                        //float dist = (float) Math.sqrt((enemy.getEntityPosition().getX()- path.get(0).getX())*(enemy.getEntityPosition().getX()-path.get(0).getX()) + (enemy.getEntityPosition().getY()-path.get(0).getY()) * (enemy.getEntityPosition().getY()-path.get(0).getY()));
                            
                           
                                
                            if(path.size() > 1){
                                i++;
                            }
                                
                       
                                
                            
                               
                                    X = (int) path.get(i).getX();
                                    Y = (int) path.get(i).getY();
                                    
                                 
                                  //System.out.println(dist);
                                    
                                    //enemy.setEntityPosition(x,y);
                                    
                                //System.out.println(X);
                                //X += Math.cos(enemy.getRadians()) * enemy.getMaxSpeed() * gameData.getDelta();
                               // Y += Math.sin(enemy.getRadians()) * enemy.getMaxSpeed() * gameData.getDelta();
                               
                                
                                    //System.out.println(path.size());
                                    //enemy.setEntityPosition(X, Y);
//                                int nx = (int) (X - enemy.getEntityPosition().getX());
//                                int ny = (int) (Y - enemy.getEntityPosition().getY());
//
//                                float norm = (float) Math.sqrt(nx * nx + ny * ny);
//                                playerCenterX = (int) ((nx/norm) * enemy.getMaxSpeed());
//                                playerCenterY = (int) ((ny/norm) * enemy.getMaxSpeed());
//
//                                x +=  playerCenterX *gameData.getDelta();
//                                y +=  playerCenterY * gameData.getDelta();


                                enemy.setEntityPosition(X, Y);
                                
                                lastPress = System.currentTimeMillis();
                                System.out.println(path.size());
                            
                        }
                    }
                        
                        //enemy.setEntityPosition(playerCenterX, playerCenterrY);
                    }
                    //float dist = (float) Math.sqrt((enemy.getEntityPosition().getX() - X) * (enemy.getEntityPosition().getX() - X) + (enemy.getEntityPosition().getY() - Y) * (enemy.getEntityPosition().getY() - Y) );
                                
                }
                    
                }
        }
    }
}
