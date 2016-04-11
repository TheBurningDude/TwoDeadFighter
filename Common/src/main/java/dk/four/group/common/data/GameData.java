/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.common.data;

import dk.four.group.common.events.Event;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author nasib
 */
public class GameData {
    private float delta;
    private int displayWidth;
    private int displayHeight;
    private final GameKeys keys = new GameKeys();
    private List<Event> events = new CopyOnWriteArrayList<>();
    
    public void addEvent(Event e){
        events.add(e);
    }
    
    public void removeEvent(Event e){
        events.remove(e);
    }
    
    public List<Event> getEvents(){
        return events;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public float getDelta() {
        return delta;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

}