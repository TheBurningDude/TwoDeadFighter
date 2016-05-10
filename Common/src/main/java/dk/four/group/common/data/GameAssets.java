/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.common.data;

/**
 *
 * @author nasib
 */
public class GameAssets {
    
    private final String fileName;
    private final String path;

    public GameAssets(String fileName, String path) {
        this.fileName = fileName;
        this.path = path;
    }
    
    public String getName(){
        return fileName;
    }

    public int getID(){
        return hashCode();
    }
    
    public String getPath(){
        return path;
    }
    
    
}
