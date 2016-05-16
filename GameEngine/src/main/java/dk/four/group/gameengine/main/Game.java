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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.files.FileHandle;
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
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openide.util.Lookup.Item;

/**
 *
 * @author nasib
 */
public class Game implements ApplicationListener {

    private static OrthographicCamera cam;
    private final Lookup lookup = Lookup.getDefault();
    private final GameData gameData = new GameData();
    private Map<String, Entity> world = new ConcurrentHashMap<>();
    private List<IGamePluginService> gamePlugins = new CopyOnWriteArrayList<>();
    private Lookup.Result<IGamePluginService> result;

    private ShapeRenderer _shapeRenderer;
    private AssetManager _assetManager;
    private SpriteBatch sb;
    private BitmapFont font;
    private float x;
    private float y;
    private float life;
    @Override
    public void create() {

        _shapeRenderer = new ShapeRenderer();
        _assetManager = new AssetManager();

        sb = new SpriteBatch();

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        result = lookup.lookupResult(IGamePluginService.class);
        result.addLookupListener(lookupListener);
        
        ClassLoader loader = Lookup.getDefault().lookup(ClassLoader.class);
        FileHandle file = new FileHandle(loader.getResource("fonts/HyperspaceBold.ttf").getPath());
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(file);
        font = gen.generateFont(40);

        for (Item<IGamePluginService> plugin : result.allItems()) {
            plugin.getInstance().start(gameData, world);
        }

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
        loadAssets();
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
        //System.out.println(world.values().size());
        for (Entity entity : world.values()) {

            Asset a = entity.getAsset();

            if(a == null) continue;

            if (_assetManager.isLoaded(a.getPath())) {


                sb.begin();

                Texture t = _assetManager.get(a.getPath(), Texture.class);
                Sprite s = new Sprite(t);

                
                
                if(null != entity.getType())switch (entity.getType()) {

                        case PLAYER:
                            _shapeRenderer.begin(ShapeType.Line);
                            _shapeRenderer.setColor(Color.RED);
                            _shapeRenderer.circle(entity.getEntityPosition().getX() + entity.getEntityBody().getWidth() / 2, entity.getEntityPosition().getY() + entity.getEntityBody().getHeight() / 2, entity.getRadius());
                            _shapeRenderer.rect(entity.getEntityPosition().getX(), entity.getEntityPosition().getY(), entity.getEntityBody().getWidth(), entity.getEntityBody().getHeight());
                            x = entity.getEntityPosition().getX();
                            y = entity.getEntityPosition().getY();
                            s.setSize(s.getWidth(), s.getHeight());
                            //s.setRotation(entity.getRadians());
                            s.setOrigin(32, 32);
                            s.setRotation(entity.getRadians());
                            s.setPosition(entity.getEntityPosition().getX(), entity.getEntityPosition().getY());
                            //System.out.println(entity.getRadians());
                            break;

                        case WEAPON:
                            s.setRotation(entity.getRadians());
                            s.setPosition(entity.getEntityPosition().getX(), entity.getEntityPosition().getY());

                            break;
                        case MAP:
                            _shapeRenderer.begin(ShapeType.Line);
                            _shapeRenderer.setColor(Color.BLUE);
                            /*for (int i = 0; i < 16; i++) {
                                for (int j = 0; j < 16; j++) {
                                    _shapeRenderer.rect(i * 64, j * 64, 64, 64);
                                }

                            }*/
                            //score
                            
                           
                            s.setSize(entity.getSize(), entity.getSize());
                            s.setRotation(entity.getRadians());
                            s.setPosition(entity.getEntityPosition().getX(), entity.getEntityPosition().getY());

                            break;
                        case BULLET:
                            s.setSize(entity.getSize(), entity.getSize());
                            s.setRotation(entity.getRadians() - 90);
                            //float newY = entity.getEntityPosition().getY() + (1000 * gameData.getDelta());
                            //System.out.println(newY);

                            s.setPosition(entity.getEntityPosition().getX(), entity.getEntityPosition().getY());

                            break;
                        case ENEMY:

                        _shapeRenderer.begin(ShapeType.Line);
                        _shapeRenderer.setColor(Color.RED);
                        _shapeRenderer.circle(entity.getEntityPosition().getX() + 32, entity.getEntityPosition().getY()+ 32, entity.getRadius());
                        float angle = (float) Math.atan2(y - entity.getEntityPosition().getY(), x - entity.getEntityPosition().getX());
                        angle = (float) (angle * (180 / Math.PI));
                        if(angle < 0)angle= 360 -(-angle);
                        s.setSize(entity.getSize(),entity.getSize());
                        s.setRotation(angle);
                        //float newY = entity.getEntityPosition().getY() + (1000 * gameData.getDelta());
                        //System.out.println(newY);
                        
                        s.setPosition(entity.getEntityPosition().getX(),entity.getEntityPosition().getY());      
                    
                        break;
                        
                    default:
                            
                            break;

                    }

                
                        
        
                s.draw(sb);


                        sb.end();
                        
          
               _shapeRenderer.end();


            }
            
            if(entity.getType().equals(EntityType.HEALTH)){
                sb.begin();
                sb.setColor(0, 0, 1, 1);
                font.draw(sb, Long.toString((long) gameData.getScore()), 50, 900);
                                _shapeRenderer.begin(ShapeType.Filled);
                                _shapeRenderer.setColor(Color.GREEN);
                                _shapeRenderer.rect(50,gameData.getDisplayHeight()-50, gameData.getPlayer().getLife(), 20);
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

            Collection<? extends IGamePluginService> updated = result.allInstances();

            for (IGamePluginService us : updated) {
                // Newly installed modules
                if (!gamePlugins.contains(us)) {
                    us.start(gameData, world);
                    gamePlugins.add(us);
                }
            }

            // Stop and remove module
            for (IGamePluginService gs : gamePlugins) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData);
                    gamePlugins.remove(gs);
                }
            }
        }
    };

    private void loadAssets() {

        //for loop loadingg all assets
        for (Asset asset : ResourceManager.asset()) {
            String path = asset.getPath();
            if (!_assetManager.isLoaded(path, Texture.class)) {
                _assetManager.load(path, Texture.class);
            }
        }
        //s = new Sprite(ResourceManager.manager.get(ResourceManager.player_location1, Texture.class));
        //s2 = new Sprite(ResourceManager.manager.get(ResourceManager.player_location, Texture.class));

    }

}
