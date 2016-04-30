/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.four.group.gameengine.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import dk.four.group.common.data.Asset;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import dk.four.group.common.data.Entity;
import dk.four.group.common.data.EntityType;
import dk.four.group.common.data.GameData;
import dk.four.group.common.data.ResourceManager;
import dk.four.group.common.services.IEntityProcessingService;
import dk.four.group.common.services.IGamePluginService;
import dk.four.group.gameengine.managers.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author nasib
 */
public class Game implements ApplicationListener {

   private static OrthographicCamera cam;
    private ShapeRenderer sr;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private Map<String, Entity> world = new ConcurrentHashMap<>();
    private List<IGamePluginService> gamePlugins;
    private ShapeRenderer _shapeRenderer;
    private AssetManager _assetManager;
    private SpriteBatch sb;
 
   
    @Override
    public void create() {
        //ResourceManager.load();
        //sb = new SpriteBatch();
        _shapeRenderer = new ShapeRenderer();
        sb = new SpriteBatch();
        _assetManager = new AssetManager();

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        Lookup.Result<IGamePluginService> result = lookup.lookupResult(IGamePluginService.class);
        result.addLookupListener(lookupListener);
        gamePlugins = new ArrayList<>(result.allInstances());
        result.allItems();

        for (IGamePluginService plugin : gamePlugins) {
            plugin.start(gameData, world);
        }
        //s = new Sprite(ResourceManager.manager.get(ResourceManager.player_location1, Texture.class));
        //s2 = new Sprite(ResourceManager.manager.get(ResourceManager.player_location, Texture.class));
        
        loadAssets();
        _assetManager.finishLoading();
    }

    @Override
    public void render() {
        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());
        gameData.getKeys().update();
        
        update();
        draw();
    }

    private void update() {
        // Update
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            for (Entity e : world.values()) {
                entityProcessorService.process(gameData, world, e);
            }
        }
        _assetManager.update();
    }

    private void draw() {
      
        _shapeRenderer.setProjectionMatrix(cam.combined);
        for (Entity entity : world.values()) {
            
            Asset a = entity.getAsset();
            
            if(_assetManager.isLoaded(a.getPath())){
                sb.begin();
                if(entity.getType() == EntityType.PLAYER){
                 
                 
                _shapeRenderer.begin(ShapeType.Line);
                _shapeRenderer.setColor(Color.RED);
                _shapeRenderer.circle(entity.getEntityPosition().getX() + entity.getEntityBody().getWidth()/2  , entity.getEntityPosition().getY() + entity.getEntityBody().getHeight()/2, entity.getRadius());
                
                }else{
                _shapeRenderer.begin(ShapeType.Line);
                _shapeRenderer.setColor(Color.RED);
                _shapeRenderer.rect(entity.getEntityPosition().getX(), entity.getEntityPosition().getY() , entity.getEntityBody().getWidth(), entity.getEntityBody().getHeight());
                
                }
                
                
                Texture t = _assetManager.get(a.getPath(), Texture.class);
                Sprite s = new Sprite(t);
                s.setSize(entity.getSize(),entity.getSize());
                //s.setCenter(entity.getX(), entity.getY());
                s.setRotation(entity.getRadians());
                s.setPosition(entity.getEntityPosition().getX(), entity.getEntityPosition().getY());
                //sb.draw(t, entity.getX(), entity.getY());
                s.draw(sb);

                sb.end();
                _shapeRenderer.end();
            }
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        //ResourceManager.dispose();
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return lookup.lookupAll(IEntityProcessingService.class);
    }

    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {
            for (IGamePluginService updatedGamePlugin : lookup.lookupAll(IGamePluginService.class)) {
                if (!gamePlugins.contains(updatedGamePlugin)) {
                    updatedGamePlugin.start(gameData, world);
                    gamePlugins.add(updatedGamePlugin);
                }
            }
        }
    };
    
    private void loadAssets(){
    
        //for loop loadingg all assets
        for(Asset asset : ResourceManager.asset()){
            String path = asset.getPath();
            if(!_assetManager.isLoaded(path, Texture.class)){
            _assetManager.load(path, Texture.class);
            }
        }
        //s = new Sprite(ResourceManager.manager.get(ResourceManager.player_location1, Texture.class));
        //s2 = new Sprite(ResourceManager.manager.get(ResourceManager.player_location, Texture.class));

    }
}
