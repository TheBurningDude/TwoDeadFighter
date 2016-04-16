/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.gameengine.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import dk.four.group.common.data.GameAssets;

/**
 *
 * @author nasib
 */
public class ResourceManager {
    private final GameAssets gameAssets;
    public static AssetManager manager = new AssetManager();
    public static String player_location;
    private static String filePath;

    public ResourceManager(GameAssets gameAssets) {
        this.gameAssets = gameAssets;
    }
    
    public String getFileName(){
        return filePath;
    }
    
    public static void load(){
        manager = new AssetManager();
        filePath = "../player/assets";
        
        player_location = filePath+"/playertext.png";
        manager.load(player_location, Texture.class);
        
        while(!manager.update()){
            System.out.println("Loaded " + manager.getProgress()*100 + "%");
        }
        
        //player_Texture = manager.get(player_location);
        
    }
    
    public static Boolean isLoaded(){
        
        if(manager.getProgress() >= 1){
            return true;
        }
        return false;
    }
    
    public static void dispose(){
        manager.dispose();
        manager = null;
    }
}
