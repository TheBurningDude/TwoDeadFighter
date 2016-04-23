/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.common.data;

/**
 *
 * @author Sidon K. K
 */
public class Asset {
    private final String assetPath;
    
      
    public Asset(String path){
        assetPath = path;
    
    }     
    public String getPath(){
        return assetPath;
    }
}
