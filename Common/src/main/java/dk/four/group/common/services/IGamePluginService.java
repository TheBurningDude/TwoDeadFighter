/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.common.services;

import dk.four.group.common.data.Entity;
import dk.four.group.common.data.GameData;
import java.util.Map;

/**
 *
 * @author nasib
 */
public interface IGamePluginService {
    void start(GameData gameData, Map<String, Entity> world);

    void stop(GameData gameData);
}
