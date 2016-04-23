/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.common.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Sidon K. K
 */
public class ResourceManager {
    private static Map<String, Asset> assets = new ConcurrentHashMap<>();
    
    public static Collection<Asset> asset()
    {
        return assets.values();
    }
    
    public static void createAssest(String path){
        Asset a = null;
        a = new Asset(path);
        assets.put(path, a);
    }
    
    public static Asset getAsset(String path)
    {
        return assets.get(path);
    }

    public static void removeAsset(String path)
    {
        assets.remove(path);
    }
}
