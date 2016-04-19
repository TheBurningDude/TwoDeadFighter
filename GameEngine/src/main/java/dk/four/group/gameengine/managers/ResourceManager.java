/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.gameengine.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;


/**
 *
 * @author nasib
 */
public class ResourceManager {
    public static final AssetManager manager = new AssetManager(new AbsoluteFileHandleResolver());
    public static final String player_location = "C:/Users/nasib/Documents/GitHub/TwoDeadFighter/Player/src/main/resources/data/grass.jpg";
    //"D:/TwoDeadFighter/Player/src/main/resources/data/grass.jpg"
    public static final String player_location1 = "C:/Users/nasib/Documents/GitHub/TwoDeadFighter/Player/src/main/resources/data/playertext.png";
    //"D:/TwoDeadFighter/Player/src/main/resources/data/playertext.png"
    public static Texture t;
       
    public static void load(){

        manager.load(player_location, Texture.class);
        manager.load(player_location1, Texture.class);
        
        while(!manager.update()){
            System.out.println("Loaded " + manager.getProgress()*100 + "%"); 
            
        }
        
       
    }
    
    public static Boolean isLoaded(){
        
        if(manager.getProgress() >= 1){
            return true;
        }
        return false;
    }
    
    public static void dispose(){
        manager.dispose();

    }
}
